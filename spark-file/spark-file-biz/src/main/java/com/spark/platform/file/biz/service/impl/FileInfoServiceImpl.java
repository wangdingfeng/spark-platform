package com.spark.platform.file.biz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.spark.plateform.file.api.dto.FileInfoDTO;
import com.spark.plateform.file.api.dto.FileQueryDTO;
import com.spark.plateform.file.api.entity.FileInfo;
import com.spark.platform.common.base.constants.BizConstants;
import com.spark.platform.common.base.constants.GlobalsConstants;
import com.spark.platform.common.base.exception.BusinessException;
import com.spark.platform.common.base.support.WrapperSupport;
import com.spark.platform.common.config.properties.SparkProperties;
import com.spark.platform.common.utils.FileUtil;
import com.spark.platform.file.biz.dao.FileInfoDao;
import com.spark.platform.file.biz.service.FileInfoService;
import com.spark.platform.file.biz.utils.MinioUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 文件信息表 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-04-18
 */
@Service
@AllArgsConstructor
@Slf4j
public class FileInfoServiceImpl extends ServiceImpl<FileInfoDao, FileInfo> implements FileInfoService {

    private final SparkProperties sparkProperties;

    @Override
    public FileInfo upload(MultipartFile file) {
        log.info("文件上传开始");
        //获取文件名称
        String fileName = file.getOriginalFilename();
        String fileType = StringUtils.substringAfterLast(fileName, ".");
        String fileCode = UUID.randomUUID().toString().replace("-", "");
        String fileUploadName = fileCode + "." + fileType;
        String filePath = GlobalsConstants.FILE_PATH_TEMP + File.separator + DateUtil.formatDate(new Date());
        //拼接文件上传路径
        String fileUploadPath = sparkProperties.getFilePath() + File.separator + filePath+File.separator;
        //保存文件
        try {
            File folder = new File(fileUploadPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            File dest = new File(fileUploadPath + fileUploadName);
            file.transferTo(dest);
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(fileName);
            fileInfo.setFileCode(fileCode);
            fileInfo.setFileType(fileType);
            fileInfo.setFileSize(new FileInputStream(dest).available());
            fileInfo.setFilePath(filePath + File.separator + fileUploadName);
            fileInfo.setStatus(BizConstants.FILE_STATUS_NO_BIND);
            super.save(fileInfo);
            return fileInfo;
        } catch (IOException e) {
            log.error("上传文件失败", e);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = false)
    public void bindFile(FileInfoDTO fileInfoDTO) {
        log.info("文件绑定开始,绑定id:{},绑定类型:{}", fileInfoDTO.getBizId(), fileInfoDTO.getBizType());
        try {
            //创建当前的存储桶
            MinioUtil.makeBucket(fileInfoDTO.getServiceName());
            //删除文件
            if (CollectionUtil.isNotEmpty(fileInfoDTO.getDeleteFileIds())) {
                super.removeByIds(fileInfoDTO.getDeleteFileIds());
            }
            //绑定新文件
            List<FileInfo> fileInfoList = Lists.newArrayList();
            for (Long fileId : fileInfoDTO.getFileIds()) {
                FileInfo tempFile = super.getById(fileId);
                FileInfo fileInfo = new FileInfo();
                fileInfo.setId(fileId);
                fileInfo.setBizId(fileInfoDTO.getBizId());
                fileInfo.setBizType(fileInfoDTO.getBizType());
                //转移文件 将临时路径转移到绑定的路径
                String srcPath = sparkProperties.getFilePath() + File.separator + tempFile.getFilePath();
                //拼接正式文件路径
                StringBuilder bizPathBuild = new StringBuilder(GlobalsConstants.FILE_PATH_BIZ);
                bizPathBuild.append(GlobalsConstants.FILE_SEPARATOR).append(fileInfoDTO.getBizType()).append(GlobalsConstants.FILE_SEPARATOR)
                        .append( fileInfoDTO.getBizId()).append( GlobalsConstants.FILE_SEPARATOR).append(tempFile.getFileCode()).append(".").append(tempFile.getFileType());
                // 上传到Minio文件存储中
                MinioUtil.putObject(fileInfoDTO.getServiceName(),bizPathBuild.toString(),new FileInputStream(srcPath));
                fileInfo.setFilePath(bizPathBuild.toString());
                fileInfo.setStatus(BizConstants.FILE_STATUS_BIND);
                fileInfoList.add(fileInfo);
            }
            super.updateBatchById(fileInfoList);
        } catch (IOException e) {
            log.error("绑定文件失败", e);
            throw new BusinessException("绑定文件失败");
        }
    }

    @Override
    public IPage findPage(Page page, FileInfo fileInfo) {
        QueryWrapper wrapper = new QueryWrapper<FileInfo>();
        WrapperSupport.putParamsLike(wrapper,fileInfo,"fileName","fileType");
        WrapperSupport.putParamsEqual(wrapper,fileInfo,"bizId","bizType","status");
        return super.page(page, wrapper);
    }

    @Override
    public List<FileInfo> findByBiz(FileQueryDTO fileQueryDTO) {
        FileInfo file = new FileInfo();
        if(StringUtils.isNotEmpty(fileQueryDTO.getBizType())){
            file.setBizType(fileQueryDTO.getBizType());
        }
        if(StringUtils.isNotEmpty(fileQueryDTO.getBizId())){
            file.setBizId(fileQueryDTO.getBizId());
        }
        if(StringUtils.isNotEmpty(fileQueryDTO.getServiceName())){
            file.setServiceName(fileQueryDTO.getServiceName());
        }
        return super.list(Wrappers.query(file));
    }

    @Override
    public void downloadFile(Long id, HttpServletResponse response) {
        FileInfo fileInfo = super.getById(id);
        Assert.notNull(fileInfo, "找不到此文件信息");
        try {
            // 判断当前文件是临时文件还是绑定文件
            InputStream inputStream;
            if(BizConstants.FILE_STATUS_NO_BIND.equals(fileInfo.getStatus())){
                String fileRealPath = sparkProperties.getFilePath() + File.separator + fileInfo.getFilePath();
                inputStream = new FileInputStream(fileRealPath);
            } else {
                inputStream = MinioUtil.getObject(fileInfo.getServiceName(),fileInfo.getFilePath());
            }
            FileUtil.download(fileInfo.getFileName(), inputStream, response);
        } catch (IOException e) {
            log.error("下载文件失败", e);
            throw new BusinessException("下载失败");
        }

    }

    @Override
    public String getDownloadURl(Long id, Integer expires) {
        FileInfo fileInfo = super.getById(id);
        Assert.notNull(fileInfo, "找不到此文件信息");
        Assert.isTrue(BizConstants.FILE_STATUS_BIND.equals(fileInfo.getStatus()),"当前文件不是正式文件状态");
        if(null == expires){
            expires = MinioUtil.DEFAULT_EXPIRY_TIME;
        }
        return MinioUtil.presignedGetObject(fileInfo.getServiceName(),fileInfo.getFilePath(),expires);
    }
}

package com.spark.platform.adminbiz.service.file;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.adminapi.dto.FileInfoDTO;
import com.spark.platform.adminapi.entity.file.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 文件信息表 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-04-18
 */
public interface FileInfoService extends IService<FileInfo> {

    /**
     * 文件上传
     * @param file
     * @return
     */
    FileInfo upload(MultipartFile file);

    /**
     * 文件绑定
     * @param fileInfoDTO
     */
    void bindFile(FileInfoDTO fileInfoDTO);

    /**
     * 分页
     * @param page
     * @param fileInfo
     * @return
     */
    IPage findPage(Page page,FileInfo fileInfo);

    /**
     * 通过业务查询数据
     * @param bizId 业务id
     * @param bizType 业务类型
     * @return
     */
    List<FileInfo> findByBiz(String bizId,String bizType);

    /**
     * 下载文件
     * @param id
     */
    void downloadFile(Long id, HttpServletResponse response);

}

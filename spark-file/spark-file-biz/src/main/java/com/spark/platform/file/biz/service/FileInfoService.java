package com.spark.platform.file.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.plateform.file.api.dto.FileInfoDTO;
import com.spark.plateform.file.api.dto.FileQueryDTO;
import com.spark.plateform.file.api.entity.FileInfo;
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
     *
     * @param file
     * @return
     */
    FileInfo upload(MultipartFile file);

    /**
     * 文件绑定
     *
     * @param fileInfoDTO
     */
    void bindFile(FileInfoDTO fileInfoDTO);

    /**
     * 分页
     *
     * @param page
     * @param fileInfo
     * @return
     */
    IPage findPage(Page page, FileInfo fileInfo);

    /**
     * 通过业务查询数据
     *
     * @param fileQueryDTO 文件信息查询
     * @return
     */
    List<FileInfo> findByBiz(FileQueryDTO fileQueryDTO);

    /**
     * 下载文件
     *
     * @param id       文件ID
     * @param response
     */
    void downloadFile(Long id, HttpServletResponse response);

    /**
     * 获取下载地址 有失效时间
     *
     * @param id 文件地址
     * @param expires  失效时间 不允许大于7天
     * @return
     */
    String getDownloadURl(Long id, Integer expires);

}

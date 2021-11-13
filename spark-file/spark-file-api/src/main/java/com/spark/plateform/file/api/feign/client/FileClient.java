package com.spark.plateform.file.api.feign.client;

import com.spark.plateform.file.api.dto.FileInfoDTO;
import com.spark.plateform.file.api.dto.FileQueryDTO;
import com.spark.plateform.file.api.entity.FileInfo;
import com.spark.plateform.file.api.feign.fallback.FileClientFallBackFactory;
import com.spark.platform.common.base.constants.ServiceNameConstants;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.feign.config.FeignRequestInterceptorConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.plateform.file.api.feign.client
 * @ClassName: FileClient
 * @Author: wangdingfeng
 * @Description: 文件信息请求服务接口
 * @Date: 2020/11/7 13:25
 * @Version: 1.0
 */
@FeignClient(contextId = "fileClient", name = ServiceNameConstants.SPARK_FILE, configuration = FeignRequestInterceptorConfig.class, fallbackFactory = FileClientFallBackFactory.class)
public interface FileClient {

    /**
     * 文件绑定接口
     *
     * @param fileInfoDTO
     * @return
     */
    @PatchMapping
    ApiResponse bind(@RequestBody FileInfoDTO fileInfoDTO);

    /**
     * 通过业务信息查询文件信息
     *
     * @param fileQueryDTO
     * @return
     */
    @GetMapping("/biz")
    ApiResponse<List<FileInfo>> findByBiz(FileQueryDTO fileQueryDTO);

    /**
     * 通过文件ID 查询数据
     *
     * @param id 文件ID
     * @return
     */
    @GetMapping("/{id}")
    ApiResponse<FileInfo> getById(@PathVariable("id") Long id);

    /**
     * 获取文件下载路径
     *
     * @param id      文件ID
     * @param expires 时效时间 不允许大于 7天
     * @return
     */
    @GetMapping("/url/{id}")
    ApiResponse<String> url(@PathVariable("id") Long id,@RequestParam("expires") Integer expires);


}

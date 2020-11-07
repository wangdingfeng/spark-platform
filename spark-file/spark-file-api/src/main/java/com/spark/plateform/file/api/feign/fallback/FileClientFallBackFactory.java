package com.spark.plateform.file.api.feign.fallback;

import com.spark.plateform.file.api.dto.FileInfoDTO;
import com.spark.plateform.file.api.dto.FileQueryDTO;
import com.spark.plateform.file.api.entity.FileInfo;
import com.spark.plateform.file.api.feign.client.FileClient;
import com.spark.platform.common.base.constants.ServiceNameConstants;
import com.spark.platform.common.base.support.ApiResponse;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.plateform.file.api.feign.fallback
 * @ClassName: FileFallback
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2020/11/7 13:26
 * @Version: 1.0
 */
@Component
@Slf4j
public class FileClientFallBackFactory implements FallbackFactory<FileClient> {
    @Override
    public FileClient create(Throwable throwable) {
        return new FileClient() {
            @Override
            public ApiResponse bind(FileInfoDTO fileInfoDTO) {
                log.error("调用spark-file服务FileClient:bind方法失败!,错误日志:{}", throwable);
                return ApiResponse.hystrixError(ServiceNameConstants.SPARK_FILE, "bind");
            }

            @Override
            public ApiResponse<List<FileInfo>> findByBiz(FileQueryDTO fileQueryDTO) {
                log.error("调用spark-file服务FileClient:findByBiz方法失败!,错误日志:{}", throwable);
                return ApiResponse.hystrixError(ServiceNameConstants.SPARK_FILE, "findByBiz");
            }

            @Override
            public ApiResponse<FileInfo> getById(Long id) {
                log.error("调用spark-file服务FileClient:getById方法失败!,错误日志:{}", throwable);
                return ApiResponse.hystrixError(ServiceNameConstants.SPARK_FILE, "getById");
            }

            @Override
            public ApiResponse<String> url(Long id, Integer expires) {
                log.error("调用spark-file服务FileClient:url方法失败!,错误日志:{}", throwable);
                return ApiResponse.hystrixError(ServiceNameConstants.SPARK_FILE, "url");
            }
        };
    }
}

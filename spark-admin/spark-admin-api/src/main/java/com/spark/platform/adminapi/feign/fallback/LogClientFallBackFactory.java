package com.spark.platform.adminapi.feign.fallback;

import com.spark.platform.adminapi.entity.log.LogApi;
import com.spark.platform.adminapi.entity.log.LogLogin;
import com.spark.platform.adminapi.feign.client.LogClient;
import com.spark.platform.common.base.constants.ServiceNameConstants;
import com.spark.platform.common.base.support.ApiResponse;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminapi.feign.fallback
 * @ClassName: ApiLogClientFallBack
 * @Author: wangdingfeng
 * @Description: 日志调用失败返回错误信息
 * @Date: 2020/3/24 13:33
 * @Version: 1.0
 */
@Component
@Slf4j
public class LogClientFallBackFactory implements FallbackFactory<LogClient> {

    @Override
    public LogClient create(Throwable throwable) {
        return new LogClient() {
            @Override
            public ApiResponse save(LogApi apiLog) {
                log.error("调用spark-admin服务LogClient:save方法失败!,错误日志:{}",throwable.getMessage());
                return ApiResponse.hystrixError(ServiceNameConstants.SPARK_ADMIN , "save");
            }

            @Override
            public ApiResponse saveLoginLog(LogLogin loginLog) {
                log.error("调用spark-admin服务LogClient:saveLoginLog方法失败!,错误日志:{}",throwable.getMessage());
                return ApiResponse.hystrixError(ServiceNameConstants.SPARK_ADMIN , "saveLoginLog");
            }
        };
    }
}

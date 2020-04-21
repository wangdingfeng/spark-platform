package com.spark.platform.adminapi.feign.client;

import com.spark.platform.adminapi.entity.log.LogApi;
import com.spark.platform.adminapi.entity.log.LogLogin;
import com.spark.platform.adminapi.feign.fallback.LogClientFallBackFactory;
import com.spark.platform.common.base.constants.ServiceNameConstants;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.feign.config.FeignRequestInterceptorConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminapi.feign.client
 * @ClassName: ApiLogClient
 * @Author: wangdingfeng
 * @Description: 日志 client
 * @Date: 2020/3/24 13:31
 * @Version: 1.0
 */
@FeignClient(contextId = "apiLogClient", name = ServiceNameConstants.SPARK_ADMIN, configuration = FeignRequestInterceptorConfig.class, fallbackFactory = LogClientFallBackFactory.class)
public interface LogClient {

    /**
     * 保存日志
     * @param apiLog
     * @return
     */
    @PostMapping("/log/api")
    ApiResponse save(@RequestBody LogApi apiLog);

    /**
     * 保存登录日志
     * @param loginLog
     * @return
     */
    @PostMapping("/login-log/api")
    ApiResponse saveLoginLog(@RequestBody LogLogin loginLog);


}

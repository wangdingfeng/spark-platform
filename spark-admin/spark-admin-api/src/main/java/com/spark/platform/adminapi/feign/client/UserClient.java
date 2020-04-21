package com.spark.platform.adminapi.feign.client;


import com.spark.platform.common.base.constants.ServiceNameConstants;
import com.spark.platform.common.feign.config.FeignRequestInterceptorConfig;
import com.spark.platform.adminapi.feign.fallback.UserClientFallBackFactory;
import com.spark.platform.common.base.support.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminapi.feign.client
 * @ClassName: UserClient
 * @Description:
 * @Version: 1.0
 */
@FeignClient(contextId = "userClient", name = ServiceNameConstants.SPARK_ADMIN, configuration = FeignRequestInterceptorConfig.class, fallbackFactory = UserClientFallBackFactory.class)
public interface UserClient {

    @GetMapping("/user/api")
    ApiResponse getUserByUserName(@RequestParam String username);

    @GetMapping("/user/info/{id}")
    ApiResponse getUserByUserId(@PathVariable Long id);
}

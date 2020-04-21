package com.spark.platform.adminapi.feign.client;

import com.spark.platform.common.base.constants.ServiceNameConstants;
import com.spark.platform.common.feign.config.FeignRequestInterceptorConfig;
import com.spark.platform.adminapi.feign.fallback.AuthorityClientFallBackFactory;
import com.spark.platform.common.base.support.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminapi.feign.client
 * @ClassName: AuthorityClient
 * @Description:
 * @Version: 1.0
 */
@FeignClient(contextId = "authorityClient", name = ServiceNameConstants.SPARK_ADMIN, configuration = FeignRequestInterceptorConfig.class, fallbackFactory = AuthorityClientFallBackFactory.class)
public interface AuthorityClient {

    @GetMapping("/authority/api/info")
    ApiResponse getOauthClientDetailsByClientId(@RequestParam String clientId);
}

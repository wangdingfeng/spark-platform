package com.spark.platform.admin.api.feign.client;

import com.spark.platform.admin.api.feign.fallback.RoleClientFallBackFactory;
import com.spark.platform.common.base.constants.ServiceNameConstants;
import com.spark.platform.common.feign.config.FeignRequestInterceptorConfig;
import com.spark.platform.common.base.support.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminapi.feign.client
 * @ClassName: RoleClient
 * @Description:
 * @Version: 1.0
 */
@FeignClient(contextId = "roleClient", name = ServiceNameConstants.SPARK_ADMIN, configuration = FeignRequestInterceptorConfig.class, fallbackFactory = RoleClientFallBackFactory.class)
public interface RoleClient {
    /**
     * 根据用户id获取用户角色信息
     * @param id
     * @return
     */
    @GetMapping("/role/{id}")
    ApiResponse getRoleByUserId(@PathVariable("id") Long id);
}

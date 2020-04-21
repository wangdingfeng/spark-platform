package com.spark.platform.adminapi.feign.fallback;

import com.spark.platform.common.base.constants.ServiceNameConstants;
import com.spark.platform.adminapi.feign.client.RoleClient;
import com.spark.platform.common.base.support.ApiResponse;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: waangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminapi.feign.fallback
 * @ClassName: RoleClientFallBack
 * @Description:
 * @Version: 1.0
 */
@Component
@Slf4j
public class RoleClientFallBackFactory implements FallbackFactory<RoleClient> {

    @Override
    public RoleClient create(Throwable throwable) {
        return new RoleClient() {
            @Override
            public ApiResponse getRoleByUserId(Long id) {
                log.error("调用spark-admin服务RoleClient:getRoleByUserId方法失败!,错误日志:{}", throwable.getMessage());
                return ApiResponse.hystrixError(ServiceNameConstants.SPARK_ADMIN, "getRoleByUserId");
            }
        };
    }
}

package com.spark.platform.flowable.api.feign.fallback;

import com.spark.platform.common.base.constants.ServiceNameConstants;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.flowable.api.feign.client.InstanceClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/8 22:52
 * @Description:
 */
@Component
@Slf4j
public class InstanceFallBackFactory implements FallbackFactory<InstanceClient> {
    @Override
    public InstanceClient create(Throwable throwable) {
        return new InstanceClient() {
            @Override
            public ApiResponse startByKey(String key, String businessKey, String businessType, String businessName, Map<String, Object> variables) {
                log.error("调用spark-flowable服务InstanceClient:startByKey方法失败!,错误日志:{}", throwable.getMessage());
                return ApiResponse.hystrixError(ServiceNameConstants.SPARK_FLOWABLE, "startByKey");
            }
        };
    }
}

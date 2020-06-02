package com.spark.platform.flowable.api.feign.fallback;

import com.spark.platform.common.base.constants.ServiceNameConstants;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.flowable.api.feign.client.InstanceClient;
import com.spark.platform.flowable.api.request.ProcessInstanceCreateRequest;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


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
            public ApiResponse startByKey(ProcessInstanceCreateRequest request) {
                log.error("调用spark-flowable服务InstanceClient:startByKey方法失败!,错误日志:{}", throwable);
                return ApiResponse.hystrixError(ServiceNameConstants.SPARK_FLOWABLE, "InstanceClient:startByKey");
            }

            @Override
            public ApiResponse delete(String processInstanceId) {
                log.error("调用spark-flowable服务InstanceClient:delete方法失败!,错误日志:{}", throwable);
                return ApiResponse.hystrixError(ServiceNameConstants.SPARK_FLOWABLE, "InstanceClient:delete");
            }
        };
    }
}

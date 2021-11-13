package com.spark.platform.flowable.api.feign.client;

import com.spark.platform.common.base.constants.ServiceNameConstants;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.feign.config.FeignRequestInterceptorConfig;
import com.spark.platform.flowable.api.feign.fallback.InstanceFallBackFactory;
import com.spark.platform.flowable.api.feign.fallback.TaskFallBackFactory;
import com.spark.platform.flowable.api.request.ProcessInstanceCreateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/8 22:47
 * @Description:
 */
@FeignClient(contextId = "instanceClient", name = ServiceNameConstants.SPARK_FLOWABLE, configuration = FeignRequestInterceptorConfig.class, fallbackFactory = InstanceFallBackFactory.class)
public interface InstanceClient {

    /**
     * 开启流程
     * @return
     */
    @PostMapping("runtime/process-instances")
    ApiResponse startByKey(@RequestBody ProcessInstanceCreateRequest request);

    /**
     * 删除流程
     * @param processInstanceId 流程实例ID
     * @return
     */
    @DeleteMapping(value = "runtime/process-instances/{processInstanceId}")
    ApiResponse delete(@PathVariable("processInstanceId") String processInstanceId);

}

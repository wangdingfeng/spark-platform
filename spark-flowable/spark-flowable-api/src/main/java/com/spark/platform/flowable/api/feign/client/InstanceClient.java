package com.spark.platform.flowable.api.feign.client;

import com.spark.platform.common.base.constants.ServiceNameConstants;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.feign.config.FeignRequestInterceptorConfig;
import com.spark.platform.flowable.api.feign.fallback.InstanceFallBackFactory;
import com.spark.platform.flowable.api.feign.fallback.TaskFallBackFactory;
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
     * @param key 流程key
     * @param businessKey 主键
     * @param businessType 业务类型
     * @param businessName 业务name
     * @param variables 参数
     * @return
     */
    @PostMapping("runtime/process-instances")
    ApiResponse startByKey(@RequestParam(value = "key") String key, @RequestParam(value = "businessKey") String businessKey, @RequestParam(value = "businessType") String businessType,
                           @RequestParam(value = "businessName") String businessName, @RequestBody Map<String, Object> variables);

    /**
     * 删除流程
     * @param processInstanceId 流程实例ID
     * @return
     */
    @DeleteMapping(value = "runtime/process-instances/{processInstanceId}")
    ApiResponse delete(@PathVariable String processInstanceId);

}

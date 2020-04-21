package com.spark.platform.flowable.api.feign.client;

import com.spark.platform.common.base.support.ApiResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/8 22:47
 * @Description:
 */
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
    ApiResponse startByKey(@RequestParam(value = "key") String key, @RequestParam(value = "businessKey") String businessKey, @RequestParam(value = "businessType") String businessType,
                           @RequestParam(value = "businessName") String businessName, @RequestBody Map<String, Object> variables);

}

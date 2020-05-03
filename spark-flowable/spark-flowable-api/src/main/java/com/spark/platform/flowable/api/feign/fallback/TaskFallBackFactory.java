package com.spark.platform.flowable.api.feign.fallback;

import com.spark.platform.common.base.constants.ServiceNameConstants;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.flowable.api.feign.client.TaskClient;
import com.spark.platform.flowable.api.request.TaskRequestQuery;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/8 22:44
 * @Description:
 */
@Component
@Slf4j
public class TaskFallBackFactory implements FallbackFactory<TaskClient> {
    @Override
    public TaskClient create(Throwable throwable) {
        return new TaskClient() {
            @Override
            public ApiResponse executeTask(String taskId, String action, String assignee, boolean localScope, Map<String, Object> variables) {
                log.error("调用spark-flowable服务TaskClient:executeTask方法失败!,错误日志:{}", throwable.getMessage());
                return ApiResponse.hystrixError(ServiceNameConstants.SPARK_FLOWABLE, "TaskClient:executeTask");
            }

            @Override
            public ApiResponse addComments(String taskId, String processInstanceId, String message) {
                log.error("调用spark-flowable服务TaskClient:addComments!,错误日志:{}", throwable.getMessage());
                return ApiResponse.hystrixError(ServiceNameConstants.SPARK_FLOWABLE, "TaskClient:addComments");
            }

            @Override
            public ApiResponse getTask(TaskRequestQuery taskRequestQuery) {
                log.error("调用spark-flowable服务TaskClient:getTask!,错误日志:{}", throwable.getMessage());
                return ApiResponse.hystrixError(ServiceNameConstants.SPARK_FLOWABLE, "TaskClient:getTask");
            }
        };
    }
}

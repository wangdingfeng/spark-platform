package com.spark.platform.flowable.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.flowable.api.request
 * @ClassName: ExecuteTaskRequest
 * @Author: wangdingfeng
 * @Description: 任务执行
 * @Date: 2020/5/5 11:31
 * @Version: 1.0
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ExecuteTaskRequest",description = "task任务执行")
public class ExecuteTaskRequest {
    @ApiModelProperty(value = "任务ID")
    private String taskId;
    @ApiModelProperty(value = "执行任务类型")
    private String action;
    @ApiModelProperty(value = "受让人")
    private String assignee;
    @ApiModelProperty(value = "流程参数存储范围")
    private boolean localScope = false;
    @ApiModelProperty(value = "参数")
    private Map<String,Object> variables;
}

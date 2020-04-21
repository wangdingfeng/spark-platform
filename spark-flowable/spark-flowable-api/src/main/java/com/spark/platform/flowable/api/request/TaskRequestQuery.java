package com.spark.platform.flowable.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.List;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.flowable.api.request
 * @ClassName: TaskQuery
 * @Author: wangdingfeng
 * @Description: 任务查询
 * @Date: 2020/4/9 10:59
 * @Version: 1.0
 */
@Data
@ApiModel(value = "TaskRequestQuery",description = "任务查询query")
public class TaskRequestQuery {
    @ApiModelProperty(value = "任务ID")
    private String taskId;
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "用户groupIds")
    private List<String> groupIds;
    @ApiModelProperty(value = "当前节点name")
    private String name;
    @ApiModelProperty(value = "流程实例key")
    private String taskDefinitionKey;
    @ApiModelProperty(value = "业务主键")
    private String businessKey;
    @ApiModelProperty(value = "业务类型")
    private String businessType;
    @ApiModelProperty(value = "业务名称")
    private String businessName;
    @ApiModelProperty(value = "流程实例ID")
    private String processInstanceId;
    /**
     * 页码
     */
    private long size;
    /**
     * 当前页
     */
    private long current;
}

package com.spark.platform.flowable.api.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

/**
 * @author: wangdingfeng
 * @Date: 2020/5/29 21:17
 * @Description: 流程实例
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="ProcessInstance", description="流程实例对象")
public class ProcessInstanceVo {

    private String id;

    private String processDefinitionKey;

    private String processDefinitionId;

    private String businessKey;

    private String processDefinitionName;

    private Date startTime;

    private String deploymentId;

    private String description;

    private String name;

    private Integer processDefinitionVersion;

    private Map<String, Object> processVariables;

    private String startUserId;

    private String callbackId;

    private String callbackType;

    private String tenantId;

    private String localizedDescription;

    private String localizedName;

    private String processInstanceId;

    private String activityId;

    private String parentId;

    private String rootProcessInstanceId;

    private String superExecutionId;

    private Boolean isSuspended;

    private Boolean isEnded;

}

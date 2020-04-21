package com.spark.platform.flowable.api.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/6 22:34
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="ProcessDefinition", description="流程定义对象")
public class ProcessDefinitionVO {

    private String id;

    private String category;

    private String name;

    private String key;

    private String description;

    private int version;

    private String resourceName;

    private String deploymentId;

    private String diagramResourceName;

    private String tenantId;

    private String derivedFrom;

}

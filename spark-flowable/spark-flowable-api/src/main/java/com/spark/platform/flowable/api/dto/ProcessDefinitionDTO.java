package com.spark.platform.flowable.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/6 22:16
 * @Description:
 */
@Data
@NoArgsConstructor
@ApiModel(value = "ProcessDefinitionDTO",description = "流程定义查询DTO")
public class ProcessDefinitionDTO {

    @ApiModelProperty(value = "流程名称")
    private String name;
    @ApiModelProperty(value = "流程KEY")
    private String key;
    @ApiModelProperty(value = "资源名称")
    private String resourceName;
    @ApiModelProperty(value = "分类")
    private String category;
    @ApiModelProperty(value = "租户")
    private String tenantId;

}

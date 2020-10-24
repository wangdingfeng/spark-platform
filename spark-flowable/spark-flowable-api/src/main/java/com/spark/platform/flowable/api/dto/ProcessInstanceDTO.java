package com.spark.platform.flowable.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: wangdingfeng
 * @Date: 2020/5/29 21:02
 * @Description:
 */
@Data
@ApiModel(value = "ProcessDefinitionDTO",description = "流程实例查询DTO")
public class ProcessInstanceDTO {
    @ApiModelProperty(value = "业务ID")
    private String businessKey;
    @ApiModelProperty(value = "实例名称")
    private String name;
}

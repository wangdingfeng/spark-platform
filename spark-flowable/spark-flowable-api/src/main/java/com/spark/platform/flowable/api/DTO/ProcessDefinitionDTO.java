package com.spark.platform.flowable.api.DTO;

import io.swagger.annotations.ApiModel;
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

    private String name;

    private String key;

    private String resourceName;

    private String category;

    private String tenantId;

}

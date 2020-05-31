package com.spark.platform.flowable.api.DTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: wangdingfeng
 * @Date: 2020/5/29 21:02
 * @Description:
 */
@Data
@NoArgsConstructor
@ApiModel(value = "ProcessDefinitionDTO",description = "流程实例查询DTO")
public class ProcessInstanceDTO {

    private String businessKey;

    private String name;

}

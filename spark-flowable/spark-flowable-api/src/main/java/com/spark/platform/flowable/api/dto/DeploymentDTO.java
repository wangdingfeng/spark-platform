package com.spark.platform.flowable.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/6 15:24
 * @Description: 部署DTO
 */
@Data
@ApiModel(value = "DeploymentDTO",description = "部署查询DTO")
public class DeploymentDTO {
    @ApiModelProperty(value = "部署名称")
    private String deploymentName;
    @ApiModelProperty(value = "部署名实例key")
    private String deploymentKey;
    @ApiModelProperty(value = "分类名称")
    private String category;
    @ApiModelProperty(value = "部署时间")
    private Date deploymentTime;
    @ApiModelProperty(value = "租户")
    private String tenantId;
    @ApiModelProperty(value = "版本")
    private String engineVersion;

}

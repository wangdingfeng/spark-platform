package com.spark.platform.flowable.api.DTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/6 15:24
 * @Description:
 */
@Data
@NoArgsConstructor
@ApiModel(value = "DeploymentDTO")
public class DeploymentDTO {
    /**
     * 流程实例名称
     */
    private String deploymentName;
    /**
     * 流程实例key
     */
    private String deploymentKey;
    /**
     * 分类名称
     */
    private String category;
    /**
     * 部署时间
     */
    private Date deploymentTime;
    /**
     * 租户
     */
    private String tenantId;
    /**
     * 版本
     */
    private String engineVersion;

}

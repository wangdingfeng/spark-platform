package com.spark.platform.common.config.properties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.config.properties
 * @ClassName: QiNiuProperties
 * @Author: wangdingfeng
 * @Description: 七牛云配置
 * @Date: 2020/12/19 15:36
 * @Version: 1.0
 */
@Getter
@Setter
public class QiNiuProperties {
    @ApiModelProperty(value = "accessKey")
    private String accessKey;
    @ApiModelProperty(value = "secretKey")
    private String secretKey;
    @ApiModelProperty(value = "空间名")
    private String bucketName;
    @ApiModelProperty(value = "域名")
    private String domain;
}

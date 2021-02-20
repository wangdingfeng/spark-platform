package com.spark.platform.common.config.properties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: wangdingfeng
 * @Date: 2020/12/13 20:55
 * @Description: 微信配置项
 */
@Getter
@Setter
public class SparkWxProperties {
    @ApiModelProperty(value = "登录凭证校验")
    private String authUrl = "https://api.weixin.qq.com/sns/jscode2session";
    @ApiModelProperty(value = "小程序 appId")
    private String appId;
    @ApiModelProperty(value = "小程序 appSecret")
    private String secret;
}

package com.spark.platform.common.config.properties;

import com.spark.platform.common.base.constants.GlobalsConstants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/12 17:17
 * @Description: spark 自定义配置
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = GlobalsConstants.SPARK_PREFIX)
public class SparkProperties {
    @ApiModelProperty(value = "文件路径")
    private String filePath;
    @ApiModelProperty(value = "redis配置")
    SparkRedisProperties redis = new SparkRedisProperties();
    @ApiModelProperty(value = "微信配置")
    SparkWxProperties wx = new SparkWxProperties();
}

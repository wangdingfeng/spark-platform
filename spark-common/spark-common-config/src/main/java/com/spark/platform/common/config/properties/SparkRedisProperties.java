package com.spark.platform.common.config.properties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.config.redis
 * @ClassName: SparkRedisProperties
 * @Author: wangdingfeng
 * @Description: redis配置
 * @Date: 2020/7/1 10:14
 * @Version: 1.0
 */
@Getter
@Setter
public class SparkRedisProperties {
    @ApiModelProperty(value = "是否开启redis缓存")
    private Boolean enable = true;
}

package com.spark.platform.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.config
 * @ClassName: LogisticsConfig
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2021/1/14 9:58
 * @Version: 1.0
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "logistics")
public class LogisticsConfig {
    /**
     * 圆通配置
     */
    private YtoConfig yto;
}

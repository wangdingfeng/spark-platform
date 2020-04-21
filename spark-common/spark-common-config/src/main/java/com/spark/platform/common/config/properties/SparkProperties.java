package com.spark.platform.common.config.properties;

import com.spark.platform.common.base.constants.GlobalsConstants;
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
    /**
     * 文件路径
     */
    private String filePath;
}

package com.spark.platform.common.security.properties;

import com.spark.platform.common.base.constants.GlobalsConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.security.properties
 * @ClassName: SparkSecurityProperties
 * @Description:
 * @Version: 1.0
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = GlobalsConstants.SPARK_OAUTH_PREFIX)
public class SparkSecurityProperties {

    WebProperties web = new WebProperties();

    OAuth2Properties oauth2 = new OAuth2Properties();

}

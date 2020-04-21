package com.spark.platform.common.security.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.security.properties
 * @ClassName: FilterIgnoreProperties
 * @Description: 授权码 信息
 * @Version: 1.0
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "security.oauth2.client")
public class SecurityOAuth2ClientProperties {

    private String clientId;

    private String clientSecret;
}

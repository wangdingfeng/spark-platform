package com.spark.platform.common.security.properties;

import com.spark.platform.common.base.enums.LoginType;
import lombok.Getter;
import lombok.Setter;


/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.security.properties
 * @ClassName: WebProperties
 * @Description:
 * @Version: 1.0
 */
@Getter
@Setter
public class WebProperties {

    private String loginPage;

    private LoginType loginType = LoginType.JSON;

    private String[] unInterceptUris = {};
}

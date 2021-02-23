package com.spark.platform.common.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.base.enums
 * @ClassName: GrantTypesEnum
 * @Author: wangdingfeng
 * @Description: 授权模式
 * @Date: 2020/12/14 18:29
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum GrantTypesEnum {

    PASSWORD("password","密码模式"),
    REFRESH_TOKEN("refresh_token","刷新token"),
    AUTHORIZATION_CODE("authorization_code","授权码"),
    CLIENT_CREDENTIALS("client_credentials","客户端模式"),
    IMPLICIT("implicit","简化模式");

    private String type;
    private String desc;
}

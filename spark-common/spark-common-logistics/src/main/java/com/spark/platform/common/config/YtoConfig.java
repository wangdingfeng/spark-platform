package com.spark.platform.common.config;

import lombok.Getter;
import lombok.Setter;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.config
 * @ClassName: YtoConfig
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2021/1/14 9:59
 * @Version: 1.0
 */
@Getter
@Setter
public class YtoConfig {
    /**
     * 请求地址
     */
    private String apiUrl;
    /**
     * 客户编码
     */
    private String appKey;
    /**
     * 客户标识
     */
    private String userId;
    /**
     * 密钥
     */
    private String secretKey;
    /**
     * API协议的版本号
     */
    private String v = "1.01";
}

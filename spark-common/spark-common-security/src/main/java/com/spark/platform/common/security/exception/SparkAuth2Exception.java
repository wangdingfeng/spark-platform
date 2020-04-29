package com.spark.platform.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.spark.platform.common.security.support.SparkAuth2ExceptionSerializer;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.security.exception
 * @ClassName: SparkAuth2Exception
 * @Author: wangdingfeng
 * @Description: oauth2 异常 注意请使用spring cloud的稳定版本 否则翻译失效
 * @Date: 2020/4/29 16:28
 * @Version: 1.0
 */
@JsonSerialize(using = SparkAuth2ExceptionSerializer.class)
public class SparkAuth2Exception extends OAuth2Exception {
    @Getter
    private String errorCode;

    public SparkAuth2Exception(String msg) {
        super(msg);
    }

    public SparkAuth2Exception(String msg, String errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }
}

package com.spark.platform.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author: wangdingfeng
 * @Date: 2020/6/13 19:02
 * @Description: 自定义OAuth2Exception 异常
 */
@JsonSerialize(using = SparkOAuthExceptionJacksonSerializer.class)
public class SparkOAuth2Exception extends OAuth2Exception {

    public SparkOAuth2Exception(String msg, Throwable t) {
        super(msg, t);

    }
    public SparkOAuth2Exception(String msg) {
        super(msg);

    }
}

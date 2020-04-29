package com.spark.platform.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.spark.platform.common.security.support.SparkAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.security.exception
 * @ClassName: MethodNotAllowed
 * @Author: wangdingfeng
 * @Description: 方法不被允许
 * @Date: 2020/4/29 16:38
 * @Version: 1.0
 */
@JsonSerialize(using = SparkAuth2ExceptionSerializer.class)
public class MethodNotAllowed extends SparkAuth2Exception {
    public MethodNotAllowed(String msg, Throwable t) {
        super(msg);
    }
    @Override
    public String getOAuth2ErrorCode() {
        return "method_not_allowed";
    }

    @Override
    public int getHttpErrorCode() {
        return HttpStatus.METHOD_NOT_ALLOWED.value();
    }

}

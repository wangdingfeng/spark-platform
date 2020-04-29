package com.spark.platform.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.spark.platform.common.security.support.SparkAuth2ExceptionSerializer;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.security.exception
 * @ClassName: InvalidException
 * @Author: wangdingfeng
 * @Description: 无效的凭证
 * @Date: 2020/4/29 16:36
 * @Version: 1.0
 */
@JsonSerialize(using = SparkAuth2ExceptionSerializer.class)
public class InvalidException extends SparkAuth2Exception {

    public InvalidException(String msg, Throwable t) {
        super(msg);
    }
    @Override
    public String getOAuth2ErrorCode() {
        return "invalid_exception";
    }
    @Override
    public int getHttpErrorCode() {
        return 426;
    }
}

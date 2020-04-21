package com.spark.platform.common.security.support;

import com.spark.platform.common.base.enums.SparkHttpStatus;
import com.spark.platform.common.base.support.ApiResponse;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidScopeException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.security.component
 * @ClassName: SparkWebResponseExceptionTranslator
 * @Description: oauth2异常翻译
 * @Version: 1.0
 */
public class SparkWebResponseExceptionTranslator implements WebResponseExceptionTranslator {

    @Override
    @SneakyThrows
    public ResponseEntity<?> translate(Exception e) {
        ResponseEntity.BodyBuilder status = ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        String message = "认证失败";
        if (e instanceof UnsupportedGrantTypeException) {
            message = "不支持该认证类型";
            return status.body(new ApiResponse(SparkHttpStatus.INVALID_TOKEN.getCode(), message,((UnsupportedGrantTypeException) e).getOAuth2ErrorCode()));
        }
        if (e instanceof InvalidTokenException
                && StringUtils.containsIgnoreCase(e.getMessage(), "Invalid refresh token (expired)")) {
            message = "刷新令牌已过期，请重新登录";
            return status.body(new ApiResponse(SparkHttpStatus.INVALID_TOKEN.getCode(), message,((InvalidTokenException) e).getOAuth2ErrorCode()));
        }
        if (e instanceof InvalidScopeException) {
            message = "不是有效的scope值";
            return status.body(new ApiResponse(SparkHttpStatus.INVALID_TOKEN.getCode(), message,((InvalidScopeException) e).getOAuth2ErrorCode()));
        }
        if (e instanceof InvalidGrantException) {
            if (StringUtils.containsIgnoreCase(e.getMessage(), "Invalid refresh token")) {
                message = "refresh token无效";
                return status.body(new ApiResponse(SparkHttpStatus.INVALID_TOKEN.getCode(), message,((InvalidGrantException) e).getOAuth2ErrorCode()));
            }
            message = "用户名或密码错误";
            return status.body(new ApiResponse(SparkHttpStatus.INVALID_TOKEN.getCode(), message,((InvalidGrantException) e).getOAuth2ErrorCode()));
        }
        if (e instanceof InternalAuthenticationServiceException) {
            //自定义异常
            message = e.getMessage();
        }
        return status.body(new ApiResponse(SparkHttpStatus.INVALID_TOKEN.getCode(), message));
    }
}



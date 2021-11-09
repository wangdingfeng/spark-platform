package com.spark.platform.common.feign.parser;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: wangdingfeng
 * @Date: 2021/11/9 14:18
 * @Description: 请求头解析判断
 */
public class SparkHeaderRequestOriginParser implements RequestOriginParser {

    /**
     * 请求头获取allow
     */
    private static final String ALLOW = "Allow";

    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader(ALLOW);
    }
}

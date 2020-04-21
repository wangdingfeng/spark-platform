package com.spark.platform.common.feign.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.feign.config
 * @ClassName: FeignRequestInterceptorConfig
 * @Description: feign 拦截器 清洗请求头信息
 * @Version: 1.0
 */
@Component
@Slf4j
public class FeignRequestInterceptorConfig implements RequestInterceptor {

    private final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        request.getRequestURI();
        //清洗日志请求头信息
        if (request != null) {
            // 只携带token
            String authorization = request.getHeader(AUTHORIZATION_HEADER);
            log.info("Authorization :\t\t" + authorization);
            requestTemplate.header(AUTHORIZATION_HEADER, authorization);

        }

    }

}
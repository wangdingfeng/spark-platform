package com.spark.platform.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.gateway.config
 * @ClassName: RequestRateConfig
 * @Author: wangdingfeng
 * @Description: 网关限流配置项 提供三种限流方式 需要哪个请打开哪个bean的注释
 * @Date: 2020/4/16 16:27
 * @Version: 1.0
 */
@Configuration
public class RequestRateConfig {
    /**
     * 通过ip地址实现限流
     * @return
     */
    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

    /**
     * 通过用户user 实现限流
     * @return
     */
//    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
    }

    /**
     * 通过用户url 实现限流
     * @return
     */
//    @Bean
    public KeyResolver uriKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getURI().getPath());
    }
}

package com.spark.platform.common.feign.config;

import com.alibaba.cloud.sentinel.feign.SentinelFeignAutoConfiguration;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import com.spark.platform.common.feign.ext.SparkSentinelFeign;
import com.spark.platform.common.feign.handle.SparkResponseBlockHandler;
import com.spark.platform.common.feign.parser.SparkHeaderRequestOriginParser;
import feign.Feign;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author: wangdingfeng
 * @Date: 2021/11/9 14:34
 * @Description:  sentinel 配置 代码参考pig
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(SentinelFeignAutoConfiguration.class)
@EnableFeignClients(basePackages = "com.spark.platform.**.feign")
public class SparkFeignAutoConfiguration {

    /**
     * 用来解决 FallbackFactory
     * @return
     */
    @Bean
    @Scope("prototype")
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "feign.sentinel.enabled")
    public Feign.Builder feignSentinelBuilder() {
        return SparkSentinelFeign.builder();
    }
    @Bean
    @ConditionalOnMissingBean
    public BlockExceptionHandler blockExceptionHandler() {
        return new SparkResponseBlockHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public RequestOriginParser requestOriginParser() {
        return new SparkHeaderRequestOriginParser();
    }
}

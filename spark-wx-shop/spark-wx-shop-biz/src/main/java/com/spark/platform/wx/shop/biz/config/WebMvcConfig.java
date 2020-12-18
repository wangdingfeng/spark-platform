package com.spark.platform.wx.shop.biz.config;

import com.spark.platform.common.config.properties.SparkProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.wx.shop.biz.config
 * @ClassName: WebMvcConfigurer
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2020/12/17 17:04
 * @Version: 1.0
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private final SparkProperties sparkProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**").addResourceLocations("file:" + sparkProperties.getFilePath());
    }
}

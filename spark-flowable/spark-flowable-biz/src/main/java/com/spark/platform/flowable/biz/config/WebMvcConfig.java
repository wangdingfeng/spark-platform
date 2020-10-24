package com.spark.platform.flowable.biz.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.flowable.biz.config
 * @ClassName: WebMvcConfig
 * @Author: wangdingfeng
 * @Description: mvc 配置
 * @Date: 2020/5/28 16:19
 * @Version: 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 注册静态文件
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/flowable/**").addResourceLocations("classpath:/flowable/");
    }
    /**
     * 注册自定义拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PathInterceptor()).addPathPatterns("/**");
    }

}

package com.spark.platform.common.log;

import com.spark.platform.admin.api.feign.client.LogClient;
import com.spark.platform.common.log.aspect.WebLogAspect;
import com.spark.platform.common.log.listener.ApiLogListener;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.log
 * @ClassName: LogAutoConfiguration
 * @Author: wangdingfeng
 * @Description: 异步配置器 代码参考pig
 * @Date: 2020/4/21 17:44
 * @Version: 1.0
 */
@EnableAsync
@AllArgsConstructor
@ConditionalOnWebApplication
@Configuration(proxyBeanMethods = false)
public class LogAutoConfiguration {

    private final LogClient logClient;

    @Bean
    public ApiLogListener sysLogListener() {
        return new ApiLogListener(logClient);
    }

    @Bean
    public WebLogAspect webLogAspect(){
        return new WebLogAspect();
    }
}

package com.spark.platform.common.security.annotation;

import com.spark.platform.common.security.config.SparkResourceServerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.security.annotation
 * @ClassName: EnableSparkResourceServer
 * @Author: wangdingfeng
 * @Description: 自定义资源服务器注解
 * @Date: 2020/7/30 13:45
 * @Version: 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SparkResourceServerConfig.class)
public @interface EnableSparkResourceServer {

}

package com.spark.platform.common.log.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.log.annotation
 * @ClassName: SysLog
 * @Author: wangdingfeng
 * @Description: 日志注解
 * @Date: 2020/7/3 11:51
 * @Version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiLog {
    /**
     * 日志title
     * @return
     */
    String value() default "";
    /**
     * 是否不记录日志
     * @return
     */
    boolean ignore () default false;
}

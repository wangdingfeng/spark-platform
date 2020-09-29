package com.spark.platform.common.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.base.annotation
 * @ClassName: DictProperty
 * @Author: wangdingfeng
 * @Description: 字典注解 用来导出excel 翻译
 * @Date: 2020/9/28 14:30
 * @Version: 1.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DictProperty {

    /**
     * 字典值
     * @return
     */
    String type() default "";

}

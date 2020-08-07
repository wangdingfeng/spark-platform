package com.spark.platform.common.datasource.annotation;

import java.lang.annotation.*;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.base.datasource.annotation
 * @ClassName: DataScope
 * @Author: wangdingfeng
 * @Description: 数据权限注解
 * @Date: 2020/6/5 13:25
 * @Version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {

    /**
     * 权限字段
     * @return
     */
    String field() default "dept_id";
    /**
     * 是否使用角色权限
     * @return
     */
    boolean isRole() default false;
    /**
     * 自定义权限
     * @return
     */
    String[] customize() default {};

}

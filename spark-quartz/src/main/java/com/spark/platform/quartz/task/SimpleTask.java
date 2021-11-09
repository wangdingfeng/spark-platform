package com.spark.platform.quartz.task;

import org.springframework.stereotype.Component;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.quartz.task
 * @ClassName: SimpleTask
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2020/6/9 16:32
 * @Version: 1.0
 */
@Component
public class SimpleTask {

    public void doMultipleParams(String s, Boolean b, Long l, Double d, Integer i) {
        System.out.println(String.format("执行多参方法： 字符串类型:%s，布尔类型:%b，长整型:%x，浮点型:%f，整形:%d", s, b, l, d, i));
    }

    public void doParams(String params) {
        System.out.println("执行有参方法：" + params);
    }

    public void doNoParams() {
        System.out.println("执行无参方法");
    }
}

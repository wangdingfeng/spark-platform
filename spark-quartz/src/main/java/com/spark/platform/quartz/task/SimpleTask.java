package com.spark.platform.quartz.task;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
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
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void doParams(String params) {
        System.out.println("执行有参方法：" + params);
    }

    public void doNoParams() {
        System.out.println("执行无参方法");
    }
}

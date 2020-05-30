package com.spark.platform.common.functional;

/**
 * @author: wangdingfeng
 * @Date: 2020/5/29 21:27
 * @Description: 拷贝函数
 */
@FunctionalInterface
public interface BeanCopyUtilCallBack <S, T> {

    /**
     * 定义默认回调方法
     * @param t
     * @param s
     */
    void callBack(S t, T s);
}

package com.spark.platform.common.utils;

import java.util.regex.Pattern;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.utils
 * @ClassName: UrlMatchersUtil
 * @Author: wangdingfeng
 * @Description: 通配符路径匹配
 * @Date: 2020/7/3 10:45
 * @Version: 1.0
 */
public class RequestUrlMatchersUtil {
    /**
     * 将通配符表达式转化为正则表达式
     *
     * @param path
     * @return
     */
    private static String getRegPath(String path) {
        char[] chars = path.toCharArray();
        int len = chars.length;
        StringBuilder sb = new StringBuilder();
        boolean preX = false;
        for (int i = 0; i < len; i++) {
            if (chars[i] == '*') {
                //遇到*字符
                if (preX) {
                    //如果是第二次遇到*，则将**替换成.*
                    sb.append(".*");
                    preX = false;
                } else if (i + 1 == len) {
                    //如果是遇到单星，且单星是最后一个字符，则直接将*转成[^/]*
                    sb.append("[^/]*");
                } else {
                    //否则单星后面还有字符，则不做任何动作，下一把再做动作
                    preX = true;
                    continue;
                }
            } else {
                //遇到非*字符
                if (preX) {
                    //如果上一把是*，则先把上一把的*对应的[^/]*添进来
                    sb.append("[^/]*");
                    preX = false;
                }
                if (chars[i] == '?') {
                    //接着判断当前字符是不是?，是的话替换成.
                    sb.append('.');
                } else {
                    //不是?的话，则就是普通字符，直接添进来
                    sb.append(chars[i]);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 通配符模式
     *
     * @param excludePath - 不过滤地址
     * @param reqUrl      - 请求地址
     * @return true 匹配成功 false 失败
     */
    public static boolean filterUrls(String excludePath, String reqUrl) {
        String regPath = getRegPath(excludePath);
        return Pattern.compile(regPath).matcher(reqUrl).matches();
    }

    /**
     * 校验是否路径是白名单放行的路径
     *
     * @param excludeUrls 配置路径集合
     * @param reqUrl 请求地址
     * @return true 匹配成功 false 失败
     */
    public static boolean checkWhiteList(String[] excludeUrls, String reqUrl) {
        for (String url : excludeUrls) {
            if (filterUrls(url, reqUrl)) {
                return true;
            }
        }
        return false;
    }
}

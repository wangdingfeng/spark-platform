package com.spark.platform.common.utils;

import cn.hutool.core.io.resource.ClassPathResource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.utils
 * @ClassName: AddressUtils
 * @Author: wangdingfeng
 * @Description: 根据ip获取地址
 * @Date: 2020/3/23 18:13
 * @Version: 1.0
 */
@Slf4j
public class AddressUtils {

    private static final String LOCAL_IP = "0:0:0:0:0:0:0:1";
    private static final String LOCAL_IP_127 = "127.0.0.1";
    private static final String UNKNOWN = "unknown";

    /**
     * 获取用户ip地址位置信息
     *
     * @param request
     * @return
     */
    public static String getCityInfo(HttpServletRequest request) {
        return getCityInfo(getIpAddress(request));
    }


    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址。
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        log.trace("当前IP来源[X-Forwarded-For], 值[{}]", ip);
        if(!StringUtils.isEmpty(ip) && !UNKNOWN.equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(',');
            if(index != -1){
                return ip.substring(0, index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        log.trace("当前IP来源[X-Real-IP], 值[{}]", ip);
        if(!StringUtils.isEmpty(ip) && !UNKNOWN.equalsIgnoreCase(ip)){
            return ip;
        }
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            log.trace("当前IP来源[Proxy-Client-IP], 值[{}]", ip);
        }
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            log.trace("当前IP来源[WL-Proxy-Client-IP], 值[{}]", ip);
        }
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            log.trace("当前IP来源[HTTP_CLIENT_IP], 值[{}]", ip);
        }
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            log.trace("当前IP来源[HTTP_X_FORWARDED_FOR], 值[{}]", ip);
        }
        if (StringUtils.isEmpty(ip)|| UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            log.trace("当前IP来源[getRemoteAddr], 值[{}]", ip);
            if (LOCAL_IP_127.equals(ip) || LOCAL_IP.equals(ip)) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    log.error("获取ip失败", e);
                }
                ip = inet.getHostAddress();
            }
        }
        return ip;
    }

    /**
     * 根据ip 获取位置信息
     *
     * @param ip
     * @return
     */
    public static String getCityInfo(String ip) {
        try {
            String dbPath = AddressUtils.class.getResource("/ip2region/ip2region.db").getPath();
            File file = new File(dbPath);
            if (!file.exists()) {
                String tmpDir = System.getProperties().getProperty("java.io.tmpdir");
                dbPath = tmpDir + "ip.db";
                file = new File(dbPath);
                ClassPathResource classPathResource = new ClassPathResource("/ip2region/ip2region.db");
                FileUtils.copyInputStreamToFile(classPathResource.getStream(), file);
            }
            int algorithm = DbSearcher.BTREE_ALGORITHM;
            DbConfig config = new DbConfig();
            DbSearcher searcher = new DbSearcher(config, file.getPath());
            Method method;
            switch (algorithm) {
                case DbSearcher.BTREE_ALGORITHM:
                    method = searcher.getClass().getMethod("btreeSearch", String.class);
                    break;
                case DbSearcher.BINARY_ALGORITHM:
                    method = searcher.getClass().getMethod("binarySearch", String.class);
                    break;
                default:
                    method = searcher.getClass().getMethod("memorySearch", String.class);
                    break;
            }
            DataBlock dataBlock;
            if (!Util.isIpAddress(ip)) {
                log.error("Error: Invalid ip address");
            }
            dataBlock = (DataBlock) method.invoke(searcher, ip);
            return StringUtils.replace(dataBlock.getRegion(),"|0","");
        } catch (Exception e) {
            log.error("获取地址信息异常：{}", e);
        }
        return "";
    }
}

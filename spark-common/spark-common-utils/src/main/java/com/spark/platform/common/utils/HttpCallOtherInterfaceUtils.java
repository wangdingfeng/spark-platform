package com.spark.platform.common.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Map;

/**
 * @author wangdingfeng
 */
@Slf4j
public class HttpCallOtherInterfaceUtils {
    /**
     * 服务间调用
     * @param jsonParam 参数
     * @param gatewayUrl 网关地址
     * @param postUrl 请求地址
     * @return
     */
    public static JSONObject callOtherPostInterface(JSONObject jsonParam, String gatewayUrl, String postUrl) {
        HttpClient client = HttpClients.createDefault();
        // 要调用的接口方法
        String url = gatewayUrl + postUrl;
        HttpPost post = new HttpPost(url);
        JSONObject jsonObject = null;
        try {
            StringEntity s = new StringEntity(jsonParam.toString(), "UTF-8");
            //s.setContentEncoding("UTF-8");//此处测试发现不能单独设置字符串实体的编码，否则出错！应该在创建对象时创建
            s.setContentType("application/json");
            post.setEntity(s);
            post.addHeader("content-type", "application/json;charset=UTF-8");
            HttpResponse res = client.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 返回json格式：
                jsonObject = JSONObject.parseObject(EntityUtils.toString(res.getEntity()));
            }
        } catch (Exception e) {
            log.error("服务间接口POST请求调用出错！",e);
        }
        return jsonObject;
    }
    /**
     * 服务间调用
     * @param gatewayUrl 网关地址
     * @param getUrl 请求地址
     * @return
     */
    public static JSONObject callOtherInterface(String gatewayUrl, String getUrl) {
        HttpClient client = HttpClients.createDefault();
        // 要调用的接口方法
        String url = gatewayUrl + getUrl;
        HttpPost get = new HttpPost(url);
        JSONObject jsonObject = null;
        try {
            HttpResponse res = client.execute(get);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 返回json格式：
                jsonObject = JSONObject.parseObject(EntityUtils.toString(res.getEntity()));
            }
        } catch (Exception e) {
            log.error("服务间接口GET请求调用出错！",e);
        }
        return jsonObject;
    }

    /**
     * 发送 get请求
     * @param url
     * @return
     */
    public static JSONObject getUrl(String url) {
        HttpClient client = HttpClients.createDefault();
        // 要调用的接口方法
        HttpGet get = new HttpGet(url);
        JSONObject jsonObject = null;
        try {
            HttpResponse res = client.execute(get);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 返回json格式：
                jsonObject = JSONObject.parseObject(EntityUtils.toString(res.getEntity()));
            }
        } catch (Exception e) {
            log.error("get请求方法失败", e);
        }
        return jsonObject;
    }

    /**
     * 发送post请求
     * @param url
     * @param headers
     * @param param
     * @return
     */
    public static String postUrl(String url, Map<String,String> headers, Map param){
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        try {
            StringEntity s = new StringEntity(JSONObject.toJSONString(param), "UTF-8");
            post.setEntity(s);
            if(null != headers){
                headers.forEach((v,k)->{
                    post.setHeader(v,k);
                });
            }
            HttpResponse res = client.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 返回json格式：
                return EntityUtils.toString(res.getEntity());
            }
        } catch (Exception e) {
            log.error("POST请求调用出错！",e);
        }
        return null;
    }


    public static String callOtherGetInterface(String gatewayUrl, String getUrl) {
        HttpClient client = HttpClients.createDefault();
        // 要调用的接口方法
        String url = gatewayUrl + getUrl;
        HttpGet get = new HttpGet(url);
        JSONObject jsonObject = null;
        try {
            HttpResponse res = client.execute(get);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 返回json格式：
                jsonObject = JSONObject.parseObject(EntityUtils.toString(res.getEntity()));
            }
        } catch (Exception e) {
            System.out.println("服务间接口调用出错！");
            e.printStackTrace();
        }
        return null == jsonObject ? "" : jsonObject.toString();
    }
}

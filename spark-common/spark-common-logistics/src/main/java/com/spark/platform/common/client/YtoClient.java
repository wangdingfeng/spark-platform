package com.spark.platform.common.client;

import com.alibaba.fastjson.JSON;
import com.spark.platform.common.config.YtoConfig;
import com.spark.platform.common.constants.YtoConstants;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: wangdingfeng
 * @description: 圆通快递
 * @date: 2021/1/15 14:20
 */
@Slf4j
public class YtoClient {

    private final YtoConfig ytoConfig;

    public YtoClient(YtoConfig ytoConfig){
        this.ytoConfig = ytoConfig;
    }

    /**
     * 执行
     * @param apiType 请求接口
     * @param params 参数
     * @return
     */
    @SneakyThrows
    public String execute(String apiType, Object params){
        String apiUrl = ytoConfig.getApiUrl() + apiType +"/v1/"+ ytoConfig.getUserId();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String timestamp = dateFormat.format(new Date());
        String format = "JSON";
        String text = "app_key" + ytoConfig.getAppKey() + "format" + format + "method" + YtoConstants.REQUEST_METHOD_WAYBILLTRACE + "timestamp" + timestamp + "user_id" + ytoConfig.getUserId() + "v" + ytoConfig.getV();
        String sign = DigestUtils.md5Hex(ytoConfig.getSecretKey() + text).toUpperCase();
        //打开连接
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type",YtoConstants.CONTENT_TYPE);
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
        StringBuilder query = new StringBuilder();
        query.append("sign=").append(sign).append("&app_key=").append(ytoConfig.getAppKey()).append("&format=").append(format)
                .append("&method=").append(YtoConstants.REQUEST_METHOD_WAYBILLTRACE).append("&timestamp=").append(timestamp).append("&user_id=").append(ytoConfig.getUserId())
                .append("&v=").append(ytoConfig.getV()).append("&param=").append(JSON.toJSONString(params));
        log.info("【圆通】请求的参数信息：{}",query.toString());
        out.write(query.toString());
        out.flush();
        out.close();
        //获取服务端的反馈
        String responseString = "";
        String strLine = "";
        InputStream in = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        while ((strLine = reader.readLine()) != null) {
            responseString += strLine + "\n";
        }
        in.close();
        log.info("【圆通】请求的返回信息：{}",responseString);
        return responseString;
    }

}

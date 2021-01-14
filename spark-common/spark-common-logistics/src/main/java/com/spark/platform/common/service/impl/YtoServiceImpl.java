package com.spark.platform.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.spark.platform.common.config.LogisticsConfig;
import com.spark.platform.common.config.YtoConfig;
import com.spark.platform.common.constants.YtoConstants;
import com.spark.platform.common.enums.YtoWaybillNoStatus;
import com.spark.platform.common.model.LogisticsResponse;
import com.spark.platform.common.model.WaybillNoModel;
import com.spark.platform.common.service.YtoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.service.impl
 * @ClassName: YtoServiceImpl
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2021/1/14 9:25
 * @Version: 1.0
 */
@Service
@AllArgsConstructor
@Slf4j
public class YtoServiceImpl implements YtoService {

    private final LogisticsConfig logisticsConfig;

    @Override
    public LogisticsResponse<List<WaybillNoModel>> findWaybillNo(String billNo) {
        JSONArray params = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Number",billNo);
        params.add(jsonObject);
        String result = post( "waybill_query/v1/",params);
        Object json = JSON.parse(result);
        LogisticsResponse response = new LogisticsResponse<List<WaybillNoModel>>();
        response.setSuccess(true);
        if(json instanceof JSONArray){
            // 查询出来结果 返回是 数组
            List<Map> array = JSONArray.parseArray(result,Map.class);
            List<WaybillNoModel> list = Lists.newArrayList();
            array.stream().forEach(map -> {
                WaybillNoModel waybillNoModel = new WaybillNoModel();
                waybillNoModel.setWaybillNo(map.get("waybill_No").toString());
                waybillNoModel.setUploadTime(map.get("upload_Time").toString());
                waybillNoModel.setStatus(YtoWaybillNoStatus.statusOf(map.get("infoContent").toString()));
                waybillNoModel.setContent(map.get("processInfo").toString());
                list.add(waybillNoModel);
            });
            response.setObject(list);
        }else {
            Map map = (Map)json;
            if(!Boolean.valueOf(map.get("success").toString())){
                response.setSuccess(false);
            }
            response.setCode(Integer.parseInt(map.get("code").toString()));
            response.setMessage(map.get("message").toString());
        }
        return response;
    }

    /**
     * 获取报文头
     * @return
     */
    private String post(String urlType, JSONArray params){
        YtoConfig ytoConfig = logisticsConfig.getYto();
        String apiUrl = ytoConfig.getApiUrl() + urlType + ytoConfig.getUserId();
        try{
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
                    .append("&v=").append(ytoConfig.getV()).append("&param=").append(params.toJSONString());
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
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

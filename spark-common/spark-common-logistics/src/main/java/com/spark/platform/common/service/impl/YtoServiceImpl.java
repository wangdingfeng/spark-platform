package com.spark.platform.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.spark.platform.common.client.YtoClient;
import com.spark.platform.common.config.LogisticsConfig;
import com.spark.platform.common.enums.YtoWaybillNoStatus;
import com.spark.platform.common.model.LogisticsResponse;
import com.spark.platform.common.model.WaybillNoModel;
import com.spark.platform.common.service.YtoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
        YtoClient ytoClient = new YtoClient(logisticsConfig.getYto());
        String result = ytoClient.execute("waybill_query", Lists.newArrayList(ImmutableMap.of("Number", billNo)));
        Object json = JSON.parse(result);
        LogisticsResponse response = new LogisticsResponse<List<WaybillNoModel>>();
        response.setSuccess(true);
        if (json instanceof JSONArray) {
            // 查询出来结果 返回是 数组
            List<Map> array = JSONArray.parseArray(result, Map.class);
            List<WaybillNoModel> list = Lists.newArrayList();
            array.stream().forEach(map -> {
                WaybillNoModel waybillNoModel = WaybillNoModel.builder().waybillNo((String)map.get("waybill_No")).uploadTime((String)map.get("upload_Time"))
                        .status(YtoWaybillNoStatus.statusOf((String)map.get("infoContent"))).content((String)map.get("processInfo")).build();
                list.add(waybillNoModel);
            });
            response.setObject(list);
        } else {
            Map map = (Map) json;
            if (!Boolean.valueOf(map.get("success").toString())) {
                response.setSuccess(false);
            }
            response.setCode(Integer.parseInt(map.get("code").toString()));
            response.setMessage(map.get("message").toString());
        }
        return response;
    }
}

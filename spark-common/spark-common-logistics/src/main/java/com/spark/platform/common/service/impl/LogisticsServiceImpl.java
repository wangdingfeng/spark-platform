package com.spark.platform.common.service.impl;

import com.spark.platform.common.model.LogisticsResponse;
import com.spark.platform.common.model.WaybillNoModel;
import com.spark.platform.common.service.LogisticsService;
import com.spark.platform.common.service.YtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2021/1/14 15:01
 */
@Service
@RequiredArgsConstructor
public class LogisticsServiceImpl implements LogisticsService {

    private final YtoService ytoService;

    @Override
    public LogisticsResponse<List<WaybillNoModel>> findWaybillNo(String LogisticsType, String billNo) {
        if("YTO".equals(LogisticsType)){
            return ytoService.findWaybillNo(billNo);
        }
        return null;
    }
}

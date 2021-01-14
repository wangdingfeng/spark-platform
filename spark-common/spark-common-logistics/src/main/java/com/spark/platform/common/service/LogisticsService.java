package com.spark.platform.common.service;

import com.spark.platform.common.model.LogisticsResponse;
import com.spark.platform.common.model.WaybillNoModel;

import java.util.List;

/**
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2021/1/14 15:01
 */
public interface LogisticsService {

    /**
     *  查询物流轨迹
     * @param LogisticsType 快递公司
     * @param billNo 单号
     * @return
     */
    LogisticsResponse<List<WaybillNoModel>> findWaybillNo(String  LogisticsType, String billNo);

}

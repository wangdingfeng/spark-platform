package com.spark.platform.common.service;

import com.spark.platform.common.model.LogisticsResponse;
import com.spark.platform.common.model.WaybillNoModel;

import java.util.List;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.service
 * @ClassName: YtoService
 * @Author: wangdingfeng
 * @Description: 圆通查询接口
 * @Date: 2021/1/14 9:24
 * @Version: 1.0
 */
public interface YtoService {
    /**
     * 查询物流轨迹
     * @param billNo
     * @return
     */
    LogisticsResponse<List<WaybillNoModel>> findWaybillNo(String billNo);
}

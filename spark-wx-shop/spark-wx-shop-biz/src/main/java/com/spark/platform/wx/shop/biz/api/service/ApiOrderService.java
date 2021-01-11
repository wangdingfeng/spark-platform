package com.spark.platform.wx.shop.biz.api.service;

import com.spark.platform.wx.shop.api.dto.SubmitOrderDTO;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.wx.shop.biz.api.service
 * @ClassName: ApiOrderService
 * @Author: wangdingfeng
 * @Description: 订单
 * @Date: 2020/12/23 15:30
 * @Version: 1.0
 */
public interface ApiOrderService {
    /**&
     * 提交订单
     * @param submitOrderDTO
     * @return
     */
    boolean submit(SubmitOrderDTO submitOrderDTO);
}

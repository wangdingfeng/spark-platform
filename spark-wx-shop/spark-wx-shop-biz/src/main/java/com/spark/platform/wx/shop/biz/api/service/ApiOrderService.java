package com.spark.platform.wx.shop.biz.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spark.platform.wx.shop.api.dto.OrderRefundDTO;
import com.spark.platform.wx.shop.api.dto.ShopOrderQueryDTO;
import com.spark.platform.wx.shop.api.dto.SubmitOrderDTO;
import com.spark.platform.wx.shop.api.vo.OrderCardVo;

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

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    boolean cancel(Integer orderId);

    /**
     * 确认收货
     * @param orderId
     * @return
     */
    boolean confirmSend(Integer orderId);

    /**
     * 分页查询订单卡片信息
     * @param queryDTO
     * @return
     */
    IPage<OrderCardVo> page(ShopOrderQueryDTO queryDTO);

    /**
     * 退款
     * @param refundDTO
     * @return
     */
    boolean refund(OrderRefundDTO refundDTO);
}

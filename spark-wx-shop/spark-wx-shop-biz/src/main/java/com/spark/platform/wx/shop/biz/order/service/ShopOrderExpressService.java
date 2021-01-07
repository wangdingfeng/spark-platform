package com.spark.platform.wx.shop.biz.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.wx.shop.api.entity.order.ShopOrderExpress;

import java.util.List;

/**
 * <p>
 * 订单物流信息表，发货时生成 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-21
 */
public interface ShopOrderExpressService extends IService<ShopOrderExpress> {
    /**
     * 通过订单ID 查询数据
     * @param orderId
     * @return
     */
    List<ShopOrderExpress> findOrderId(Integer orderId);
}

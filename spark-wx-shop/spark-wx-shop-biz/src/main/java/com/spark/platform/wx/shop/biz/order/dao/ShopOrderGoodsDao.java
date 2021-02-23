package com.spark.platform.wx.shop.biz.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spark.platform.wx.shop.api.entity.order.ShopOrderGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 下单商品详情 Mapper 接口
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-21
 */
public interface ShopOrderGoodsDao extends BaseMapper<ShopOrderGoods> {

    /**
     * 通过订单ID 查询商品信息
     * @param orderId
     * @return
     */
    List<ShopOrderGoods> findByOrderId(@Param("orderId") Integer orderId);

}

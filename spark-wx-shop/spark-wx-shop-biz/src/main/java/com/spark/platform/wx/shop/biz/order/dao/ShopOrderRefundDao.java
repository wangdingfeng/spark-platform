package com.spark.platform.wx.shop.biz.order.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.order.ShopOrderRefund;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 退款管理 Mapper 接口
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-07
 */
public interface ShopOrderRefundDao extends BaseMapper<ShopOrderRefund> {

    /**
     * 分页查询订单信息
     * @param page
     * @param wrapper
     * @return
     */
    IPage listPage(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 查询订单Id
     * @param id
     * @return
     */
    @Select("SELECT order_id FROM shop_order_refund WHERE id=#{id}")
    @ResultType(Integer.class)
    Integer findOrderId(@Param("id") Integer id);

}

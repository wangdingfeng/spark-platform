package com.spark.platform.wx.shop.biz.order.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.order.ShopOrder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单管理 Mapper 接口
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-21
 */
public interface ShopOrderDao extends BaseMapper<ShopOrder> {

    /**
     * 分页查询订单信息
     * @param page
     * @param wrapper
     * @return
     */
    IPage listPage(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 分页查询订单卡片信息
     * @param page
     * @param wrapper
     * @return
     */
    IPage cardPage(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 统计状态数量
     * @param orderType
     * @return
     */
    @Select("SELECT order_status,count(id) FROM shop_order WHERE order_type=#{orderType} GROUP BY order_status")
    @ResultType(Map.class)
    List<Map<Integer,Integer>> statusCount(@Param("orderType") String orderType);

}

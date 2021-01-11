package com.spark.platform.wx.shop.biz.order.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.order.ShopOrder;
import org.apache.ibatis.annotations.Param;

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

}

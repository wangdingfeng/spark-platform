package com.spark.platform.wx.shop.biz.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.wx.shop.api.dto.ShopOrderQueryDTO;
import com.spark.platform.wx.shop.api.entity.order.ShopOrder;

/**
 * <p>
 * 订单管理 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-21
 */
public interface ShopOrderService extends IService<ShopOrder> {

    /**
     * 查询订单
     * @param page
     * @param shopOrder
     * @return
     */
    IPage findPage(Page page, ShopOrder shopOrder);

    /**
     * 查询订单卡片
     * @param queryDTO
     * @return
     */
    IPage cardPage(ShopOrderQueryDTO queryDTO);


    /**
     * 获取订单详情
     * @param id
     * @return
     */
    ShopOrder getOrder(Integer id);

    /**
     * 保存订单数据
     * @param shopOrder
     * @return
     */
    boolean saveOrder(ShopOrder shopOrder);

    /**
     * 发货
     * @param id
     * @param shipperName 快递公司
     * @param shipperCode
     * @param logisticCode 快递单号
     * @return
     */
    boolean send(Integer id,String shipperName, String shipperCode, String logisticCode);

    /**
     * 取消订单
     * @param id
     * @return
     */
    boolean cancel(Integer id);
}

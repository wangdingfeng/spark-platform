package com.spark.platform.wx.shop.biz.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.order.ShopOrder;
import com.spark.platform.wx.shop.api.entity.user.ShopUser;
import com.spark.platform.wx.shop.biz.order.dao.ShopOrderDao;
import com.spark.platform.wx.shop.biz.order.service.ShopOrderExpressService;
import com.spark.platform.wx.shop.biz.order.service.ShopOrderGoodsService;
import com.spark.platform.wx.shop.biz.order.service.ShopOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spark.platform.wx.shop.biz.user.service.ShopUserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * <p>
 * 订单管理 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-21
 */
@Service
@RequiredArgsConstructor
public class ShopOrderServiceImpl extends ServiceImpl<ShopOrderDao, ShopOrder> implements ShopOrderService {

    private final ShopOrderGoodsService shopOrderGoodsService;
    private final ShopUserService shopUserService;
    private final ShopOrderExpressService orderExpressService;

    @Override
    public IPage findPage(Page page, ShopOrder shopOrder) {
        QueryWrapper wrapper = new QueryWrapper<ShopOrder>();
        wrapper.eq(null != shopOrder.getUserId(),"o.user_id",shopOrder.getUserId());
        wrapper.eq(StringUtils.isNotBlank(shopOrder.getOrderSn()),"o.order_sn",shopOrder.getOrderSn());
        wrapper.eq(null != shopOrder.getOrderStatus(),"o.order_status",shopOrder.getOrderStatus());
        wrapper.eq(StringUtils.isNotBlank(shopOrder.getOrderType()),"o.order_type",shopOrder.getOrderType());
        return super.baseMapper.listPage(page, wrapper);
    }

    @Override
    public ShopOrder getOrder(Integer id) {
        ShopOrder shopOrder = super.getById(id);
        Assert.notNull(shopOrder,"查询不到当前订单详情!");
        shopOrder.setGoodsList(shopOrderGoodsService.findByOrderId(id));
        shopOrder.setUser(shopUserService.getById(shopOrder.getId()));
        shopOrder.setExpressList(orderExpressService.findOrderId(id));
        return shopOrder;
    }
}

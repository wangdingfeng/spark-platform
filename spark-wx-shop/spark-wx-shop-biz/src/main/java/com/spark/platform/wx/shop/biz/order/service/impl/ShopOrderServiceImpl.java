package com.spark.platform.wx.shop.biz.order.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.order.ShopOrder;
import com.spark.platform.wx.shop.biz.order.dao.ShopOrderDao;
import com.spark.platform.wx.shop.biz.order.service.ShopOrderGoodsService;
import com.spark.platform.wx.shop.biz.order.service.ShopOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
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

    @Override
    public IPage findPage(Page page, ShopOrder shopOrder) {
        return super.baseMapper.listPage(page, Wrappers.query(shopOrder));
    }

    @Override
    public ShopOrder getOrder(Integer id) {
        ShopOrder shopOrder = super.getById(id);
        Assert.notNull(shopOrder,"查询不到当前订单详情!");
        shopOrder.setGoodsList(shopOrderGoodsService.findByOrderId(id));
        return shopOrder;
    }
}

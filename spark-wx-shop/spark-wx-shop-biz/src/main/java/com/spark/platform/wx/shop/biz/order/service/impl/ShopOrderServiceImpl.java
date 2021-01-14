package com.spark.platform.wx.shop.biz.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.dto.ShopOrderQueryDTO;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsSku;
import com.spark.platform.wx.shop.api.entity.order.ShopOrder;
import com.spark.platform.wx.shop.api.entity.order.ShopOrderExpress;
import com.spark.platform.wx.shop.api.entity.order.ShopOrderGoods;
import com.spark.platform.wx.shop.api.enums.ShippingStatusEnum;
import com.spark.platform.wx.shop.api.enums.ShopOrderStatusEnum;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsSkuService;
import com.spark.platform.wx.shop.biz.order.dao.ShopOrderDao;
import com.spark.platform.wx.shop.biz.order.service.ShopOrderExpressService;
import com.spark.platform.wx.shop.biz.order.service.ShopOrderGoodsService;
import com.spark.platform.wx.shop.biz.order.service.ShopOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spark.platform.wx.shop.biz.user.service.ShopUserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final ShopGoodsSkuService shopGoodsSkuService;

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
    public IPage cardPage(ShopOrderQueryDTO queryDTO) {
        QueryWrapper wrapper = new QueryWrapper<ShopOrder>();
        wrapper.eq(null != queryDTO.getUserId(),"o.user_id",queryDTO.getUserId());
        wrapper.eq(null != queryDTO.getOrderStatus(),"o.order_status",queryDTO.getOrderStatus());
        wrapper.like(null != queryDTO.getGoodsTitle(),"g.goods_title",queryDTO.getGoodsTitle());
        wrapper.orderByDesc("o.modify_date");
        return super.baseMapper.cardPage(new Page(queryDTO.getCurrent(),queryDTO.getSize()),wrapper);
    }

    @Override
    public ShopOrder getOrder(Integer id) {
        ShopOrder shopOrder = super.getById(id);
        Assert.notNull(shopOrder,"查询不到当前订单详情!");
        shopOrder.setGoodsList(shopOrderGoodsService.findByOrderId(id));
        shopOrder.setUser(shopUserService.getById(shopOrder.getUserId()));
        shopOrder.setExpressList(orderExpressService.findOrderId(id));
        return shopOrder;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean saveOrder(ShopOrder shopOrder) {
        boolean flag = super.save(shopOrder);
        List<ShopOrderGoods> shopOrderGoodsList = shopOrder.getGoodsList();
        shopOrderGoodsList.forEach(shopOrderGoods -> {
            shopOrderGoods.setOrderId(shopOrder.getId());
        });
        shopOrderGoodsService.saveBatch(shopOrderGoodsList);
        return flag;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean send(Integer id, String shipperName, String shipperCode, String logisticCode) {
        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setId(id);
        shopOrder.setOrderStatus(ShopOrderStatusEnum.SEND.getStatus());
        shopOrder.setShippingStatus(ShippingStatusEnum.SEND.getStatus());
        shopOrder.setShippingStatus(1);
        shopOrder.setSendTime(LocalDateTime.now());
        ShopOrderExpress orderExpress = new ShopOrderExpress();
        orderExpress.setOrderId(id);
        orderExpress.setShipperName(shipperName);
        orderExpress.setShipperCode(shipperCode);
        orderExpress.setLogisticCode(logisticCode);
        orderExpressService.saveSendData(orderExpress);
        return super.updateById(shopOrder);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean cancel(Integer id) {
        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setId(id);
        shopOrder.setOrderStatus(ShopOrderStatusEnum.CANCEL.getStatus());
        super.updateById(shopOrder);
        // 释放库存
        List<ShopOrderGoods> orderGoods = shopOrderGoodsService.findByOrderId(id);
        orderGoods.forEach(shopOrderGoods -> {
            ShopGoodsSku shopGoodsSku = shopGoodsSkuService.findByGoodsIdAndVals(shopOrderGoods.getGoodsId(),shopOrderGoods.getGoodsAttrValIds());
            if(null != shopGoodsSku){
                shopGoodsSkuService.addStock(shopGoodsSku.getId(),shopOrderGoods.getNumber());
            }
        });
        return false;
    }

    @Override
    public Map<Integer, Integer> statusCount(String orderType) {
        List<Map<Integer,Integer>> maps = super.baseMapper.statusCount(orderType);
        Map<Integer,Integer> resultMap = new HashMap<>(maps.size());
        maps.forEach(map -> {
            resultMap.put(map.get("order_status"),map.get("count(id)"));
        });
        return resultMap;
    }
}

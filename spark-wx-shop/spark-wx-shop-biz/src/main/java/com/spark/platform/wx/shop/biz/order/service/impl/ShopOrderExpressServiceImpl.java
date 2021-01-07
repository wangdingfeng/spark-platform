package com.spark.platform.wx.shop.biz.order.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.spark.platform.wx.shop.api.entity.order.ShopOrderExpress;
import com.spark.platform.wx.shop.biz.order.dao.ShopOrderExpressDao;
import com.spark.platform.wx.shop.biz.order.service.ShopOrderExpressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 订单物流信息表，发货时生成 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-21
 */
@Service
public class ShopOrderExpressServiceImpl extends ServiceImpl<ShopOrderExpressDao, ShopOrderExpress> implements ShopOrderExpressService {

    @Override
    public List<ShopOrderExpress> findOrderId(Integer orderId) {
        return super.list(Wrappers.<ShopOrderExpress>lambdaQuery().eq(ShopOrderExpress::getOrderId,orderId));
    }
}

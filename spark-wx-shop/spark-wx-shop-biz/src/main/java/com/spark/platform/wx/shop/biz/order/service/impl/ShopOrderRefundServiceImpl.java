package com.spark.platform.wx.shop.biz.order.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.order.ShopOrder;
import com.spark.platform.wx.shop.api.entity.order.ShopOrderRefund;
import com.spark.platform.wx.shop.api.enums.RefundStatusEnum;
import com.spark.platform.wx.shop.biz.order.dao.ShopOrderRefundDao;
import com.spark.platform.wx.shop.biz.order.service.ShopOrderRefundService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spark.platform.wx.shop.biz.order.service.ShopOrderService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

/**
 * <p>
 * 退款管理 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-07
 */
@Service
@RequiredArgsConstructor
public class ShopOrderRefundServiceImpl extends ServiceImpl<ShopOrderRefundDao, ShopOrderRefund> implements ShopOrderRefundService {

    private final ShopOrderService shopOrderService;

    @Override
    public IPage findPage(Page page, ShopOrderRefund shopOrderRefund) {
        QueryWrapper wrapper = new QueryWrapper<ShopOrderRefund>();
        wrapper.like(StringUtils.isNotBlank(shopOrderRefund.getOrderSn()),"r.order_sn",shopOrderRefund.getOrderSn());
        wrapper.like(StringUtils.isNotBlank(shopOrderRefund.getRefundSn()),"r.refund_sn",shopOrderRefund.getOrderSn());
        wrapper.eq(null != shopOrderRefund.getOrderId(),"r.order_id",shopOrderRefund.getOrderId());
        wrapper.eq(null != shopOrderRefund.getRefundStatus(),"r.refundStatus",shopOrderRefund.getRefundStatus());
        wrapper.orderByDesc("r.modify_date");
        return super.baseMapper.listPage(page,wrapper);
    }

    @Override
    public boolean launchRefund(ShopOrderRefund orderRefund) {
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        orderRefund.setRefundSn(String.valueOf(snowflake.nextId()));
        orderRefund.setRefundStatus(RefundStatusEnum.REQUEST.getStatus());
        return super.save(orderRefund);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean refund(Integer id, boolean isAgree, String refusedReason) {
        ShopOrderRefund shopOrderRefund = new ShopOrderRefund();
        shopOrderRefund.setId(id);
        if(isAgree){
            shopOrderRefund.setRefundStatus(RefundStatusEnum.COMPLETE.getStatus());
            shopOrderRefund.setRefundTime(LocalDateTime.now());
            // 调用退款接口
        }else {
            Assert.notNull(refusedReason,"请填写拒绝退货理由！");
            shopOrderRefund.setRefundStatus(RefundStatusEnum.REFUSED.getStatus());
            shopOrderRefund.setRefusedReason(refusedReason);
        }
        // 更新订单信息
        Integer orderId = super.baseMapper.findOrderId(id);
        ShopOrder order = new ShopOrder();
        order.setId(orderId);
        order.setRefundStatus(shopOrderRefund.getRefundStatus());
        shopOrderService.updateById(order);
        return super.updateById(shopOrderRefund);
    }
}

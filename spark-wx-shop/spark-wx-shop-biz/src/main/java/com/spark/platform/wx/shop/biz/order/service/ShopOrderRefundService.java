package com.spark.platform.wx.shop.biz.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.wx.shop.api.entity.order.ShopOrderRefund;

/**
 * <p>
 * 退款管理 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-07
 */
public interface ShopOrderRefundService extends IService<ShopOrderRefund> {


    /**
     * 查询订单
     * @param page
     * @param shopOrderRefund
     * @return
     */
    IPage findPage(Page page, ShopOrderRefund shopOrderRefund);

    /**
     * 发起退款流程
     * @param orderRefund
     * @return
     */
    boolean launchRefund(ShopOrderRefund orderRefund);

    /**
     * 退款
     * @param id
     * @param isAgree 是否同意退款
     * @param refusedReason 退款原因
     * @return
     */
    boolean refund(Integer id, boolean isAgree, String refusedReason);
}

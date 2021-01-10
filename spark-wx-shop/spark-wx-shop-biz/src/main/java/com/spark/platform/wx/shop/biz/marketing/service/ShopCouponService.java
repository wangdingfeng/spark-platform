package com.spark.platform.wx.shop.biz.marketing.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.wx.shop.api.entity.marketing.ShopCoupon;
import com.spark.platform.wx.shop.api.vo.CouponCardVo;

import java.util.List;

/**
 * <p>
 * 优惠券 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-03
 */
public interface ShopCouponService extends IService<ShopCoupon> {
    /**
     * 查询可用的优惠券
     * @return
     */
    List<CouponCardVo> findUseVo();
}

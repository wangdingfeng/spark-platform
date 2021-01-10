package com.spark.platform.wx.shop.biz.marketing.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.wx.shop.api.entity.marketing.ShopCouponUser;
import com.spark.platform.wx.shop.api.vo.CouponCardVo;

import java.util.List;

/**
 * <p>
 * 优惠券发放记录 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-04
 */
public interface ShopCouponUserService extends IService<ShopCouponUser> {

    /**
     * 分页查询
     * @param page
     * @param shopCouponUser
     * @return
     */
    IPage findPage(Page page, ShopCouponUser shopCouponUser);

    /**
     * 领取优惠券
     * @param userId
     * @return
     */
    boolean receiveCoupon(Integer userId, Integer couponId);

    /**
     * 查询用户历史优惠券
     * @param page
     * @param userId
     * @return
     */
    IPage pageUser(Page page, Integer userId, boolean isUse);

    /**
     * 刷新过期优惠券信息
     */
    void taskOverdue();
}

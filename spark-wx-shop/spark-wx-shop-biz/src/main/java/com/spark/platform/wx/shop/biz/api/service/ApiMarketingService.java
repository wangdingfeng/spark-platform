package com.spark.platform.wx.shop.biz.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.vo.*;

import java.util.List;

/**
 * @author: wangdingfeng
 * @Date: 2021/1/9 13:52
 * @Description: 营销service
 */
public interface ApiMarketingService {
    /**
     * 查询优惠券
     * @param limit 限制数量
     * @return
     */
    List<CouponCardVo> findCoupon(Integer limit);

    /**
     * 查询用户优惠券
     *
     * @param size
     * @param current
     * @param userId
     * @return
     */
    IPage pageCouponUser(Long size, Long current, Integer userId, boolean isUse);

    /**
     * 查询秒杀列表
     *
     * @return
     */
    List<SeckillVo> findSeckill();

    /**
     * 查询秒杀商品列表
     *
     * @param size
     * @param current
     * @return
     */
    IPage<GoodsSecKillCardVo> secKillGoods(Long size, Long current, Integer secKillId);

    /**
     * 查询团购商品列表
     *
     * @param size
     * @param current
     * @return
     */
    IPage<PinkGoodsCardVo> pinkGoods(Long size, Long current);

    /**
     * 领取优惠券
     * @param userId 用户信息
     * @param couponId 优惠券信息
     * @return
     */
    boolean receiveCoupon(Integer userId, Integer couponId);

}

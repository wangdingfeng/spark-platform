package com.spark.platform.wx.shop.biz.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.common.base.enums.DelFlagEnum;
import com.spark.platform.wx.shop.api.entity.marketing.ShopSeckill;
import com.spark.platform.wx.shop.api.enums.ShopGoodsStatusEnum;
import com.spark.platform.wx.shop.api.vo.CouponCardVo;
import com.spark.platform.wx.shop.api.vo.GoodsSecKillCardVo;
import com.spark.platform.wx.shop.api.vo.PinkGoodsCardVo;
import com.spark.platform.wx.shop.api.vo.SeckillVo;
import com.spark.platform.wx.shop.biz.api.service.ApiMarketingService;
import com.spark.platform.wx.shop.biz.marketing.dao.ShopPinkGoodsDao;
import com.spark.platform.wx.shop.biz.marketing.dao.ShopSeckillGoodsDao;
import com.spark.platform.wx.shop.biz.marketing.service.ShopCouponService;
import com.spark.platform.wx.shop.biz.marketing.service.ShopCouponUserService;
import com.spark.platform.wx.shop.biz.marketing.service.ShopSeckillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * @author: wangdingfeng
 * @Date: 2021/1/9 13:52
 * @Description: 营销中心 实现类
 */
@Service
@RequiredArgsConstructor
public class ApiMarketingServiceImpl implements ApiMarketingService {

    private final ShopCouponService couponService;
    private final ShopCouponUserService shopCouponUserService;
    private final ShopSeckillService shopSeckillService;
    private final ShopSeckillGoodsDao shopSeckillGoodsDao;
    private final ShopPinkGoodsDao shopPinkGoodsDao;

    @Override
    public List<CouponCardVo> findCoupon(Integer limit) {
        return couponService.findUseVo(limit);
    }

    @Override
    public IPage pageCouponUser(Long size, Long current, Integer userId, boolean isUse) {
        return shopCouponUserService.pageUser(new Page(size, current), userId, isUse);
    }

    @Override
    public List<SeckillVo> findSeckill() {
        return shopSeckillService.findVoByStatus(true);
    }

    @Override
    public IPage<GoodsSecKillCardVo> secKillGoods(Long size, Long current, Integer secKillId) {
        ShopSeckill shopSeckill = shopSeckillService.getById(secKillId);
        LocalDateTime start = LocalDateTime.of(LocalDate.now(), shopSeckill.getStartTime());
        LocalDateTime end = LocalDateTime.of(LocalDate.now(), shopSeckill.getEndTime());
        Assert.notNull(shopSeckill, "当前秒杀列表不存在");
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("g.status", ShopGoodsStatusEnum.PUBLISH.getStatus());
        wrapper.eq("sg.del_flag", DelFlagEnum.NORMAL.getValue());
        wrapper.ge("sg.start_time", start);
        wrapper.le("sg.end_time", end);
        wrapper.orderByDesc("sg.sales");
        return shopSeckillGoodsDao.pageGoods(new Page(current, size), wrapper);
    }

    @Override
    public IPage<PinkGoodsCardVo> pinkGoods(Long size, Long current) {
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("pg.del_flag", DelFlagEnum.NORMAL.getValue());
        wrapper.eq("g.status", ShopGoodsStatusEnum.PUBLISH.getStatus());
        wrapper.gt("pg.start_time",LocalDateTime.of(LocalDate.now(), LocalTime.MIN));
        wrapper.lt("pg.end_time", LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
        return shopPinkGoodsDao.pageGoods(new Page(current, size), wrapper);
    }

    @Override
    public boolean receiveCoupon(Integer userId, Integer couponId) {
        return shopCouponUserService.receiveCoupon(userId,couponId);
    }
}

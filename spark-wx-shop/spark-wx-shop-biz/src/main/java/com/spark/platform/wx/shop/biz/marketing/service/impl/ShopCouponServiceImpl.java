package com.spark.platform.wx.shop.biz.marketing.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spark.platform.wx.shop.api.entity.marketing.ShopCoupon;
import com.spark.platform.wx.shop.api.vo.CouponCardVo;
import com.spark.platform.wx.shop.biz.marketing.dao.ShopCouponDao;
import com.spark.platform.wx.shop.biz.marketing.service.ShopCouponService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 优惠券 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-03
 */
@Service
public class ShopCouponServiceImpl extends ServiceImpl<ShopCouponDao, ShopCoupon> implements ShopCouponService {

    @Override
    public List<CouponCardVo> findUseVo() {
        return super.baseMapper.findUseVo();
    }
}

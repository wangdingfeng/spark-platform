package com.spark.platform.wx.shop.biz.marketing.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.common.base.exception.BusinessException;
import com.spark.platform.wx.shop.api.entity.marketing.ShopCoupon;
import com.spark.platform.wx.shop.api.entity.marketing.ShopCouponUser;
import com.spark.platform.wx.shop.api.enums.CouponUserStatusEnum;
import com.spark.platform.wx.shop.biz.marketing.dao.ShopCouponDao;
import com.spark.platform.wx.shop.biz.marketing.dao.ShopCouponUserDao;
import com.spark.platform.wx.shop.biz.marketing.service.ShopCouponUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 优惠券发放记录 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-04
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ShopCouponUserServiceImpl extends ServiceImpl<ShopCouponUserDao, ShopCouponUser> implements ShopCouponUserService {

    private final ShopCouponDao shopCouponDao;

    @Override
    public IPage findPage(Page page, ShopCouponUser shopCouponUser) {
        QueryWrapper wrapper = new QueryWrapper<ShopCouponUser>();
        wrapper.eq(null != shopCouponUser.getUserId(), "u.user_id", shopCouponUser.getUserId());
        wrapper.eq(null != shopCouponUser.getCouponId(), "u.coupon_id", shopCouponUser.getCouponId());
        wrapper.orderByDesc("u.modify_date");
        return this.listPage(page, wrapper);
    }

    /**
     * 分页基础方法
     *
     * @param page
     * @param wrapper
     * @return
     */
    private IPage listPage(Page page, Wrapper wrapper) {
        return super.baseMapper.pageUserCoupon(page, wrapper);
    }

    @Override
    public boolean receiveCoupon(Integer userId, Integer couponId) {
        ShopCoupon coupon = shopCouponDao.selectById(couponId);
        Assert.notNull(coupon, "查询不到当前优惠券信息，请重新刷新数据!");
        if (coupon.getEndTime().compareTo(LocalDateTime.now()) == -1) {
            throw new BusinessException("已超过当前优惠券领取时间！");
        }
        if (coupon.getIsLimited() && coupon.getTotal() >= coupon.getLastTotal()) {
            throw new BusinessException("当前优惠券领取超过最大值！");
        }
        log.info("【领取优惠券】,用户:{},优惠券：{}", userId, couponId);
        ShopCouponUser couponUser = new ShopCouponUser();
        couponUser.setUserId(userId);
        couponUser.setCouponId(couponId);
        couponUser.setEndTime(coupon.getEndTime());
        couponUser.setStatus(CouponUserStatusEnum.NO_USE.getStatus());
        return super.save(couponUser);
    }

    @Override
    public IPage pageUser(Page page, Integer userId, boolean isUse) {
        Assert.notNull(userId, "请输入要查询的用户！");
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("u.user_id", userId);
        if (!isUse) {
            // 查询未使用的优惠券
            wrapper.eq("u.status", CouponUserStatusEnum.NO_USE.getStatus());
            wrapper.lt("u.end_time", LocalDateTime.now());
        } else {
            // 查询未使用的优惠券
            wrapper.ne("u.status", CouponUserStatusEnum.NO_USE.getStatus());
            wrapper.gt("u.end_time", LocalDateTime.now());
        }
        return this.listPage(page, wrapper);
    }

    @Override
    public ShopCouponUser findById(Integer id) {
        return super.baseMapper.findById(id);
    }

    @Override
    public void taskOverdue() {
        // 查询未使用的 超过最后时间的优惠券 自动作废
        List<ShopCouponUser> couponUsers = super.list(Wrappers.<ShopCouponUser>lambdaQuery()
                .eq(ShopCouponUser::getStatus, CouponUserStatusEnum.NO_USE.getStatus()).gt(ShopCouponUser::getEndTime, LocalDateTime.now()));
        couponUsers.forEach(shopCouponUser -> {
            shopCouponUser.setStatus(CouponUserStatusEnum.OVERDUE.getStatus());
        });
        super.updateBatchById(couponUsers);
    }
}

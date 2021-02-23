package com.spark.platform.wx.shop.biz.marketing.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.marketing.ShopCouponUser;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 * 优惠券发放记录 Mapper 接口
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-04
 */
public interface ShopCouponUserDao extends BaseMapper<ShopCouponUser> {
    /**
     * 查询用户优惠券
     * @param wrapper
     * @param page
     * @return
     */
    IPage pageUserCoupon(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     *  获取用户优惠券信息
     * @param id
     * @return
     */
    ShopCouponUser findById(@Param("id") Integer id);

}

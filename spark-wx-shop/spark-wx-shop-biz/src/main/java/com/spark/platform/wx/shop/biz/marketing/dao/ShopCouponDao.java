package com.spark.platform.wx.shop.biz.marketing.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spark.platform.wx.shop.api.entity.marketing.ShopCoupon;
import com.spark.platform.wx.shop.api.vo.CouponCardVo;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 优惠券 Mapper 接口
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-03
 */
public interface ShopCouponDao extends BaseMapper<ShopCoupon> {

    /**
     * 查询当前可用的优惠券
     * @return
     */
    @Select("select * from shop_coupon where del_flag='0' and end_time < NOW()")
    @ResultType(CouponCardVo.class)
    List<CouponCardVo> findUseVo();


}

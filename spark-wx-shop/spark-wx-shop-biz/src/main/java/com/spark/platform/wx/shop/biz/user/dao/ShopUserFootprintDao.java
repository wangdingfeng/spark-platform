package com.spark.platform.wx.shop.biz.user.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.user.ShopUserFootprint;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户浏览足迹 Mapper 接口
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-10
 */
public interface ShopUserFootprintDao extends BaseMapper<ShopUserFootprint> {

    /**
     * 分页
     * @param page
     * @param wrapper
     * @return
     */
    IPage listPage(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 获取当前是否有足迹信息
     * @param userId
     * @param goodsId
     * @return
     */
    @Select("SELECT count(id) FROM shop_user_footprint WHERE user_id=#{userId} AND goods_id=#{goodsId} AND TO_DAYS(create_date) = TO_DAYS(NOW())")
    @ResultType(Integer.class)
    Integer findToday(@Param("userId") Integer userId,@Param("goodsId") Integer goodsId);

}

package com.spark.platform.wx.shop.biz.setting.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.setting.ShopSettingSwiper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spark.platform.wx.shop.api.vo.SwiperVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 首页轮播图 Mapper 接口
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-03
 */
public interface ShopSettingSwiperDao extends BaseMapper<ShopSettingSwiper> {

    /**
     * 分页
     * @param page
     * @param wrapper
     * @return
     */
    IPage listPage(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 查询轮播图
     * @param limit
     * @return
     */
    List<SwiperVo> limitSwiperVo(@Param("limit") Integer limit);

}

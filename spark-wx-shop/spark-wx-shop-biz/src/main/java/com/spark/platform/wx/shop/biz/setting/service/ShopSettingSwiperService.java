package com.spark.platform.wx.shop.biz.setting.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.setting.ShopSettingSwiper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.wx.shop.api.vo.SwiperVo;

import java.util.List;

/**
 * <p>
 * 首页轮播图 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-03
 */
public interface ShopSettingSwiperService extends IService<ShopSettingSwiper> {
    /**
     * 分页
     * @param page
     * @param shopSettingSwiper
     * @return
     */
    IPage finPage(Page page,ShopSettingSwiper shopSettingSwiper);

    /**
     * 查询轮播图
     * @param limit
     * @return
     */
    List<SwiperVo> limitSwiperVo(Integer limit);
}

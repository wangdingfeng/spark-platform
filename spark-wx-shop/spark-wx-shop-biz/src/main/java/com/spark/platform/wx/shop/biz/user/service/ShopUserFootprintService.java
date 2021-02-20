package com.spark.platform.wx.shop.biz.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.wx.shop.api.entity.user.ShopUserFootprint;

/**
 * <p>
 * 用户浏览足迹 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-10
 */
public interface ShopUserFootprintService extends IService<ShopUserFootprint> {

    /**
     * 分页
     * @param page
     * @param shopUserFootprint
     * @return
     */
    IPage listPage(Page page, ShopUserFootprint shopUserFootprint);

    /**
     * 计算总数
     * @param userId
     * @return
     */
    int count(Integer userId);

    /**
     * 保存用户足迹
     * @param userId
     * @param goodsId
     * @return
     */
    void saveFootprint(Integer userId, Integer goodsId);
}

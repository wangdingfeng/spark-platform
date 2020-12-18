package com.spark.platform.wx.shop.biz.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.wx.shop.api.entity.user.ShopUserCollect;

/**
 * <p>
 * 用户收藏 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-10
 */
public interface ShopUserCollectService extends IService<ShopUserCollect> {

    /**
     * 分页
     * @param page
     * @return
     */
    IPage listPage(Page page, ShopUserCollect shopUserCollect);
}

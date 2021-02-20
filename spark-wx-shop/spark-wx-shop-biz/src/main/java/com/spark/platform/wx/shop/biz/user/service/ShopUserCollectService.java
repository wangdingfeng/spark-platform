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
     * @param shopUserCollect
     * @return
     */
    IPage listPage(Page page, ShopUserCollect shopUserCollect);

    /**
     * 计算总数
     * @param userId
     * @return
     */
    int count(Integer userId);

    /**
     * 收藏
     * @param userId
     * @param goodsId
     * @return
     */
    boolean collect(Integer userId, Integer goodsId);

    /**
     * 查询是否有收藏
     * @param userId
     * @param goodsId
     * @return
     */
    boolean getCollect(Integer userId, Integer goodsId);


}

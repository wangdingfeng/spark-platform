package com.spark.platform.wx.shop.biz.goods.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsComment;

/**
 * <p>
 * 商品评价 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-18
 */
public interface ShopGoodsCommentService extends IService<ShopGoodsComment> {
    /**
     * 分页
     * @param page
     * @return
     */
    IPage listPage(Page page, ShopGoodsComment shopGoodsComment);
}

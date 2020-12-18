package com.spark.platform.wx.shop.biz.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsCategory;

import java.util.List;

/**
 * <p>
 * 商品分类 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-17
 */
public interface ShopGoodsCategoryService extends IService<ShopGoodsCategory> {
    /**
     * 获取树
     * @return
     */
    List<ShopGoodsCategory> tree(String name);
}

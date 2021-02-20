package com.spark.platform.wx.shop.biz.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsGallery;

import java.util.List;

/**
 * <p>
 * 商品主图 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-15
 */
public interface ShopGoodsGalleryService extends IService<ShopGoodsGallery> {
    /**
     * 保存or更新
     * @param goodsGalleryList
     * @param goodsIds
     */
    void insertOrUpdateBatch(List<ShopGoodsGallery> goodsGalleryList, Integer goodsIds);

    /**
     * 根据商品ID获取数据
     * @param goodsIds
     * @return
     */
    List<ShopGoodsGallery> findByGoodsId(Integer goodsIds);
}

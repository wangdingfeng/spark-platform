package com.spark.platform.wx.shop.biz.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsAttr;

import java.util.List;

/**
 * <p>
 * 商品属性 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-15
 */
public interface ShopGoodsAttrService extends IService<ShopGoodsAttr> {
    /**
     * 批量保存更新
     * @param shopGoodsAttrs
     * @param goodsId 商品主键
     */
    void insertOrUpdateBatch(List<ShopGoodsAttr> shopGoodsAttrs, Integer goodsId);

    /**
     * 根据商品ID 查询数据
     * @param goodsId
     * @return
     */
    List<ShopGoodsAttr> findByGoodsId(Integer goodsId);
}

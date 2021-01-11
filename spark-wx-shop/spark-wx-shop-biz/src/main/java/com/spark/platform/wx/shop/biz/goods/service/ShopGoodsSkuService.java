package com.spark.platform.wx.shop.biz.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsSku;

import java.util.List;

/**
 * <p>
 * 商品属性搭配 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-17
 */
public interface ShopGoodsSkuService extends IService<ShopGoodsSku> {
    /**
     * 插入更新
     * @param shopGoodsSkus
     * @param goodsId
     */
    void insertOrUpdate(List<ShopGoodsSku> shopGoodsSkus, Integer goodsId);

    /**
     * 通过商品ID查询数据
     * @param goodsId
     * @return
     */
    List<ShopGoodsSku> findByGoodsId(Integer goodsId);

    /**
     * 通过商品ID查询数据
     * @param goodsId
     * @param attrValIds
     * @return
     */
    ShopGoodsSku findByGoodsIdAndVals(Integer goodsId, String attrValIds);

    /**
     * 扣减库存
     * @param id
     * @param num
     * @return
     */
    boolean subStock(Integer id, Integer num);

    /**
     * 增加库存
     * @param id
     * @param num
     */
    void addStock(Integer id, Integer num);
}

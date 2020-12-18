package com.spark.platform.wx.shop.biz.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsParam;

import java.util.List;

/**
 * <p>
 * 商品产品参数 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-18
 */
public interface ShopGoodsParamService extends IService<ShopGoodsParam> {
    /**
     * 保存更新
     * @param goodsId
     * @param list
     * @param delIds
     */
    void insertOrUpdate(Integer goodsId,List<ShopGoodsParam> list, List<Integer> delIds);

    /**
     * 通过商品ID 查询
     * @param goodsId
     * @return
     */
    List<ShopGoodsParam> getByGoodIds(Integer goodsId);
}

package com.spark.platform.wx.shop.biz.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoods;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-15
 */
public interface ShopGoodsService extends IService<ShopGoods> {
    /**
     * 保存商品信息
     * @param shopGoods
     */
    void saveShopGoods(ShopGoods shopGoods);
    /**
     * 更新商品信息
     * @param shopGoods
     */
    void updateShopGoods(ShopGoods shopGoods);

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    ShopGoods getShopGoods(Integer id);

}

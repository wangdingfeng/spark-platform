package com.spark.platform.wx.shop.biz.goods.service.impl;

import com.spark.platform.wx.shop.api.entity.goods.ShopGoods;
import com.spark.platform.wx.shop.biz.goods.dao.ShopGoodsDao;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsAttrService;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsSkuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-15
 */
@Service
@RequiredArgsConstructor
public class ShopGoodsServiceImpl extends ServiceImpl<ShopGoodsDao, ShopGoods> implements ShopGoodsService {

    private final ShopGoodsAttrService shopGoodsAttrService;
    private final ShopGoodsSkuService shopGoodsSkuService;

    @Override
    public void saveShopGoods(ShopGoods shopGoods) {
        super.saveOrUpdate(shopGoods);
        // 保存商品属性信息
        shopGoodsAttrService.insertOrUpdateBatch(shopGoods.getShopGoodsAttrs(),shopGoods.getId());
        // 保存库存信息
        shopGoodsSkuService.insertOrUpdate(shopGoods.getShopGoodsSkus(),shopGoods.getId());
    }

    @Override
    public void updateShopGoods(ShopGoods shopGoods) {
        super.save(shopGoods);
    }

    @Override
    public ShopGoods getShopGoods(Integer id) {
       ShopGoods shopGoods = super.getById(id);
        Assert.notNull(shopGoods,"查询不到当前商品信息！");
        shopGoods.setShopGoodsAttrs(shopGoodsAttrService.findByGoodsId(id));
        shopGoods.setShopGoodsSkus(shopGoodsSkuService.findByGoodsId(id));
        return shopGoods;
    }
}

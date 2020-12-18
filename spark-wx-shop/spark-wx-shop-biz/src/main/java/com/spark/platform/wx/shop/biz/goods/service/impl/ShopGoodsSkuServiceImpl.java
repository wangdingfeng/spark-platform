package com.spark.platform.wx.shop.biz.goods.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsSku;
import com.spark.platform.wx.shop.biz.goods.dao.ShopGoodsSkuDao;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsSkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品属性搭配 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-17
 */
@Service
public class ShopGoodsSkuServiceImpl extends ServiceImpl<ShopGoodsSkuDao, ShopGoodsSku> implements ShopGoodsSkuService {

    @Override
    public void insertOrUpdate(List<ShopGoodsSku> shopGoodsSkus, Integer goodsId) {
        // 删除库存数据
        super.remove(Wrappers.<ShopGoodsSku>lambdaQuery().eq(ShopGoodsSku::getGoodsId,goodsId));
        shopGoodsSkus.forEach(s->{
            s.setGoodsId(goodsId);
        });
        super.saveBatch(shopGoodsSkus);
    }

    @Override
    public List<ShopGoodsSku> findByGoodsId(Integer goodsId) {
        return super.list(Wrappers.<ShopGoodsSku>lambdaQuery().eq(ShopGoodsSku::getGoodsId,goodsId));
    }
}

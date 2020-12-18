package com.spark.platform.wx.shop.biz.goods.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsAttr;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsAttrVal;
import com.spark.platform.wx.shop.biz.goods.dao.ShopGoodsAttrDao;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsAttrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsAttrValService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品属性 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-15
 */
@Service
@RequiredArgsConstructor
public class ShopGoodsAttrServiceImpl extends ServiceImpl<ShopGoodsAttrDao, ShopGoodsAttr> implements ShopGoodsAttrService {

    private final ShopGoodsAttrValService shopGoodsAttrValService;


    @Override
    @Transactional(readOnly = false)
    public void insertOrUpdateBatch(List<ShopGoodsAttr> shopGoodsAttrs, Integer goodsId) {
        // 删除原有属性
        super.remove(Wrappers.<ShopGoodsAttr>lambdaQuery().eq(ShopGoodsAttr::getGoodsId,goodsId));
        shopGoodsAttrValService.remove(Wrappers.<ShopGoodsAttrVal>lambdaQuery().eq(ShopGoodsAttrVal::getGoodsId,goodsId));
        shopGoodsAttrs.forEach(a -> {
            a.setGoodsId(goodsId);
            a.getAttrValList().forEach( b -> {
                b.setGoodsId(goodsId);
                b.setAttrId(a.getAttrId());
            });
            // 更新属性值
            shopGoodsAttrValService.saveBatch(a.getAttrValList());
        });
        super.saveBatch(shopGoodsAttrs);

    }

    @Override
    public List<ShopGoodsAttr> findByGoodsId(Integer goodsId) {
        List<ShopGoodsAttr> shopGoodsAttrs = super.list(Wrappers.<ShopGoodsAttr>lambdaQuery().eq(ShopGoodsAttr::getGoodsId,goodsId));
        shopGoodsAttrs.forEach(a->{
            a.setAttrValList(shopGoodsAttrValService.list(Wrappers.<ShopGoodsAttrVal>lambdaQuery()
                    .eq(ShopGoodsAttrVal::getGoodsId,goodsId).eq(ShopGoodsAttrVal::getAttrId,a.getAttrId())));
        });
        return shopGoodsAttrs;
    }
}

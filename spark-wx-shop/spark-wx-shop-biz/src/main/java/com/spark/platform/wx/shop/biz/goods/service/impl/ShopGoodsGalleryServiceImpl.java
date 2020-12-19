package com.spark.platform.wx.shop.biz.goods.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsGallery;
import com.spark.platform.wx.shop.biz.goods.dao.ShopGoodsGalleryDao;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsGalleryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 * 商品主图 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-15
 */
@Service
public class ShopGoodsGalleryServiceImpl extends ServiceImpl<ShopGoodsGalleryDao, ShopGoodsGallery> implements ShopGoodsGalleryService {

    @Override
    public void insertOrUpdateBatch(List<ShopGoodsGallery> goodsGalleryList, Integer goodsIds) {
        // 查询原始的
        List<Integer> sourceIds = super.baseMapper.getIdsByGoods(goodsIds);
        List<Integer> ids = new ArrayList<>(goodsGalleryList.size());
        goodsGalleryList.forEach(shopGoodsGallery -> {
            shopGoodsGallery.setGoodsId(goodsIds);
            if(null != shopGoodsGallery.getId()){
                ids.add(shopGoodsGallery.getId());
            }
        });
        super.saveOrUpdateBatch(goodsGalleryList);
        if(CollUtil.isNotEmpty(ids)){
            // 获取要删除的id
            List<Integer> removeIds = sourceIds.stream().filter(item -> !ids.contains(item)).collect(toList());
            if(CollUtil.isNotEmpty(removeIds)){
                super.removeByIds(removeIds);
            }
        }

    }

    @Override
    public List<ShopGoodsGallery> findByGoodsId(Integer goodsIds) {
        return super.list(Wrappers.<ShopGoodsGallery>lambdaQuery().eq(ShopGoodsGallery::getGoodsId,goodsIds));
    }
}

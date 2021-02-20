package com.spark.platform.wx.shop.biz.goods.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsParam;
import com.spark.platform.wx.shop.biz.goods.dao.ShopGoodsParamDao;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsParamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品产品参数 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-18
 */
@Service
public class ShopGoodsParamServiceImpl extends ServiceImpl<ShopGoodsParamDao, ShopGoodsParam> implements ShopGoodsParamService {

    @Override
    public void insertOrUpdate(Integer goodsId, List<ShopGoodsParam> list, List<Integer> delIds) {
        if(CollUtil.isEmpty(list)){
            return;
        }
        list.forEach(shopGoodsParam -> {
            shopGoodsParam.setGoodsId(goodsId);
        });
        super.saveOrUpdateBatch(list);
        if(CollUtil.isNotEmpty(delIds)){
            super.removeByIds(delIds);
        }
    }

    @Override
    public List<ShopGoodsParam> getByGoodIds(Integer goodsId) {
        return super.list(Wrappers.<ShopGoodsParam>lambdaQuery().eq(ShopGoodsParam::getGoodsId,goodsId));
    }
}

package com.spark.platform.wx.shop.biz.specs.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.spark.platform.common.base.constants.RedisConstants;
import com.spark.platform.wx.shop.api.entity.specs.ShopSpecsAttr;
import com.spark.platform.wx.shop.api.entity.specs.ShopSpecsAttrVal;
import com.spark.platform.wx.shop.biz.specs.dao.ShopSpecsAttrDao;
import com.spark.platform.wx.shop.biz.specs.service.ShopSpecsAttrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spark.platform.wx.shop.biz.specs.service.ShopSpecsAttrValService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品规格属性 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-16
 */
@Service
@RequiredArgsConstructor
public class ShopSpecsAttrServiceImpl extends ServiceImpl<ShopSpecsAttrDao, ShopSpecsAttr> implements ShopSpecsAttrService {

    private final ShopSpecsAttrValService shopSpecsAttrValService;

    @Override
    @CacheEvict(value = RedisConstants.SHOP_ATTR_CACHE, key = "#entity.attrId")
    public boolean saveOrUpdate(ShopSpecsAttr entity) {
        boolean flag = super.saveOrUpdate(entity);
        List<ShopSpecsAttrVal> vals = entity.getAttrVals();
        vals.forEach(v -> {
            v.setAttrId(entity.getAttrId());
        });
        shopSpecsAttrValService.saveOrUpdateBatch(vals);
        return flag;
    }

    @Override
    @Cacheable(value = RedisConstants.SHOP_ATTR_CACHE, unless = "#result == null", key = "#id")
    public ShopSpecsAttr getShopSpecsAttr(Long id) {
        ShopSpecsAttr shopSpecsAttr = super.getById(id);
        shopSpecsAttr.setAttrVals(shopSpecsAttrValService.list(Wrappers.<ShopSpecsAttrVal>lambdaQuery()
                .eq(ShopSpecsAttrVal::getAttrId,id).orderByAsc(ShopSpecsAttrVal::getSort)));
        return shopSpecsAttr;
    }
}

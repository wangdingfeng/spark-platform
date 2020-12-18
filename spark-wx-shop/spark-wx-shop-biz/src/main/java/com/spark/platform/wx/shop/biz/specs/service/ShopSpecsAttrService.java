package com.spark.platform.wx.shop.biz.specs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.wx.shop.api.entity.specs.ShopSpecsAttr;

/**
 * <p>
 * 商品规格属性 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-16
 */
public interface ShopSpecsAttrService extends IService<ShopSpecsAttr> {
    /**
     * 通过ID获取详情
     * @param id
     * @return
     */
    ShopSpecsAttr getShopSpecsAttr(Long id);
}

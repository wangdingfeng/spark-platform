package com.spark.platform.wx.shop.biz.goods.service.impl;

import com.spark.platform.admin.api.entity.user.User;
import com.spark.platform.common.security.model.LoginUser;
import com.spark.platform.common.security.util.UserUtils;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoods;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsSku;
import com.spark.platform.wx.shop.api.enums.ShopGoodsStatusEnums;
import com.spark.platform.wx.shop.biz.goods.dao.ShopGoodsDao;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsAttrService;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsGalleryService;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsParamService;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsSkuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-15
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ShopGoodsServiceImpl extends ServiceImpl<ShopGoodsDao, ShopGoods> implements ShopGoodsService {

    private final ShopGoodsAttrService shopGoodsAttrService;
    private final ShopGoodsSkuService shopGoodsSkuService;
    private final ShopGoodsParamService shopGoodsParamService;
    private final ShopGoodsGalleryService shopGoodsGalleryService;

    @Override
    @Transactional(readOnly = false)
    public void saveShopGoods(ShopGoods shopGoods) {
        if (ShopGoodsStatusEnums.PUBLISH.equals(shopGoods.getStatus())) {
            // 如果是上架状态保存上架信息
            LoginUser user = UserUtils.getLoginUser();
            log.info("[商品：{}]，操作上架人：{}", shopGoods.getTitle(), user);
            shopGoods.setPublisher(user.getUsername());
            shopGoods.setPublishTime(LocalDateTime.now());
        }
        // 保存库存信息
        Integer stock = shopGoods.getShopGoodsSkus().stream().mapToInt(ShopGoodsSku::getStock).sum();
        log.info("[商品：{}]，当前上架的库存：{}", shopGoods.getTitle(), stock);
        shopGoods.setStock(stock);
        // 获取最大单价和最小单价
        BigDecimal minPrice = shopGoods.getShopGoodsSkus().stream().map(ShopGoodsSku::getPrice).min(Comparator.naturalOrder()).get();
        BigDecimal maxPrice = shopGoods.getShopGoodsSkus().stream().map(ShopGoodsSku::getPrice).max(Comparator.naturalOrder()).get();
        shopGoods.setMinPrice(minPrice);
        if(minPrice.compareTo(maxPrice) == -1){
            shopGoods.setMaxPrice(maxPrice);
        }else{
            shopGoods.setMaxPrice(BigDecimal.ZERO);
        }
        super.saveOrUpdate(shopGoods);
        // 保存商品主图
        shopGoodsGalleryService.insertOrUpdateBatch(shopGoods.getShopGoodsGalleries(),shopGoods.getId());
        // 保存商品属性信息
        shopGoodsAttrService.insertOrUpdateBatch(shopGoods.getShopGoodsAttrs(), shopGoods.getId());
        // 保存库存信息
        shopGoodsSkuService.insertOrUpdate(shopGoods.getShopGoodsSkus(), shopGoods.getId());
        // 保存产品参数信息
        shopGoodsParamService.insertOrUpdate(shopGoods.getId(), shopGoods.getShopGoodsParams(), shopGoods.getDelParamIds());
    }

    @Override
    public void updateShopGoods(ShopGoods shopGoods) {
        super.save(shopGoods);
    }

    @Override
    public ShopGoods getShopGoods(Integer id) {
        ShopGoods shopGoods = super.getById(id);
        Assert.notNull(shopGoods, "查询不到当前商品信息！");
        shopGoods.setShopGoodsGalleries(shopGoodsGalleryService.findByGoodsId(id));
        shopGoods.setShopGoodsAttrs(shopGoodsAttrService.findByGoodsId(id));
        shopGoods.setShopGoodsSkus(shopGoodsSkuService.findByGoodsId(id));
        shopGoods.setShopGoodsParams(shopGoodsParamService.getByGoodIds(id));
        return shopGoods;
    }

    @Override
    public boolean updateStatus(Integer id, String status) {
        ShopGoods shopGoods = new ShopGoods();
        shopGoods.setId(id);
        shopGoods.setStatus(status);
        if(ShopGoodsStatusEnums.PUBLISH.getStatus().equals(status)){
            LoginUser user = UserUtils.getLoginUser();
            shopGoods.setPublisher(user.getUsername());
            shopGoods.setPublishTime(LocalDateTime.now());
        }
        boolean flag = super.updateById(shopGoods);
        log.info("[商品ID:{}],操作商品{}", shopGoods.getModifier(), ShopGoodsStatusEnums.statusOf(status).getDesc());
        return flag;
    }
}

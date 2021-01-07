package com.spark.platform.wx.shop.biz.marketing.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.common.base.enums.DelFlagEnum;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoods;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsSku;
import com.spark.platform.wx.shop.api.entity.marketing.ShopPinkGoods;
import com.spark.platform.wx.shop.api.enums.ShopGoodsActivityEnum;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsService;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsSkuService;
import com.spark.platform.wx.shop.biz.marketing.dao.ShopPinkGoodsDao;
import com.spark.platform.wx.shop.biz.marketing.service.ShopPinkGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * <p>
 * 拼团产品 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-07
 */
@Service
@RequiredArgsConstructor
public class ShopPinkGoodsServiceImpl extends ServiceImpl<ShopPinkGoodsDao, ShopPinkGoods> implements ShopPinkGoodsService {

    private final ShopGoodsService shopGoodsService;
    private final ShopGoodsSkuService shopGoodsSkuService;

    @Override
    public IPage findPage(Page page, ShopPinkGoods shopPinkGoods) {
        QueryWrapper wrapper = new QueryWrapper<ShopPinkGoods>();
        wrapper.eq("s.del_flag", DelFlagEnum.normal.getValue());
        wrapper.like(StringUtils.isNotBlank(shopPinkGoods.getGoodsTitle()),"g.title",shopPinkGoods.getGoodsTitle());
        wrapper.gt(null != shopPinkGoods.getStartTime(),"p.start_time",shopPinkGoods.getStartTime());
        wrapper.lt(null != shopPinkGoods.getEndTime(),"p.end_time",shopPinkGoods.getEndTime());
        wrapper.orderByDesc("s.create_time");
        return super.baseMapper.listPage(page, wrapper);
    }

    @Override
    public boolean deleteGoods(Integer id) {
        boolean flag = super.removeById(id);
        if(flag){
            // 修改常产品活动状态
            super.baseMapper.updateGoodsActivity(id);
        }
        return flag;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean saveOrUpdate(ShopPinkGoods entity) {
        // 获取最小价格
        BigDecimal minPrice = entity.getGoodsSkus().stream().map(ShopGoodsSku::getActivityPrice).min(Comparator.naturalOrder()).get();
        entity.setPrice(minPrice);
        Boolean flag = super.saveOrUpdate(entity);
        // 商品的活动状态
        ShopGoods goods = new ShopGoods();
        goods.setId(entity.getGoodsId());
        goods.setActivity(ShopGoodsActivityEnum.PINK.getStatus());
        shopGoodsService.updateById(goods);
        // 更新 活动价格
        if(CollUtil.isNotEmpty(entity.getGoodsSkus())){
            shopGoodsSkuService.updateBatchById(entity.getGoodsSkus());
        }
        return flag;
    }
}

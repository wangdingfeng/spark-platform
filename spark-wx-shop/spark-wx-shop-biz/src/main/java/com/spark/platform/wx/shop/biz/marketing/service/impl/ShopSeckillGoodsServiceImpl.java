package com.spark.platform.wx.shop.biz.marketing.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.common.base.enums.DelFlagEnum;
import com.spark.platform.common.base.exception.BusinessException;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoods;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsSku;
import com.spark.platform.wx.shop.api.entity.marketing.ShopSeckillGoods;
import com.spark.platform.wx.shop.api.enums.ShopGoodsActivityEnum;
import com.spark.platform.wx.shop.api.vo.SeckillGoodsVo;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsService;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsSkuService;
import com.spark.platform.wx.shop.biz.marketing.dao.ShopSeckillGoodsDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spark.platform.wx.shop.biz.marketing.service.ShopSeckillGoodsService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * <p>
 * 商品秒杀配置列表 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-05
 */
@Service
@RequiredArgsConstructor
public class ShopSeckillGoodsServiceImpl extends ServiceImpl<ShopSeckillGoodsDao, ShopSeckillGoods> implements ShopSeckillGoodsService {

    private final ShopGoodsService shopGoodsService;
    private final ShopGoodsSkuService shopGoodsSkuService;

    @Override
    public IPage findPage(Page page, ShopSeckillGoods shopSeckillGoods) {
        QueryWrapper wrapper = new QueryWrapper<ShopSeckillGoods>();
        wrapper.eq("s.del_flag", DelFlagEnum.NORMAL.getValue());
        wrapper.like(StringUtils.isNotBlank(shopSeckillGoods.getGoodsTitle()),"g.title",shopSeckillGoods.getGoodsTitle());
        wrapper.gt(null != shopSeckillGoods.getStartTime(),"s.start_time",shopSeckillGoods.getStartTime());
        wrapper.lt(null != shopSeckillGoods.getEndTime(),"s.end_time",shopSeckillGoods.getEndTime());
        wrapper.orderByDesc("s.create_time");
        return super.baseMapper.listPage(page,wrapper);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean deleteGoods(Integer id) {
        boolean flag = super.removeById(id);
        if(flag){
            // 修改常产品活动状态
            super.baseMapper.updateGoodsActivity(id);
        }
        return flag;
    }

    @Override
    public SeckillGoodsVo getByGoodIds(Integer goodsId) {
        return super.baseMapper.findByGoodsId(goodsId);
    }

    @Override
    public void addDay() {
        super.baseMapper.addDay();
    }

    @Override
    @Transactional(readOnly = false)
    public boolean saveOrUpdate(ShopSeckillGoods entity) {
        int count = shopGoodsService.count(Wrappers.<ShopGoods>lambdaQuery().eq(ShopGoods::getId,entity.getGoodsId()).eq(ShopGoods::getStatus,ShopGoodsActivityEnum.NORMAL.getStatus()));
        if(count > 0){
            throw new BusinessException("当前商品已在活动状态，不允许添加！");
        }
        // 获取最小价格
        BigDecimal minPrice = entity.getGoodsSkus().stream().map(ShopGoodsSku::getActivityPrice).min(Comparator.naturalOrder()).get();
        entity.setPrice(minPrice);
        Boolean flag = super.saveOrUpdate(entity);
        // 商品的活动状态
        ShopGoods goods = new ShopGoods();
        goods.setId(entity.getGoodsId());
        goods.setActivity(ShopGoodsActivityEnum.SECKILL.getStatus());
        shopGoodsService.updateById(goods);
        // 更新 活动价格
        if(CollUtil.isNotEmpty(entity.getGoodsSkus())){
            shopGoodsSkuService.updateBatchById(entity.getGoodsSkus());
        }
        return flag;
    }
}

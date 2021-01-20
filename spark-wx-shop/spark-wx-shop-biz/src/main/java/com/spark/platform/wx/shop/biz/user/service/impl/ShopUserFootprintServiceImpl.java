package com.spark.platform.wx.shop.biz.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.common.base.enums.DelFlagEnum;
import com.spark.platform.wx.shop.api.entity.user.ShopUserCollect;
import com.spark.platform.wx.shop.api.entity.user.ShopUserFootprint;
import com.spark.platform.wx.shop.biz.user.dao.ShopUserFootprintDao;
import com.spark.platform.wx.shop.biz.user.service.ShopUserFootprintService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户浏览足迹 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-10
 */
@Service
public class ShopUserFootprintServiceImpl extends ServiceImpl<ShopUserFootprintDao, ShopUserFootprint> implements ShopUserFootprintService {

    @Override
    public IPage listPage(Page page, ShopUserFootprint shopUserFootprint) {
        QueryWrapper queryWrapper = new QueryWrapper<ShopUserCollect>();;
        queryWrapper.like(StringUtils.isNotBlank(shopUserFootprint.getGoodsTitle()),"g.title",shopUserFootprint.getGoodsTitle())
                .eq(true,"f.del_flag", DelFlagEnum.NORMAL.getValue())
                .eq(null != shopUserFootprint.getGoodsId(),"f.goods_id",shopUserFootprint.getGoodsId())
                .eq(null != shopUserFootprint.getUserId(),"f.user_id",shopUserFootprint.getUserId())
                .orderByDesc("f.create_date");
        return super.baseMapper.listPage(page, queryWrapper);
    }

    @Override
    @Async
    public boolean saveFootprint(Integer userId, Integer goodsId) {
        int count = super.baseMapper.findToday(userId,goodsId);
        if(count > 0){
            return true;
        }
        ShopUserFootprint shopUserFootprint = new ShopUserFootprint();
        shopUserFootprint.setUserId(userId);
        shopUserFootprint.setGoodsId(goodsId);
        return super.save(shopUserFootprint);
    }
}

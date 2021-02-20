package com.spark.platform.wx.shop.biz.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.common.base.enums.DelFlagEnum;
import com.spark.platform.wx.shop.api.entity.user.ShopUserCollect;
import com.spark.platform.wx.shop.biz.user.dao.ShopUserCollectDao;
import com.spark.platform.wx.shop.biz.user.service.ShopUserCollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户收藏 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-10
 */
@Service
public class ShopUserCollectServiceImpl extends ServiceImpl<ShopUserCollectDao, ShopUserCollect> implements ShopUserCollectService {

    @Override
    public IPage listPage(Page page, ShopUserCollect shopUserCollect) {
        QueryWrapper queryWrapper = new QueryWrapper<ShopUserCollect>();
        queryWrapper.like(StringUtils.isNotBlank(shopUserCollect.getGoodsTitle()),"g.title",shopUserCollect.getGoodsTitle())
                .eq(true,"c.del_flag", DelFlagEnum.NORMAL.getValue())
                .eq(null != shopUserCollect.getGoodsId(),"c.goods_id",shopUserCollect.getGoodsId())
                .eq(null != shopUserCollect.getUserId(),"c.user_id",shopUserCollect.getUserId())
                .orderByDesc("c.create_date");
        return super.baseMapper.listPage(page, queryWrapper);
    }

    @Override
    public int count(Integer userId) {
        return super.count(Wrappers.<ShopUserCollect>lambdaQuery().eq(ShopUserCollect::getUserId,userId));
    }

    @Override
    public boolean collect(Integer userId, Integer goodsId) {
        boolean flag = this.getCollect(userId,goodsId);
        if(flag){
            // 如果当前购车已经存在 则执行删除操作
            super.baseMapper.delete(Wrappers.<ShopUserCollect>lambdaQuery().eq(ShopUserCollect::getGoodsId,goodsId).eq(ShopUserCollect::getUserId,userId));
            return false;
        }
        ShopUserCollect userCollect = new ShopUserCollect();
        userCollect.setUserId(userId);
        userCollect.setGoodsId(goodsId);
        return super.save(userCollect);
    }

    @Override
    public boolean getCollect(Integer userId, Integer goodsId) {
        int count = super.count(Wrappers.<ShopUserCollect>lambdaQuery().eq(ShopUserCollect::getGoodsId,goodsId).eq(ShopUserCollect::getUserId,userId));
        return count > 0;
    }
}

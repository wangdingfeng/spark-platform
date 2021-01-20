package com.spark.platform.wx.shop.biz.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
}

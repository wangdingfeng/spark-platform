package com.spark.platform.wx.shop.biz.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.common.base.support.WrapperSupport;
import com.spark.platform.wx.shop.api.entity.user.ShopUserCart;
import com.spark.platform.wx.shop.biz.user.dao.ShopUserCartDao;
import com.spark.platform.wx.shop.biz.user.service.ShopUserCartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 会员购物车 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-18
 */
@Service
public class ShopUserCartServiceImpl extends ServiceImpl<ShopUserCartDao, ShopUserCart> implements ShopUserCartService {

    @Override
    public IPage listPage(Page page, ShopUserCart shopUserCart) {
        QueryWrapper queryWrapper = new QueryWrapper<ShopUserCart>();
        WrapperSupport.putParamsLike(queryWrapper,"g",shopUserCart,"goodsSn");
        WrapperSupport.putParamsEqual(queryWrapper,"c",shopUserCart,"userId");
        queryWrapper.orderByDesc("create_date");
        return super.baseMapper.listPage(page,queryWrapper);
    }

    @Override
    public List<ShopUserCart> findCart(Integer userId) {
        return super.list(Wrappers.<ShopUserCart>lambdaQuery().eq(ShopUserCart::getUserId,userId).orderByDesc(ShopUserCart::getCreateDate));
    }
}

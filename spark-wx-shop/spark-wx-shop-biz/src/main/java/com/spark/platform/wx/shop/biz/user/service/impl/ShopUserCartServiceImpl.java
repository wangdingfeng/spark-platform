package com.spark.platform.wx.shop.biz.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.common.base.exception.BusinessException;
import com.spark.platform.common.base.support.WrapperSupport;
import com.spark.platform.wx.shop.api.entity.user.ShopUserCart;
import com.spark.platform.wx.shop.biz.user.dao.ShopUserCartDao;
import com.spark.platform.wx.shop.biz.user.service.ShopUserCartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

    @Override
    public boolean saveCart(ShopUserCart shopUserCart) {
        log.info("【保存当前的购物车】，用户:{},商品:{},规格:{}",shopUserCart.getUserId(),shopUserCart.getGoodsId(),shopUserCart.getAttrVals());
        // 查询的当前用户购物车中是否存在 相同的产品 规格
        Integer id = super.baseMapper.findSameId(shopUserCart.getUserId(),shopUserCart.getGoodsId(),shopUserCart.getAttrValIds());
       if(null != id){
           // 如果当前存在相同产品相同规格的 则在原有数量的基础上+1
          super.baseMapper.updateNum(id);
          log.info("【更新当前购物车】！");
          return true;
       }
       // 不存在则保存数据
        return super.save(shopUserCart);
    }

    @Override
    public boolean deleteCart(Integer userId, Integer id) {
        log.info("【购物车信息=>删除】,用户:{}",userId);
        int count = super.count(Wrappers.<ShopUserCart>lambdaQuery().eq(ShopUserCart::getUserId,userId).eq(ShopUserCart::getId,id));
        if(count == 0){
            throw new BusinessException("只允许删除自己的购物车信息！");
        }
        return false;
    }
}

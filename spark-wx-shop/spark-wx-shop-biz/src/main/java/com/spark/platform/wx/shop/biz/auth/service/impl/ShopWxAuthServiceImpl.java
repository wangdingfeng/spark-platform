package com.spark.platform.wx.shop.biz.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.spark.platform.common.base.exception.BusinessException;
import com.spark.platform.wx.shop.api.entity.auth.ShopWxAuth;
import com.spark.platform.wx.shop.biz.auth.dao.ShopWxAuthDao;
import com.spark.platform.wx.shop.biz.auth.service.ShopWxAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 微信小程序授权信息 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-14
 */
@Service
public class ShopWxAuthServiceImpl extends ServiceImpl<ShopWxAuthDao, ShopWxAuth> implements ShopWxAuthService {

    @Override
    public boolean saveOrUpdate(ShopWxAuth entity) {
        this.validAppId(entity.getAppId(),entity.getId());
        return super.saveOrUpdate(entity);
    }

    private void validAppId(String appId,Integer id){
        LambdaQueryWrapper<ShopWxAuth> queryWrapper = Wrappers.lambdaQuery();
        if (null != id) {
            queryWrapper.ne(ShopWxAuth::getId, id);
        }
        queryWrapper.eq(ShopWxAuth::getAppId, StringUtils.trim(appId));
        if (0 != super.count(queryWrapper)) {
            throw new BusinessException("appId不允许重复");
        }
    }

    @Override
    public ShopWxAuth getByAppId(String appId) {
        return super.getOne(Wrappers.<ShopWxAuth>lambdaQuery().eq(ShopWxAuth::getAppId,appId));
    }
}

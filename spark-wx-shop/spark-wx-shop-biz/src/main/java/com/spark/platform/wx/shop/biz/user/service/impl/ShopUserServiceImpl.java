package com.spark.platform.wx.shop.biz.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.spark.platform.wx.shop.api.entity.user.ShopUser;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spark.platform.wx.shop.biz.user.dao.ShopUserDao;
import com.spark.platform.wx.shop.biz.user.service.ShopUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * shop会员管理 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-10
 */
@Service
public class ShopUserServiceImpl extends ServiceImpl<ShopUserDao, ShopUser> implements ShopUserService {

    @Override
    public boolean updateStatus(Integer id, Integer status) {
        ShopUser shopUser = new ShopUser();
        shopUser.setId(id);
        shopUser.setStatus(status);
        return super.updateById(shopUser);
    }

    @Override
    public ShopUser getByOpenId(String openId) {
        return super.getOne(Wrappers.<ShopUser>lambdaQuery().eq(ShopUser::getWxOpenid,openId));
    }
}

package com.spark.platform.wx.shop.biz.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spark.platform.wx.shop.api.entity.user.ShopUserAddress;
import com.spark.platform.wx.shop.biz.user.dao.ShopUserAddressDao;
import com.spark.platform.wx.shop.biz.user.service.ShopUserAddressService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 收货地址表 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-10
 */
@Service
public class ShopUserAddressServiceImpl extends ServiceImpl<ShopUserAddressDao, ShopUserAddress> implements ShopUserAddressService {

    @Override
    public List<ShopUserAddress> findAddress(Integer userId) {
        return super.list(Wrappers.<ShopUserAddress>lambdaQuery()
                .eq(ShopUserAddress::getUserId,userId).orderByDesc(ShopUserAddress::getUpdateDate));
    }
}

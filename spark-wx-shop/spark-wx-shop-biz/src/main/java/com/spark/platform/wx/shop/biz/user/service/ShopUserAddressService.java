package com.spark.platform.wx.shop.biz.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.wx.shop.api.entity.user.ShopUserAddress;

import java.util.List;

/**
 * <p>
 * 收货地址表 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-10
 */
public interface ShopUserAddressService extends IService<ShopUserAddress> {
    /**
     * 查询用户的地址信息
     * @param userId
     * @return
     */
    List<ShopUserAddress> findAddress(Integer userId);

    /**
     * 删除用户地址
     * @param userId
     * @param id
     * @return
     */
    boolean deleteAddress(Integer userId, Integer id);
}

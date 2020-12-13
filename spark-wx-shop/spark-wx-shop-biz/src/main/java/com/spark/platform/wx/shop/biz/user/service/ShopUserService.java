package com.spark.platform.wx.shop.biz.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.wx.shop.api.entity.user.ShopUser;

/**
 * <p>
 * shop会员管理 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-10
 */
public interface ShopUserService extends IService<ShopUser> {

    /**
     * 修改状态 0 可用 1 禁用
     * @param id
     * @return
     */
    boolean updateStatus(Integer id, Integer status);

    /**
     * 根据openId 获取用户信息
     * @param openId
     * @return
     */
    ShopUser getByOpenId(String openId);
}

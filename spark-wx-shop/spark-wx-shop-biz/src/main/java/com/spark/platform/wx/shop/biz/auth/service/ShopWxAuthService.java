package com.spark.platform.wx.shop.biz.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.wx.shop.api.entity.auth.ShopWxAuth;

/**
 * <p>
 * 微信小程序授权信息 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-14
 */
public interface ShopWxAuthService extends IService<ShopWxAuth> {
    /**
     * 通过appId查询数据
     * @param appId
     * @return
     */
    ShopWxAuth getByAppId(String appId);
}

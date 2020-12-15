package com.spark.platform.wx.shop.biz.api.service;

import com.spark.platform.wx.shop.api.dto.WxLoginDTO;
import com.spark.platform.wx.shop.api.entity.user.ShopUser;

/**
 * @author: wangdingfeng
 * @Date: 2020/12/13 20:48
 * @Description: 用户信息
 */
public interface ApiUserService {
    /**
     * 登录
     * @param loginDTO
     * @return
     */
    ShopUser login(WxLoginDTO loginDTO);

}

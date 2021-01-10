package com.spark.platform.wx.shop.biz.api.service;

import com.spark.platform.wx.shop.api.dto.WxLoginDTO;
import com.spark.platform.wx.shop.api.entity.user.*;
import io.swagger.models.auth.In;

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

    /**
     * 保存用户手机号
     * @param userId 用户
     * @param mobile 手机号
     * @return
     */
    boolean saveMobile(Integer userId, String mobile);
}

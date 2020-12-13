package com.spark.platform.wx.shop.biz.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.spark.platform.common.base.exception.BusinessException;
import com.spark.platform.common.config.properties.SparkWxProperties;
import com.spark.platform.common.utils.HttpCallOtherInterfaceUtils;
import com.spark.platform.wx.shop.api.dto.WxLoginDTO;
import com.spark.platform.wx.shop.api.entity.user.ShopUser;
import com.spark.platform.wx.shop.biz.api.service.ApiUserService;
import com.spark.platform.wx.shop.biz.user.service.ShopUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author: wangdingfeng
 * @Date: 2020/12/13 21:03
 * @Description: api 用户信息管理
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ApiUserServiceImpl implements ApiUserService {

    private final ShopUserService shopUserService;
    private final SparkWxProperties sparkWxProperties;

    @Override
    public String login(WxLoginDTO loginDTO) {
        // 拼接 微信auth.code2Session 登录凭证校验
        StringBuffer url = new StringBuffer(sparkWxProperties.getAuthUrl());
        url.append("?appid=").append(sparkWxProperties.getAppId()).append("&secret=").append(sparkWxProperties.getSecret())
                .append("&js_code").append(loginDTO.getJsCode()).append("&grant_type=authorization_code");
        JSONObject jsonObject = HttpCallOtherInterfaceUtils.getUrl(url.toString());
        if(null == jsonObject || "0".equals(jsonObject.get("errcode").toString())){
            throw new BusinessException("获取微信登录失败！");
        }

        ShopUser shopUser = shopUserService.getByOpenId(jsonObject.get("openid").toString());
        if(null == shopUser){
            // 新增用户
            shopUser = new ShopUser();
            shopUser.setWxOpenid(jsonObject.get("openid").toString());
            shopUser.setAvatar(loginDTO.getAvatar());
            shopUser.setNickname(loginDTO.getNickname());
            shopUser.setMobile(loginDTO.getMobile());
            shopUser.setLastLoginTime(LocalDateTime.now());
            shopUserService.save(shopUser);
        }else {
            // 更新用户信息
            shopUser.setLastLoginTime(LocalDateTime.now());
            shopUser.setAvatar(loginDTO.getAvatar());
            shopUser.setNickname(loginDTO.getNickname());
            shopUser.setMobile(loginDTO.getMobile());
            shopUserService.updateById(shopUser);
        }
        // 通过auth2 客户端模式 获取token

        return null;
    }
}

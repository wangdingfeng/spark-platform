package com.spark.platform.wx.shop.biz.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.spark.platform.admin.api.entity.authority.OauthClientDetails;
import com.spark.platform.admin.api.feign.client.AuthorityClient;
import com.spark.platform.common.base.constants.GlobalsConstants;
import com.spark.platform.common.base.enums.GrantTypesEnum;
import com.spark.platform.common.base.exception.BusinessException;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.config.properties.SparkProperties;
import com.spark.platform.common.utils.AddressUtils;
import com.spark.platform.common.utils.HttpCallOtherInterfaceUtils;
import com.spark.platform.wx.shop.api.dto.WxLoginDTO;
import com.spark.platform.wx.shop.api.entity.auth.ShopWxAuth;
import com.spark.platform.wx.shop.api.entity.user.*;
import com.spark.platform.wx.shop.biz.api.service.ApiUserService;
import com.spark.platform.wx.shop.biz.auth.service.ShopWxAuthService;
import com.spark.platform.wx.shop.biz.user.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author: wangdingfeng
 * @Date: 2020/12/13 21:03
 * @Description: api 用户信息管理
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ApiUserServiceImpl implements ApiUserService {

    private final ShopWxAuthService wxAuthService;
    private final ShopUserService shopUserService;
    private final AuthorityClient authorityClient;
    private final SparkProperties sparkProperties;
    private final ShopUserAddressService shopUserAddressService;
    private final ShopUserCartService shopUserCartService;
    private final ShopUserCollectService shopUserCollectService;
    private final ShopUserFootprintService shopUserFootprintService;

    @Override
    public ShopUser login(WxLoginDTO loginDTO) {
        // 拼接 微信auth.code2Session 登录凭证校验
        ShopWxAuth auth = wxAuthService.getByAppId(loginDTO.getAppId());
        Assert.notNull(auth, "当前appId查询无此数据!");
        String openId = getOpenId(auth,loginDTO.getJsCode());
        ShopUser shopUser = shopUserService.getByOpenId(openId);
        if (null == shopUser) {
            // 新增用户
            shopUser = new ShopUser();
            shopUser.setWxOpenid(openId);
            shopUser.setUsername("微信用户"+openId.substring(0,6));
        }
        // 获取登录IP
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        shopUser.setLastLoginIp(AddressUtils.getIpAddress(request));
        shopUser.setLastLoginTime(LocalDateTime.now());
        shopUser.setAvatar(loginDTO.getAvatar());
        shopUser.setNickname(loginDTO.getNickname());
        shopUser.setMobile(loginDTO.getMobile());
        shopUserService.saveOrUpdate(shopUser);
        // 通过auth2 客户端模式 获取token
        ApiResponse<OauthClientDetails> apiResponse = authorityClient.getOauthClientDetailsByClientId(auth.getClientId());
        OauthClientDetails oauthClientDetails = apiResponse.getData();
        if(null == oauthClientDetails){
            throw new BusinessException("请配置小程序对应的客户端!");
        }
        shopUser.setToken(this.getToken(auth.getClientId()));
        return shopUser;
    }

    @Override
    public List<ShopUserAddress> findAddress(Integer userId) {
        return shopUserAddressService.findAddress(userId);
    }

    @Override
    public List<ShopUserCart> findCart(Integer userId) {
        return shopUserCartService.findCart(userId);
    }

    /**
     * 发送 微信授权接口
     * @param auth
     * @param jsCode
     * @return
     */
    private String getOpenId(ShopWxAuth auth,String jsCode){
        StringBuffer url = new StringBuffer(sparkProperties.getWx().getAuthUrl());
        url.append("?appid=").append(auth.getAppId()).append("&secret=").append(auth.getSecret())
                .append("&js_code").append(jsCode).append("&grant_type=").append(GrantTypesEnum.AUTHORIZATION_CODE);
        JSONObject jsonObject = HttpCallOtherInterfaceUtils.getUrl(url.toString());
        if (null == jsonObject || "0".equals(jsonObject.get("errcode").toString())) {
            throw new BusinessException("获取微信登录失败！");
        }
        return jsonObject.get("openid").toString();
    }

    /**
     * 获取 token
     * @param clientId
     * @return
     */
    private String getToken(String clientId){
        ApiResponse<OauthClientDetails> apiResponse = authorityClient.getOauthClientDetailsByClientId(clientId);
        OauthClientDetails clientDetails = apiResponse.getData();
        if(null == clientDetails){
            throw new BusinessException("请配置小程序对应的客户端!");
        }
        StringBuffer url = new StringBuffer(GlobalsConstants.OAUTH_TOKEN_URL);
        url.append("?client_id=").append(clientDetails.getClientId()).append("&client_secret=").append(clientDetails.getClientSecret())
                .append("&grant_typ=").append(GrantTypesEnum.CLIENT_CREDENTIALS);
        JSONObject jsonObject = HttpCallOtherInterfaceUtils.callOtherInterface(sparkProperties.getGatewayUrl(),url.toString());
        if (null == jsonObject) {
            throw new BusinessException("调用oauth2获取token失败！");
        }
        return (String)jsonObject.get("access_token");
    }
}

package com.spark.platform.wx.shop.biz.api.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.admin.api.entity.authority.OauthClientDetails;
import com.spark.platform.admin.api.feign.client.AuthorityClient;
import com.spark.platform.common.base.constants.GlobalsConstants;
import com.spark.platform.common.base.enums.GrantTypesEnum;
import com.spark.platform.common.base.exception.BusinessException;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.config.properties.SparkProperties;
import com.spark.platform.common.utils.AddressUtils;
import com.spark.platform.common.utils.HttpCallOtherInterfaceUtils;
import com.spark.platform.wx.shop.api.dto.UserCartDTO;
import com.spark.platform.wx.shop.api.dto.WxLoginDTO;
import com.spark.platform.wx.shop.api.entity.auth.ShopWxAuth;
import com.spark.platform.wx.shop.api.entity.user.ShopUser;
import com.spark.platform.wx.shop.api.entity.user.ShopUserCart;
import com.spark.platform.wx.shop.api.entity.user.ShopUserCollect;
import com.spark.platform.wx.shop.api.entity.user.ShopUserFootprint;
import com.spark.platform.wx.shop.api.vo.ShopUserDTO;
import com.spark.platform.wx.shop.biz.api.service.ApiUserService;
import com.spark.platform.wx.shop.biz.auth.service.ShopWxAuthService;
import com.spark.platform.wx.shop.biz.user.service.ShopUserCartService;
import com.spark.platform.wx.shop.biz.user.service.ShopUserCollectService;
import com.spark.platform.wx.shop.biz.user.service.ShopUserFootprintService;
import com.spark.platform.wx.shop.biz.user.service.ShopUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private final ShopUserCollectService shopUserCollectService;
    private final ShopUserFootprintService shopUserFootprintService;
    private final ShopUserCartService shopUserCartService;

    @Override
    @Transactional(readOnly = false)
    public ShopUserDTO login(WxLoginDTO loginDTO) {
        log.info("当前登录用户js_code:{}", loginDTO.getJsCode());
        // 拼接 微信auth.code2Session 登录凭证校验
        ShopWxAuth auth = wxAuthService.getByAppId(loginDTO.getAppId());
        Assert.notNull(auth, "当前appId查询无此数据!");
        String openId = getOpenId(auth, loginDTO.getJsCode());
        ShopUser shopUser = shopUserService.getByOpenId(openId);
        if (null == shopUser) {
            // 新增用户
            shopUser = new ShopUser();
            shopUser.setWxOpenid(openId);
            shopUser.setUserType(0);
            shopUser.setUsername("微信用户" + openId.substring(0, 6));
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
        ShopUserDTO userDTO = new ShopUserDTO();
        BeanUtil.copyProperties(shopUser,userDTO);
        userDTO.setUserTypeName(shopUser.getUserType() == 0 ? "普通用户" : "会员用户");
        // 通过auth2 客户端模式 获取token
        userDTO.setToken(this.getToken(auth.getClientId()));
        return userDTO;
    }

    @Override
    public boolean updateUser(ShopUserDTO shopUserDTO) {
        ShopUser user = new ShopUser();
        user.setId(shopUserDTO.getId());
        user.setMobile(shopUserDTO.getMobile());
        user.setBirthday(shopUserDTO.getBirthday());
        user.setAvatar(shopUserDTO.getAvatar());
        boolean flag = shopUserService.updateById(user);
        if(!flag){
            throw new BusinessException("当前用户不合法，请重新登录!");
        }
        return true;
    }

    @Override
    public ShopUserDTO findUser(Integer userId) {
        ShopUser user = shopUserService.getById(userId);
        Assert.notNull(user,"查询不到当前用户!");
        ShopUserDTO userDTO = new ShopUserDTO();
        BeanUtil.copyProperties(user,userDTO);
        userDTO.setUserTypeName(user.getUserType() == 0 ? "普通用户" : "会员用户");
        // 查询购物车和足迹数量
        userDTO.setCollectNum(shopUserCollectService.count(userId));
        userDTO.setFoorPrintNum(shopUserFootprintService.count(userId));
        return userDTO;
    }

    @Override
    public IPage<ShopUserCollect> pageCollect(long current, long size, Integer userId) {
        ShopUserCollect collect = new ShopUserCollect();
        collect.setUserId(userId);
        return shopUserCollectService.listPage(new Page(current,size),collect);
    }

    @Override
    @Async
    public boolean saveCollect(Integer userId, Integer goodsId) {
        return shopUserCollectService.collect(userId,goodsId);
    }

    @Override
    public boolean delCollects(List<Integer> ids) {
        return shopUserCollectService.removeByIds(ids);
    }

    @Override
    public IPage<ShopUserFootprint> pageFootprint(long current, long size, Integer userId) {
        ShopUserFootprint footprint = new ShopUserFootprint();
        footprint.setUserId(userId);
        return shopUserFootprintService.listPage(new Page(current,size),footprint);
    }

    @Override
    public boolean delFootprints(List<Integer> ids) {
        return shopUserFootprintService.removeByIds(ids);
    }

    @Override
    public boolean submitCart(UserCartDTO userCartDTO) {
        return shopUserCartService.submitCart(userCartDTO);
    }

    @Override
    public IPage<ShopUserCart> pageCart(long current, long size, Integer userId) {
        ShopUserCart cart = new ShopUserCart();
        cart.setUserId(userId);
        return shopUserCartService.listPage(new Page(current,size),cart);
    }

    @Override
    public boolean delCart(Integer userId, Integer cartId) {
        return shopUserCartService.deleteCart(userId,cartId);
    }

    @Override
    public boolean updateCart(UserCartDTO userCart) {
        ShopUserCart cart = new ShopUserCart();
        BeanUtil.copyProperties(userCart,cart);
        return shopUserCartService.updateById(cart);
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
                .append("&js_code=").append(jsCode).append("&grant_type=").append(GrantTypesEnum.AUTHORIZATION_CODE);
        JSONObject jsonObject = HttpCallOtherInterfaceUtils.getUrl(url.toString());
        if (null == jsonObject || null != jsonObject.get("errcode")) {
            throw new BusinessException("获取微信登录失败！");
        }
        return (String)jsonObject.get("openid");
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
                .append("&grant_type=").append(GrantTypesEnum.CLIENT_CREDENTIALS.getType());
        JSONObject jsonObject = HttpCallOtherInterfaceUtils.callOtherGetInterface(sparkProperties.getGatewayUrl(),url.toString());
        if (null == jsonObject || null == jsonObject.get("access_token")) {
            throw new BusinessException("调用oauth2获取token失败！");
        }
        return (String)jsonObject.get("access_token");
    }
}

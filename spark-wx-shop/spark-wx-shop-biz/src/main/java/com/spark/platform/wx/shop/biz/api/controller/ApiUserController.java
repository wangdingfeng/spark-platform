package com.spark.platform.wx.shop.biz.api.controller;

import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.base.support.BaseController;
import com.spark.platform.wx.shop.api.dto.WxLoginDTO;
import com.spark.platform.wx.shop.api.entity.user.ShopUser;
import com.spark.platform.wx.shop.api.entity.user.ShopUserAddress;
import com.spark.platform.wx.shop.api.entity.user.ShopUserCart;
import com.spark.platform.wx.shop.api.entity.user.ShopUserCollect;
import com.spark.platform.wx.shop.api.entity.user.ShopUserFootprint;
import com.spark.platform.wx.shop.biz.api.service.ApiUserService;
import com.spark.platform.wx.shop.biz.marketing.service.ShopCouponUserService;
import com.spark.platform.wx.shop.biz.user.service.ShopUserAddressService;
import com.spark.platform.wx.shop.biz.user.service.ShopUserCartService;
import com.spark.platform.wx.shop.biz.user.service.ShopUserCollectService;
import com.spark.platform.wx.shop.biz.user.service.ShopUserFootprintService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.wx.shop.biz.api.controller
 * @ClassName: ApiUserController
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2021/1/8 17:26
 * @Version: 1.0
 */
@RestController
@RequestMapping("/userApi")
@Api(tags = "小程序用户接口")
@RequiredArgsConstructor
@Slf4j
public class ApiUserController extends BaseController {

    private final ApiUserService apiUserService;
    private final ShopUserAddressService shopUserAddressService;
    private final ShopUserCartService shopUserCartService;
    private final ShopUserCollectService shopUserCollectService;
    private final ShopUserFootprintService shopUserFootprintService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public ApiResponse<ShopUser> login(@Valid @RequestBody WxLoginDTO loginDTO) {
        return success(apiUserService.login(loginDTO));
    }

    @PutMapping("{userId}/mobile")
    @ApiOperation(value = "保存用户手机号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true),
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true)
    })
    public ApiResponse saveMobile(@PathVariable Integer userId, @RequestParam String mobile) {
        log.info("【用户信息=>保存手机号】,用户:{},手机号:{}", userId, mobile);
        return success(apiUserService.saveMobile(userId, mobile));
    }

    @PostMapping("/address")
    @ApiOperation(value = "保存更新用户地址信息")
    public ApiResponse saveAddress(@Valid @RequestBody ShopUserAddress shopUserAddress) {
        return success(shopUserAddressService.saveOrUpdate(shopUserAddress));
    }

    @DeleteMapping("/{userId}/address")
    @ApiOperation(value = "删除用户地址信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true),
            @ApiImplicitParam(name = "id", value = "地址Id", required = true)
    })
    public ApiResponse delAddress(@PathVariable Integer userId, @RequestParam Integer id) {
        return success(shopUserAddressService.deleteAddress(userId, id));
    }

    @PostMapping("/cart")
    @ApiOperation(value = "保存更新用户购物车信息")
    public ApiResponse saveCart(@Valid @RequestBody ShopUserCart shopUserCart) {
        return success(shopUserCartService.saveCart(shopUserCart));
    }

    @DeleteMapping("/{userId}/cart")
    @ApiOperation(value = "删除用户购物车信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true),
            @ApiImplicitParam(name = "id", value = "购物车Id", required = true)
    })
    public ApiResponse delCart(@PathVariable Integer userId, @RequestParam Integer id) {
        return success(shopUserCartService.deleteCart(userId, id));
    }

    @PostMapping("/collect")
    @ApiOperation(value = "用户收藏信息")
    public ApiResponse saveCollect(@Valid @RequestBody ShopUserCollect userCollect) {
        return success(shopUserCollectService.save(userCollect));
    }

    @DeleteMapping("/collect")
    @ApiOperation(value = "删除用户收藏信息")
    @ApiImplicitParam(name = "ids", value = "收藏Id集合", required = true)
    public ApiResponse delCollect(@RequestParam("ids[]") List<String> ids) {
        return success(shopUserCollectService.removeByIds(ids));
    }

    @PostMapping("/footprint")
    @ApiOperation(value = "用户足迹信息")
    public ApiResponse saveFootprint(@Valid @RequestBody ShopUserFootprint userFootprint) {
        return success(shopUserFootprintService.save(userFootprint));
    }

    @DeleteMapping("/footprint")
    @ApiOperation(value = "删除用户足迹信息")
    @ApiImplicitParam(name = "ids", value = "收藏Id集合", required = true)
    public ApiResponse delFootprint(@RequestParam("ids[]") List<String> ids) {
        return success(shopUserFootprintService.removeByIds(ids));
    }

}

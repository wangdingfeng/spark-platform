package com.spark.platform.wx.shop.biz.marketing.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.marketing.ShopCoupon;
import com.spark.platform.wx.shop.biz.marketing.service.ShopCouponService;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spark.platform.common.base.support.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.spark.platform.common.base.support.BaseController;

/**
 * <p>
 * 优惠券 api访问层
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-03
 */
@RestController
@RequestMapping("/marketing/coupon")
@Api(tags = "优惠券")
@RequiredArgsConstructor
public class ShopCouponController extends BaseController {

    private final ShopCouponService shopSettingCouponService;

    @GetMapping("/page")
    @ApiOperation(value = "优惠券列表")
    public ApiResponse page(ShopCoupon shopSettingCoupon, Page page) {
        return success(shopSettingCouponService.page(page, Wrappers.query(shopSettingCoupon)));
    }

    @PostMapping
    @ApiOperation(value = "保存优惠券信息")
    public ApiResponse save(@RequestBody ShopCoupon shopSettingCoupon) {
        return success(shopSettingCouponService.saveOrUpdate(shopSettingCoupon));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除优惠券")
    public ApiResponse delete(@PathVariable Long id) {
        return success(shopSettingCouponService.removeById(id));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取优惠券信息")
    public ApiResponse getById(@PathVariable Long id) {
        return success(shopSettingCouponService.getById(id));
    }

}

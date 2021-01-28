package com.spark.platform.wx.shop.biz.api.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.base.support.BaseController;
import com.spark.platform.wx.shop.api.vo.CouponCardVo;
import com.spark.platform.wx.shop.api.vo.SeckillVo;
import com.spark.platform.wx.shop.api.vo.SwiperVo;
import com.spark.platform.wx.shop.biz.api.service.ApiMarketingService;
import com.spark.platform.wx.shop.biz.api.service.ApiSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: wangdingfeng
 * @Date: 2021/1/10 16:47
 * @Description: 小程序营销中心接口
 */
@RestController
@Api(tags = "小程序营销中心接口")
@RequiredArgsConstructor
public class ApiMarketingController extends BaseController {

    private final ApiMarketingService apiMarketingService;
    private final ApiSettingService apiSettingService;

    @GetMapping("/api/coupon")
    @ApiOperation(value = "全部优惠券")
    public ApiResponse<CouponCardVo> coupon(@RequestParam Integer limit) {
        return success(apiMarketingService.findCoupon(limit));
    }

    @GetMapping("/coupon/user")
    @ApiOperation(value = "获取用户优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "页数",defaultValue = "20"),
            @ApiImplicitParam(name = "current", value = "页码",defaultValue = "1"),
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true),
            @ApiImplicitParam(name = "isUse", value = "是否使用", required = true)
    })
    public ApiResponse<IPage<CouponCardVo>> couponUser(@RequestParam Long size,@RequestParam Long current,@RequestParam Integer userId, boolean isUse) {
        return success(apiMarketingService.pageCouponUser(size, current, userId, isUse));
    }

    @PostMapping("/{userId}/coupon/{couponId}")
    @ApiOperation(value = "领取优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户Id",dataType = "int"),
            @ApiImplicitParam(name = "couponId", value = "优惠券ID",dataType = "int")
    })
    public ApiResponse<CouponCardVo> coupon(@PathVariable Integer userId, @PathVariable Integer couponId) {
        return success(apiMarketingService.receiveCoupon(userId,couponId));
    }

    @GetMapping("/api/seckill")
    @ApiOperation(value = "秒杀信息")
    public ApiResponse<List<SeckillVo>> seckill() {
        return success(apiMarketingService.findSeckill());
    }

    @GetMapping("/api/seckill/goods")
    @ApiOperation(value = "秒杀商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "页数",defaultValue = "20"),
            @ApiImplicitParam(name = "current", value = "页码",defaultValue = "1"),
            @ApiImplicitParam(name = "secKillId", value = "秒杀配置ID", required = true)
    })
    public ApiResponse<CouponCardVo> secKillGoods(@RequestParam Long size,@RequestParam Long current,@RequestParam Integer secKillId) {
        return success(apiMarketingService.secKillGoods(size, current, secKillId));
    }

    @GetMapping("/api/pink/goods")
    @ApiOperation(value = "拼团商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "页数",defaultValue = "20"),
            @ApiImplicitParam(name = "current", value = "页码",defaultValue = "1")
    })
    public ApiResponse<CouponCardVo> pinkGoods(@RequestParam Long size,@RequestParam Long current) {
        return success(apiMarketingService.pinkGoods(size, current));
    }

    @GetMapping("/api/swiper")
    @ApiOperation(value = "轮播图信息")
    public ApiResponse<List<SwiperVo>> swiper() {
        return success(apiSettingService.swiper());
    }

}

package com.spark.platform.wx.shop.biz.setting.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spark.platform.wx.shop.biz.setting.service.ShopSettingSwiperService;
import com.spark.platform.wx.shop.api.entity.setting.ShopSettingSwiper;
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

import javax.validation.Valid;

/**
 * <p>
 * 首页轮播图 api访问层
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-03
 */
@RestController
@RequestMapping("/setting/swiper")
@Api(tags = "首页轮播图")
@RequiredArgsConstructor
public class ShopSettingSwiperController extends BaseController {

    private final ShopSettingSwiperService shopSettingSwiperService;

    @GetMapping("/page")
    @ApiOperation(value = "首页轮播图列表")
    public ApiResponse page(ShopSettingSwiper shopSettingSwiper, Page page) {
        return success(shopSettingSwiperService.finPage(page, shopSettingSwiper));
    }

    @PostMapping
    @ApiOperation(value = "保存首页轮播图信息")
    public ApiResponse save(@Valid @RequestBody ShopSettingSwiper shopSettingSwiper) {
        return success(shopSettingSwiperService.saveOrUpdate(shopSettingSwiper));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除首页轮播图")
    public ApiResponse delete(@PathVariable Long id) {
        return success(shopSettingSwiperService.removeById(id));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取首页轮播图信息")
    public ApiResponse getById(@PathVariable Long id) {
        return success(shopSettingSwiperService.getById(id));
    }

}

package com.spark.platform.wx.shop.biz.auth.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.auth.ShopWxAuth;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spark.platform.wx.shop.biz.auth.service.ShopWxAuthService;
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
 * 微信小程序授权信息 api访问层
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-14
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "微信小程序授权信息")
@RequiredArgsConstructor
public class ShopWxAuthController extends BaseController {

      private final ShopWxAuthService shopWxAuthService;

      @GetMapping("/page")
      @ApiOperation(value = "微信小程序授权信息列表")
      public ApiResponse page(ShopWxAuth shopWxAuth, Page page){
        return success(shopWxAuthService.page(page, Wrappers.query(shopWxAuth)));
      }

      @PostMapping
      @ApiOperation(value = "保存微信小程序授权信息信息")
      public ApiResponse save(@Valid @RequestBody ShopWxAuth shopWxAuth){
        return success(shopWxAuthService.saveOrUpdate(shopWxAuth));
      }

      @DeleteMapping("/{id}")
      @ApiOperation(value = "删除微信小程序授权信息")
      public ApiResponse delete(@PathVariable Long id){
        return success(shopWxAuthService.removeById(id));
      }

      @GetMapping("/{id}")
      @ApiOperation(value = "根据id获取微信小程序授权信息信息")
      public ApiResponse getById(@PathVariable Long id) {
        return success(shopWxAuthService.getById(id));
      }

}

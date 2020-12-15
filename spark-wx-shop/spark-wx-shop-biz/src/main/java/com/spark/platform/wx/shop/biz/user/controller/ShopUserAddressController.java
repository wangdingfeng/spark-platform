package com.spark.platform.wx.shop.biz.user.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.user.ShopUserAddress;
import com.spark.platform.wx.shop.biz.user.service.ShopUserAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spark.platform.common.base.support.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.spark.platform.common.base.support.BaseController;

/**
 * <p>
 * 收货地址表 api访问层
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-10
 */
@RestController
@RequestMapping("/user/address")
@Api(tags = "收货地址表")
@RequiredArgsConstructor
public class ShopUserAddressController extends BaseController {

      private final ShopUserAddressService shopUserAddressService;

      @GetMapping("/page")
      @ApiOperation(value = "收货地址表列表")
      public ApiResponse page(ShopUserAddress shopUserAddress, Page page){
        return success(shopUserAddressService.page(page, Wrappers.query(shopUserAddress)));
      }

      @PostMapping
      @ApiOperation(value = "保存收货地址表信息")
      public ApiResponse save(@RequestBody ShopUserAddress shopUserAddress){
        return success(shopUserAddressService.save(shopUserAddress));
      }

      @PutMapping
      @ApiOperation(value = "更新收货地址表信息")
      public ApiResponse update(@RequestBody ShopUserAddress shopUserAddress){
        return success(shopUserAddressService.updateById(shopUserAddress));
      }

      @DeleteMapping("/{id}")
      @ApiOperation(value = "删除收货地址表")
      public ApiResponse delete(@PathVariable Long id){
        return success(shopUserAddressService.removeById(id));
      }

      @GetMapping("/{id}")
      @ApiOperation(value = "根据id获取收货地址表信息")
      public ApiResponse getById(@PathVariable Long id) {
        return success(shopUserAddressService.getById(id));
      }

}

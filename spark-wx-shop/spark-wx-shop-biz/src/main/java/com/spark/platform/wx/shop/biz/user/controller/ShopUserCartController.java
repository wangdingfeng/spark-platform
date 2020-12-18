package com.spark.platform.wx.shop.biz.user.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.user.ShopUserCart;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spark.platform.wx.shop.biz.user.service.ShopUserCartService;
import com.spark.platform.common.base.support.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
 * 会员购物车 api访问层
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-18
 */
@RestController
@RequestMapping("/user/shop-user-cart")
@Api(tags = "会员购物车")
@RequiredArgsConstructor
public class ShopUserCartController extends BaseController {

      private final ShopUserCartService shopUserCartService;

      @GetMapping("/page")
      @ApiOperation(value = "会员购物车列表")
      public ApiResponse page(ShopUserCart shopUserCart, Page page){
        return success(shopUserCartService.page(page, Wrappers.query(shopUserCart)));
      }

      @PostMapping
      @ApiOperation(value = "保存会员购物车信息")
      public ApiResponse save(@RequestBody ShopUserCart shopUserCart){
        return success(shopUserCartService.save(shopUserCart));
      }

      @PutMapping
      @ApiOperation(value = "更新会员购物车信息")
      public ApiResponse update(@RequestBody ShopUserCart shopUserCart){
        return success(shopUserCartService.updateById(shopUserCart));
      }

      @DeleteMapping("/{id}")
      @ApiOperation(value = "删除会员购物车")
      public ApiResponse delete(@PathVariable Long id){
        return success(shopUserCartService.removeById(id));
      }

      @GetMapping("/{id}")
      @ApiOperation(value = "根据id获取会员购物车信息")
      public ApiResponse getById(@PathVariable Long id) {
        return success(shopUserCartService.getById(id));
      }

}

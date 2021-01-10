package com.spark.platform.wx.shop.biz.goods.controller;

import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsCategoryService;
import com.spark.platform.common.base.support.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * 商品分类 api访问层
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-17
 */
@RestController
@RequestMapping("/goods/category")
@Api(tags = "商品分类")
@RequiredArgsConstructor
public class ShopGoodsCategoryController extends BaseController {

      private final ShopGoodsCategoryService shopGoodsCategoryService;

      @GetMapping("/list")
      @ApiOperation(value = "商品分类列表")
      public ApiResponse page(String name){
        return success(shopGoodsCategoryService.tree(name));
      }

      @PostMapping
      @ApiOperation(value = "保存商品分类信息")
      public ApiResponse save(@Valid @RequestBody ShopGoodsCategory shopGoodsCategory){
        return success(shopGoodsCategoryService.saveOrUpdate(shopGoodsCategory));
      }

      @DeleteMapping("/{id}")
      @ApiOperation(value = "删除商品分类")
      public ApiResponse delete(@PathVariable Integer id){
        return success(shopGoodsCategoryService.deleteCategory(id));
      }

      @GetMapping("/{id}")
      @ApiOperation(value = "根据id获取商品分类信息")
      public ApiResponse getById(@PathVariable Integer id) {
        return success(shopGoodsCategoryService.getById(id));
      }

}

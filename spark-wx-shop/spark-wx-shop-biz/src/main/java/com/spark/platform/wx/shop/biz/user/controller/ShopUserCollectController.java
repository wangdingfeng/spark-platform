package com.spark.platform.wx.shop.biz.user.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.user.ShopUserCollect;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spark.platform.wx.shop.biz.user.service.ShopUserCollectService;
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
 * 用户收藏 api访问层
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-10
 */
@RestController
@RequestMapping("/user/collect")
@Api(tags = "用户收藏")
@RequiredArgsConstructor
public class ShopUserCollectController extends BaseController {

      private final ShopUserCollectService shopUserCollectService;

      @GetMapping("/page")
      @ApiOperation(value = "用户收藏列表")
      public ApiResponse page(ShopUserCollect shopUserCollect, Page page){
        return success(shopUserCollectService.listPage(page,shopUserCollect));
      }

      @PostMapping
      @ApiOperation(value = "保存用户收藏信息")
      public ApiResponse save(@RequestBody ShopUserCollect shopUserCollect){
        return success(shopUserCollectService.save(shopUserCollect));
      }

      @PutMapping
      @ApiOperation(value = "更新用户收藏信息")
      public ApiResponse update(@RequestBody ShopUserCollect shopUserCollect){
        return success(shopUserCollectService.updateById(shopUserCollect));
      }

      @DeleteMapping("/{id}")
      @ApiOperation(value = "删除用户收藏")
      public ApiResponse delete(@PathVariable Long id){
        return success(shopUserCollectService.removeById(id));
      }

      @GetMapping("/{id}")
      @ApiOperation(value = "根据id获取用户收藏信息")
      public ApiResponse getById(@PathVariable Long id) {
        return success(shopUserCollectService.getById(id));
      }

}

package com.spark.platform.wx.shop.biz.user.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.user.ShopUserFootprint;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spark.platform.wx.shop.biz.user.service.ShopUserFootprintService;
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
 * 用户浏览足迹 api访问层
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-10
 */
@RestController
@RequestMapping("/user/footprint")
@Api(tags = "用户浏览足迹")
@RequiredArgsConstructor
public class ShopUserFootprintController extends BaseController {

      private final ShopUserFootprintService shopUserFootprintService;

      @GetMapping("/page")
      @ApiOperation(value = "用户浏览足迹列表")
      public ApiResponse page(ShopUserFootprint shopUserFootprint, Page page){
        return success(shopUserFootprintService.page(page, Wrappers.query(shopUserFootprint)));
      }

      @PostMapping
      @ApiOperation(value = "保存用户浏览足迹信息")
      public ApiResponse save(@RequestBody ShopUserFootprint shopUserFootprint){
        return success(shopUserFootprintService.save(shopUserFootprint));
      }

      @PutMapping
      @ApiOperation(value = "更新用户浏览足迹信息")
      public ApiResponse update(@RequestBody ShopUserFootprint shopUserFootprint){
        return success(shopUserFootprintService.updateById(shopUserFootprint));
      }

      @DeleteMapping("/{id}")
      @ApiOperation(value = "删除用户浏览足迹")
      public ApiResponse delete(@PathVariable Long id){
        return success(shopUserFootprintService.removeById(id));
      }

      @GetMapping("/{id}")
      @ApiOperation(value = "根据id获取用户浏览足迹信息")
      public ApiResponse getById(@PathVariable Long id) {
        return success(shopUserFootprintService.getById(id));
      }

}

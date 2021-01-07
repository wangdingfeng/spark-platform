package com.spark.platform.wx.shop.biz.marketing.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.marketing.ShopPinkUser;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spark.platform.wx.shop.biz.marketing.service.ShopPinkUserService;
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
 * 拼团用户列表 api访问层
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-07
 */
@RestController
@RequestMapping("/marketing/pink/user")
@Api(tags = "拼团用户列表")
@RequiredArgsConstructor
public class ShopPinkUserController extends BaseController {

      private final ShopPinkUserService shopPinkUserService;

      @GetMapping("/page")
      @ApiOperation(value = "拼团用户列表列表")
      public ApiResponse page(ShopPinkUser shopPinkUser, Page page){
        return success(shopPinkUserService.page(page, Wrappers.query(shopPinkUser)));
      }

      @PostMapping
      @ApiOperation(value = "保存拼团用户列表信息")
      public ApiResponse save(@RequestBody ShopPinkUser shopPinkUser){
        return success(shopPinkUserService.save(shopPinkUser));
      }

      @PutMapping
      @ApiOperation(value = "更新拼团用户列表信息")
      public ApiResponse update(@RequestBody ShopPinkUser shopPinkUser){
        return success(shopPinkUserService.updateById(shopPinkUser));
      }

      @DeleteMapping("/{id}")
      @ApiOperation(value = "删除拼团用户列表")
      public ApiResponse delete(@PathVariable Long id){
        return success(shopPinkUserService.removeById(id));
      }

      @GetMapping("/{id}")
      @ApiOperation(value = "根据id获取拼团用户列表信息")
      public ApiResponse getById(@PathVariable Long id) {
        return success(shopPinkUserService.getById(id));
      }

}

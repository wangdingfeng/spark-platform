package com.spark.platform.wx.shop.biz.marketing.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.marketing.ShopPinkGoods;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spark.platform.wx.shop.biz.marketing.service.ShopPinkGoodsService;
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
 * 拼团产品 api访问层
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-07
 */
@RestController
@RequestMapping("/marketing/pink/goods")
@Api(tags = "拼团产品")
@RequiredArgsConstructor
public class ShopPinkGoodsController extends BaseController {

      private final ShopPinkGoodsService shopPinkGoodsService;

      @GetMapping("/page")
      @ApiOperation(value = "拼团产品列表")
      public ApiResponse page(ShopPinkGoods shopPinkGoods, Page page){
        return success(shopPinkGoodsService.findPage(page,shopPinkGoods));
      }

      @PostMapping
      @ApiOperation(value = "保存拼团产品信息")
      public ApiResponse save(@RequestBody ShopPinkGoods shopPinkGoods){
        return success(shopPinkGoodsService.saveOrUpdate(shopPinkGoods));
      }

      @PutMapping
      @ApiOperation(value = "更新拼团用户列表信息")
      public ApiResponse update(@RequestBody ShopPinkGoods shopPinkUser){
            return success(shopPinkGoodsService.updateById(shopPinkUser));
      }

      @DeleteMapping("/{id}")
      @ApiOperation(value = "删除拼团产品")
      public ApiResponse delete(@PathVariable Long id){
        return success(shopPinkGoodsService.removeById(id));
      }

      @GetMapping("/{id}")
      @ApiOperation(value = "根据id获取拼团产品信息")
      public ApiResponse getById(@PathVariable Long id) {
        return success(shopPinkGoodsService.getById(id));
      }

}

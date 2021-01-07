package com.spark.platform.wx.shop.biz.order.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.order.ShopOrderExpress;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spark.platform.wx.shop.biz.order.service.ShopOrderExpressService;
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
 * 订单物流信息表，发货时生成 api访问层
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-21
 */
@RestController
@RequestMapping("/order/express")
@Api(tags = "订单物流信息表，发货时生成")
@RequiredArgsConstructor
public class ShopOrderExpressController extends BaseController {

      private final ShopOrderExpressService shopOrderExpressService;

      @GetMapping("/page")
      @ApiOperation(value = "订单物流信息表，发货时生成列表")
      public ApiResponse page(ShopOrderExpress shopOrderExpress, Page page){
        return success(shopOrderExpressService.page(page, Wrappers.query(shopOrderExpress)));
      }

      @PostMapping
      @ApiOperation(value = "保存订单物流信息表，发货时生成信息")
      public ApiResponse save(@RequestBody ShopOrderExpress shopOrderExpress){
        return success(shopOrderExpressService.save(shopOrderExpress));
      }

      @PutMapping
      @ApiOperation(value = "更新订单物流信息表，发货时生成信息")
      public ApiResponse update(@RequestBody ShopOrderExpress shopOrderExpress){
        return success(shopOrderExpressService.updateById(shopOrderExpress));
      }

      @DeleteMapping("/{id}")
      @ApiOperation(value = "删除订单物流信息表，发货时生成")
      public ApiResponse delete(@PathVariable Long id){
        return success(shopOrderExpressService.removeById(id));
      }

      @GetMapping("/{id}")
      @ApiOperation(value = "根据id获取订单物流信息表，发货时生成信息")
      public ApiResponse getById(@PathVariable Long id) {
        return success(shopOrderExpressService.getById(id));
      }

}

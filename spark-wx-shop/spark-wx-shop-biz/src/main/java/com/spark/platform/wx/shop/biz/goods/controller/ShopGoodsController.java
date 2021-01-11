package com.spark.platform.wx.shop.biz.goods.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoods;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsSku;
import com.spark.platform.wx.shop.api.enums.ShopGoodsStatusEnum;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsSkuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsService;
import com.spark.platform.common.base.support.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.spark.platform.common.base.support.BaseController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 商品表 api访问层
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-15
 */
@RestController
@RequestMapping("/goods")
@Api(tags = "商品表")
@RequiredArgsConstructor
public class ShopGoodsController extends BaseController {

    private final ShopGoodsService shopGoodsService;
    private final ShopGoodsSkuService shopGoodsSkuService;

    @GetMapping("/page")
    @ApiOperation(value = "商品表列表")
    public ApiResponse<Page<ShopGoods>> page(ShopGoods shopGoods, Page page) {
        return success(shopGoodsService.page(page, Wrappers.query(shopGoods).orderByDesc("modify_date")));
    }

    @PostMapping
    @ApiOperation(value = "保存商品表信息")
    public ApiResponse save(@Valid @RequestBody ShopGoods shopGoods) {
        shopGoodsService.saveShopGoods(shopGoods);
        return success(shopGoods);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除商品表")
    public ApiResponse delete(@PathVariable Integer id) {
        return success(shopGoodsService.removeById(id));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取商品表信息")
    public ApiResponse<ShopGoods> getById(@PathVariable Integer id) {
        return success(shopGoodsService.getShopGoods(id));
    }

    @PatchMapping("/publish/{id}")
    @ApiOperation(value = "根据id上架商品")
    public ApiResponse publish(@PathVariable Integer id) {
        return success(shopGoodsService.updateStatus(id, ShopGoodsStatusEnum.PUBLISH.getStatus()));
    }

    @PatchMapping("/off/{id}")
    @ApiOperation(value = "根据id下架商品")
    public ApiResponse off(@PathVariable Integer id) {
        return success(shopGoodsService.updateStatus(id, ShopGoodsStatusEnum.OFF_PUBLISH.getStatus()));
    }

    @GetMapping("/sku/{id}")
    @ApiOperation(value = "根据id获取商品表信息")
    public ApiResponse<List<ShopGoodsSku>> findSku(@PathVariable Integer id) {
        return success(shopGoodsSkuService.findByGoodsId(id));
    }

}

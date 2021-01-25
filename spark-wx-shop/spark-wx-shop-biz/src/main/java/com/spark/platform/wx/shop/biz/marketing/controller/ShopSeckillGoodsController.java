package com.spark.platform.wx.shop.biz.marketing.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.marketing.ShopSeckillGoods;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spark.platform.wx.shop.biz.marketing.service.ShopSeckillGoodsService;
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
 * 商品秒杀配置列表 api访问层
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-05
 */
@RestController
@RequestMapping("/marketing/seckill/goods")
@Api(tags = "商品秒杀配置列表")
@RequiredArgsConstructor
public class ShopSeckillGoodsController extends BaseController {

    private final ShopSeckillGoodsService shopSeckillGoodsService;

    @GetMapping("/page")
    @ApiOperation(value = "商品秒杀配置列表列表")
    public ApiResponse page(ShopSeckillGoods shopSeckillGoods, Page page) {
        return success(shopSeckillGoodsService.findPage(page, shopSeckillGoods));
    }

    @PostMapping
    @ApiOperation(value = "保存商品秒杀配置列表信息")
    public ApiResponse save(@RequestBody ShopSeckillGoods shopSeckillGoods) {
        return success(shopSeckillGoodsService.saveOrUpdate(shopSeckillGoods));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除商品秒杀配置列表")
    public ApiResponse delete(@PathVariable Integer id) {
        return success(shopSeckillGoodsService.deleteGoods(id));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取商品秒杀配置列表信息")
    public ApiResponse<ShopSeckillGoods> getById(@PathVariable Integer id) {
        return success(shopSeckillGoodsService.getById(id));
    }

    @GetMapping("/day")
    @ApiOperation(value = "商品秒杀一键续期")
    public ApiResponse addToday(){
        shopSeckillGoodsService.addDay();
        return success("增加成功");
    }

}

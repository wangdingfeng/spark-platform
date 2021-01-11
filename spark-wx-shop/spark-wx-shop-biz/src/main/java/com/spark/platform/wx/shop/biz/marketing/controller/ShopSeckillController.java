package com.spark.platform.wx.shop.biz.marketing.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.marketing.ShopSeckill;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spark.platform.wx.shop.biz.marketing.service.ShopSeckillService;
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
 * 秒杀配置 api访问层
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-05
 */
@RestController
@RequestMapping("/marketing/seckill")
@Api(tags = "秒杀配置")
@RequiredArgsConstructor
public class ShopSeckillController extends BaseController {

    private final ShopSeckillService shopSeckillService;

    @GetMapping("/page")
    @ApiOperation(value = "秒杀配置列表")
    public ApiResponse page(ShopSeckill shopSeckill, Page page) {
        return success(shopSeckillService.page(page, Wrappers.query(shopSeckill)));
    }

    @PostMapping
    @ApiOperation(value = "保存秒杀配置信息")
    public ApiResponse save(@RequestBody ShopSeckill shopSeckill) {
        return success(shopSeckillService.saveOrUpdate(shopSeckill));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除秒杀配置")
    public ApiResponse delete(@PathVariable Long id) {
        return success(shopSeckillService.removeById(id));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取秒杀配置信息")
    public ApiResponse getById(@PathVariable Long id) {
        return success(shopSeckillService.getById(id));
    }

}

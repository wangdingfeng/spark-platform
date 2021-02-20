package com.spark.platform.wx.shop.biz.user.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.user.ShopUser;
import com.spark.platform.wx.shop.biz.user.service.ShopUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.spark.platform.common.base.support.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.spark.platform.common.base.support.BaseController;

/**
 * <p>
 * shop会员管理 api访问层
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-10
 */
@RestController
@RequestMapping("/user")
@Api(tags = "shop会员管理")
@RequiredArgsConstructor
public class ShopUserController extends BaseController {

    private final ShopUserService shopUserService;

    @GetMapping("/page")
    @ApiOperation(value = "shop会员管理列表")
    public ApiResponse page(ShopUser shopUser, Page page) {
        return success(shopUserService.page(page, Wrappers.query(shopUser)));
    }

    @PostMapping
    @ApiOperation(value = "保存shop会员管理信息")
    public ApiResponse save(@RequestBody ShopUser shopUser) {
        return success(shopUserService.save(shopUser));
    }

    @PatchMapping("/{id}/{status}")
    @ApiOperation(value = "更新shop会员状态")
    public ApiResponse update(@PathVariable Integer id, @PathVariable Integer status) {
        return success(shopUserService.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除shop会员管理")
    public ApiResponse delete(@PathVariable Long id) {
        return success(shopUserService.removeById(id));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取shop会员管理信息")
    public ApiResponse getById(@PathVariable Long id) {
        return success(shopUserService.getById(id));
    }

}

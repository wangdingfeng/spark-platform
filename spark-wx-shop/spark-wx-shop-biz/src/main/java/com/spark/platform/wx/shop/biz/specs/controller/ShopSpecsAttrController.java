package com.spark.platform.wx.shop.biz.specs.controller;


import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.specs.ShopSpecsAttr;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spark.platform.wx.shop.biz.specs.service.ShopSpecsAttrService;
import com.spark.platform.common.base.support.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.spark.platform.common.base.support.BaseController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品规格属性 api访问层
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-16
 */
@RestController
@RequestMapping("/specs")
@Api(tags = "商品规格属性")
@AllArgsConstructor
public class ShopSpecsAttrController extends BaseController {

    private final ShopSpecsAttrService shopSpecsAttrService;

    @GetMapping("/page")
    @ApiOperation(value = "商品规格属性列表")
    public ApiResponse page(ShopSpecsAttr shopSpecsAttr, Page page) {
        return success(shopSpecsAttrService.page(page, Wrappers.query(shopSpecsAttr)));
    }

    @GetMapping("/list")
    @ApiOperation(value = "所有商品规格属性")
    public ApiResponse<List<ShopSpecsAttr>> list() {
        return success(shopSpecsAttrService.list());
    }

    @PostMapping
    @ApiOperation(value = "保存商品规格属性信息")
    public ApiResponse save(@RequestBody ShopSpecsAttr shopSpecsAttr) {
        return success(shopSpecsAttrService.saveOrUpdate(shopSpecsAttr));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除商品规格属性")
    public ApiResponse delete(@PathVariable Long id) {
        return success(shopSpecsAttrService.removeById(id));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取商品规格属性信息")
    public ApiResponse<ShopSpecsAttr> getById(@PathVariable Integer id) {
        return success(shopSpecsAttrService.getShopSpecsAttr(id));
    }

    @GetMapping("/array/{ids}")
    @ApiOperation(value = "根据id数组获取商品规格属性信息")
    public ApiResponse<List<ShopSpecsAttr>> getByIds(@PathVariable String ids) {
        String[] idArr = ids.split(Constants.COMMA);
        List<ShopSpecsAttr> attrList = new ArrayList<>(idArr.length);
        for(int i=0; i< idArr.length; i++){
            attrList.add(shopSpecsAttrService.getShopSpecsAttr(Integer.parseInt(idArr[i])));
        }
        return success(attrList);
    }

}

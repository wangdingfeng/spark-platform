package com.spark.platform.adminbiz.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.adminapi.entity.dict.Dict;
import com.spark.platform.adminapi.entity.dict.DictItem;
import com.spark.platform.adminbiz.service.dict.DictItemService;
import com.spark.platform.adminbiz.service.dict.DictService;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.base.support.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: wangdingfeng
 * @Date: 2020/3/20 22:42
 * @Description: 字典管理
 */
@RestController
@RequestMapping("/dict")
@Api(tags = "字典管理")
public class DictController extends BaseController {

    @Autowired
    private DictService dictService;

    @Autowired
    private DictItemService dictItemService;

    @PostMapping("/page")
    @ApiOperation(value = "字典列表")
    public ApiResponse page(Dict dict, Page page){
        return success(dictService.findPage(dict,page));
    }

    @PostMapping("/page/item")
    @ApiOperation(value = "字典子列表")
    public ApiResponse pageItem(DictItem dictItem,Page page){
        return success(dictItemService.page(page, Wrappers.query(dictItem).orderByAsc("sort")));
    }

    @GetMapping("/type/{type}")
    @ApiOperation(value = "通过type查询菜单子项")
    public ApiResponse findItemType(@PathVariable String type){
        return success(dictItemService.findItemType(type));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取字典信息")
    public ApiResponse findById(@PathVariable Long id){
        return success(dictService.getById(id));
    }

    @GetMapping("/item/{id}")
    @ApiOperation(value = "根据id获取字典信息")
    public ApiResponse findItemById(@PathVariable Long id){
        return success(dictItemService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "保存字典信息")
    public ApiResponse save(@RequestBody Dict dict){
        return success(dictService.save(dict));
    }

    @PostMapping("/item")
    @ApiOperation(value = "保存字典子表信息")
    public ApiResponse saveItem(@RequestBody DictItem dictItem){
        return success(dictItemService.save(dictItem));
    }

    @PutMapping
    @ApiOperation(value = "更新字典信息")
    public ApiResponse update(@RequestBody Dict dict){
        return success(dictService.updateById(dict));
    }

    @PutMapping("/item")
    @ApiOperation(value = "更新字典子表信息")
    public ApiResponse updateItem(@RequestBody DictItem dictItem){
        return success(dictItemService.updateById(dictItem));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除字典信息")
    public ApiResponse delete(@PathVariable Long id){
        return success(dictService.removeById(id));
    }

    @DeleteMapping("/item/{id}")
    @ApiOperation(value = "删除字典子表信息")
    public ApiResponse deleteItem(@PathVariable Long id){
        return success(dictItemService.removeById(id));
    }


}

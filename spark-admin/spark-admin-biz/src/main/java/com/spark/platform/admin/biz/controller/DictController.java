package com.spark.platform.admin.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.admin.api.entity.dict.Dict;
import com.spark.platform.admin.api.entity.dict.DictItem;
import com.spark.platform.admin.biz.service.dict.DictItemService;
import com.spark.platform.admin.biz.service.dict.DictService;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.base.support.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author: wangdingfeng
 * @Date: 2020/3/20 22:42
 * @Description: 字典管理
 */
@RestController
@RequestMapping("/dict")
@Api(tags = "字典管理")
@AllArgsConstructor
public class DictController extends BaseController {

    private final DictService dictService;
    private final DictItemService dictItemService;

    @PostMapping("/page")
    @ApiOperation(value = "字典列表")
    public ApiResponse<IPage> page(Dict dict, Page page){
        return success(dictService.findPage(dict,page));
    }

    @PostMapping("/page/item")
    @ApiOperation(value = "字典子列表")
    public ApiResponse<IPage> pageItem(DictItem dictItem,Page page){
        return success(dictItemService.page(page, Wrappers.query(dictItem).orderByAsc("sort")));
    }

    @GetMapping
    @ApiOperation(value = "获取所有的字典项")
    public ApiResponse<Map<String, List<DictItem>>> getAll(){
        return success(dictService.selectAllMap());
    }

    @GetMapping("/type/{type}")
    @ApiOperation(value = "通过type查询菜单子项")
    public ApiResponse<List<DictItem>> findItemType(@PathVariable String type){
        return success(dictItemService.findItemType(type));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取字典信息")
    public ApiResponse<Dict> findById(@PathVariable Long id){
        return success(dictService.getById(id));
    }

    @GetMapping("/item/{id}")
    @ApiOperation(value = "根据id获取字典信息")
    public ApiResponse<DictItem> findItemById(@PathVariable Long id){
        return success(dictItemService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "保存字典信息")
    public ApiResponse<Dict> save(@RequestBody @Valid Dict dict){
        dictService.save(dict);
        return success(dict);
    }

    @PostMapping("/item")
    @ApiOperation(value = "保存字典子表信息")
    public ApiResponse<DictItem> saveItem(@RequestBody @Valid DictItem dictItem){
        dictItemService.save(dictItem);
        return success(dictItem);
    }

    @PutMapping
    @ApiOperation(value = "更新字典信息")
    public ApiResponse<Boolean> update(@RequestBody @Valid Dict dict){
        return success(dictService.updateDict(dict));
    }

    @PutMapping("/item")
    @ApiOperation(value = "更新字典子表信息")
    public ApiResponse<Boolean> updateItem(@RequestBody @Valid DictItem dictItem){
        return success(dictItemService.updateById(dictItem));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除字典信息")
    public ApiResponse<Boolean> delete(@PathVariable Long id){
        return success(dictService.removeById(id));
    }

    @DeleteMapping("/item/{id}")
    @ApiOperation(value = "删除字典子表信息")
    public ApiResponse<Boolean> deleteItem(@PathVariable Long id){
        return success(dictItemService.removeById(id));
    }

    @GetMapping("/cache")
    @ApiOperation(value = "重置缓存")
    public ApiResponse resetCache(){
        dictService.resetCache();
        return success("重置成功");
    }

}

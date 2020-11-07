package com.spark.platform.admin.biz.controller;

import com.spark.platform.admin.api.entity.authority.Menu;
import com.spark.platform.admin.api.vo.MenuVue;
import com.spark.platform.admin.api.vo.VueTree;
import com.spark.platform.admin.biz.service.menu.MenuService;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.base.support.BaseController;
import com.spark.platform.common.log.annotation.ApiLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.controller
 * @ClassName: MenuController
 * @Author: wangdingfeng
 * @Description: 菜单APi
 * @Date: 2020/3/16 17:15
 * @Version: 1.0
 */
@RestController
@RequestMapping("/menu")
@Api(tags = "菜单管理")
@AllArgsConstructor
public class MenuController extends BaseController {

    private final MenuService menuService;

    @GetMapping("/index")
    @ApiOperation(value = "根据用户获取菜单信息")
    public ApiResponse<List<MenuVue>> build(Principal principal){
        return success(menuService.findMenuTree(principal.getName()));
    }

    @GetMapping("/api/auth")
    @ApiLog(ignore = true)
    @ApiOperation(value = "根据用户获取菜单信息")
    public ApiResponse<List<Menu>> findAuthByUserId(@RequestParam Long userId){
        return success(menuService.findAuthByUserId(userId));
    }

    @GetMapping("/list")
    @ApiOperation(value = "获取菜单列表")
    public ApiResponse<List<Menu>> list(@RequestParam String name){
        return success(menuService.treeList(name));
    }

    @PostMapping
    @ApiOperation(value = "保存菜单")
    @PreAuthorize("hasAnyAuthority('menu:add')")
    public ApiResponse<Boolean> save(@RequestBody @Valid Menu menu){
        return success(menuService.saveMenu(menu));
    }

    @PutMapping
    @ApiOperation(value = "更新菜单")
    @PreAuthorize("hasAnyAuthority('menu:edit')")
    public ApiResponse<Boolean> update(@RequestBody Menu menu){
        return success(menuService.updateById(menu));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除菜单")
    @PreAuthorize("hasAnyAuthority('menu:delete')")
    public ApiResponse<Boolean> delete(@PathVariable Long id){
        return success(menuService.removeById(id));
    }

    @GetMapping("/tree")
    @ApiOperation(value = "获取菜单树")
    public ApiResponse<List<VueTree>> getTree(){
        return success(menuService.getMenuTree());
    }

}

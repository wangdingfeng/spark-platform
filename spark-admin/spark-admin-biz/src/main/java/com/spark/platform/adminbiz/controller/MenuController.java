package com.spark.platform.adminbiz.controller;

import com.spark.platform.adminapi.entity.authority.Menu;
import com.spark.platform.adminapi.vo.MenuVue;
import com.spark.platform.adminbiz.service.menu.MenuService;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.base.support.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/index")
    @ApiOperation(value = "根据用户获取菜单信息")
    public ApiResponse build(Principal principal){
        List<MenuVue> menuVues = menuService.findMenuTree(principal.getName());
        return success(menuVues);
    }

    @GetMapping("/api/auth")
    @ApiOperation(value = "根据用户获取菜单信息")
    public ApiResponse findAuthByUserId(@RequestParam Long userId){
        return success(menuService.findAuthByUserId(userId));
    }

    /**
     * 获取菜单list
     * @param name
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "获取菜单列表")
    public ApiResponse list(@RequestParam String name){
        return success(menuService.treeList(name));
    }

    @PostMapping
    @ApiOperation(value = "保存菜单")
    @PreAuthorize("hasAnyAuthority('menu:add')")
    public ApiResponse save(@RequestBody Menu menu){
        return success(menuService.save(menu));
    }

    @PutMapping
    @ApiOperation(value = "更新菜单")
    @PreAuthorize("hasAnyAuthority('menu:edit')")
    public ApiResponse update(@RequestBody Menu menu){
        return success(menuService.updateById(menu));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除菜单")
    @PreAuthorize("hasAnyAuthority('menu:delete1')")
    public ApiResponse delete(@PathVariable Long id){
        return success(menuService.removeById(id));
    }

    @GetMapping("/tree")
    @ApiOperation(value = "获取菜单树")
    public ApiResponse getTree(){
        return success(menuService.getMenuTree());
    }

}

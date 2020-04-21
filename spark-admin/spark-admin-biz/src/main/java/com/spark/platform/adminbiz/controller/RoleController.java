package com.spark.platform.adminbiz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.adminapi.entity.role.Role;
import com.spark.platform.adminbiz.service.menu.MenuService;
import com.spark.platform.adminbiz.service.role.RoleService;
import com.spark.platform.common.base.support.BaseController;
import com.spark.platform.common.base.support.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.controller
 * @ClassName: RoleController
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/role")
@Api(tags = "角色管理")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @GetMapping("/{id}")
    @ApiOperation(value = "根据用户id获取用户角色信息")
    public ApiResponse getRoleByUserId(@PathVariable Long id) {
        return success(roleService.getRoleByUserId(id));
    }

    @PostMapping("/page")
    @ApiOperation(value = "获取角色列表分页")
    public ApiResponse page(Role role, Page page){
        return success(roleService.findPage(role,page));
    }

    @GetMapping("/all")
    @ApiOperation(value = "获取所有角色")
    public ApiResponse getRoleAll(){
        return success(roleService.findAllRole());
    }

    @PostMapping
    @ApiOperation(value = "保存角色信息")
    @PreAuthorize("hasAnyAuthority('role:add')")
    public ApiResponse save(@RequestBody Role role){
        return success(roleService.save(role));
    }

    @PutMapping
    @ApiOperation(value = "更新角色信息")
    @PreAuthorize("hasAnyAuthority('role:edit')")
    public ApiResponse update(@RequestBody Role role){
        return success(roleService.updateById(role));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "更新角色信息")
    @PreAuthorize("hasAnyAuthority('role:delete1')")
    public ApiResponse delete(@PathVariable Long id){
        return success(roleService.removeById(id));
    }

    @GetMapping("/auth")
    @ApiOperation(value = "根据角色id获取权限")
    public ApiResponse getRoleAuth(@RequestParam Long id){
        return success(menuService.getMenuIdsByRole(id));
    }

    @PostMapping("/auth")
    @ApiOperation(value = "更新角色权限信息")
    @PreAuthorize("hasAnyAuthority('role:edit')")
    public ApiResponse saveRoleAuth(@RequestBody Role role){
        roleService.saveRoleAuth(role);
        return success("更新成功");
    }


}

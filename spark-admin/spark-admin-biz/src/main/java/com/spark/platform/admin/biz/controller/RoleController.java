package com.spark.platform.admin.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.admin.api.entity.role.Role;
import com.spark.platform.admin.api.entity.user.User;
import com.spark.platform.admin.biz.service.menu.MenuService;
import com.spark.platform.admin.biz.service.role.RoleService;
import com.spark.platform.admin.biz.service.user.UserService;
import com.spark.platform.common.base.support.BaseController;
import com.spark.platform.common.base.support.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
@RequiredArgsConstructor
public class RoleController extends BaseController {

    private final RoleService roleService;
    private final MenuService menuService;
    private final UserService userService;

    @GetMapping("/{id}")
    @ApiOperation(value = "根据用户id获取用户角色信息")
    public ApiResponse<List<Role>> getRoleByUserId(@PathVariable Long id) {
        return success(roleService.getRoleByUserId(id));
    }

    @PostMapping("/page")
    @ApiOperation(value = "获取角色列表分页")
    public ApiResponse<IPage> page(Role role, Page page) {
        return success(roleService.findPage(role, page));
    }

    @GetMapping("/all")
    @ApiOperation(value = "获取所有角色")
    public ApiResponse<List<Role>> getRoleAll() {
        return success(roleService.findAllRole());
    }

    @PostMapping
    @ApiOperation(value = "保存角色信息")
    @PreAuthorize("hasAnyAuthority('role:add')")
    public ApiResponse<Role> save(@RequestBody @Valid Role role) {
        return success(roleService.saveOrUpdateRole(role));
    }

    @PutMapping
    @ApiOperation(value = "更新角色信息")
    @PreAuthorize("hasAnyAuthority('role:edit')")
    public ApiResponse<Role> update(@RequestBody @Valid Role role) {
        return success(roleService.saveOrUpdateRole(role));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "更新角色信息")
    @PreAuthorize("hasAnyAuthority('role:delete')")
    public ApiResponse<Boolean> delete(@PathVariable Long id) {
        return success(roleService.delete(id));
    }

    @GetMapping("/auth")
    @ApiOperation(value = "根据角色id获取权限")
    public ApiResponse<List<Long>> getRoleAuth(@RequestParam Long id) {
        return success(menuService.getMenuIdsByRole(id));
    }

    @PostMapping("/auth")
    @ApiOperation(value = "更新角色权限信息")
    @PreAuthorize("hasAnyAuthority('role:auth')")
    public ApiResponse saveRoleAuth(@RequestBody Role role) {
        roleService.saveRoleAuth(role);
        return success("更新成功");
    }

    @GetMapping("/users/{id}")
    @ApiOperation(value = "根据角色id获取角色下对应的用户")
    public ApiResponse<List<User>> findUsersByRoleId(@PathVariable Long id) {
        return success(userService.findUsersByRoleId(id));
    }


}

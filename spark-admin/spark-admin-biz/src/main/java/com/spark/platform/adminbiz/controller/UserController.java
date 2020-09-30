package com.spark.platform.adminbiz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.adminapi.dto.UserDTO;
import com.spark.platform.adminapi.entity.user.User;
import com.spark.platform.common.base.support.BaseController;
import com.spark.platform.adminbiz.service.user.UserService;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.log.annotation.ApiLog;
import com.spark.platform.common.security.util.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;


/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.controller
 * @ClassName: UserController
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
@AllArgsConstructor
public class UserController extends BaseController {

    private final UserService userService;

    @GetMapping("/principal")
    @ApiOperation(value = "获取用户DTO")
    public ApiResponse<UserDTO> getUserInfo() {
        return success(userService.getUserInfo(UserUtils.getLoginUser().getUsername()));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据用户id获取用户信息")
    public ApiResponse<User> getUserByUserId(@PathVariable Long id) {
        return success(userService.loadUserByUserId(id));
    }

    @GetMapping("/api")
    @ApiLog(ignore = true)
    @ApiOperation(value = "根据用户名获取用户信息")
    public ApiResponse<UserDTO> getUserByUserName(@RequestParam String username) {
        return success(userService.loadUserByUserName(username));
    }

    @PostMapping("/page")
    @ApiOperation(value = "获取用户列表分页")
    public ApiResponse<IPage> page(User user, Page page) {
        return success(userService.findPage(user, page));
    }

    @PostMapping
    @ApiOperation(value = "保存用户数据")
    @PreAuthorize("hasAnyAuthority('user:add')")
    public ApiResponse<User> save(@RequestBody @Valid User user) {
        return success(userService.saveUser(user));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public ApiResponse<Boolean> delete(@PathVariable Long id) {
        return success(userService.removeById(id));
    }

    @PutMapping
    @ApiOperation(value = "更新用户数据")
    @PreAuthorize("hasAnyAuthority('user:edit')")
    public ApiResponse<Boolean> update(@RequestBody User user) {
        return success(userService.updateUser(user));
    }

    @GetMapping("/rest/password")
    @ApiOperation(value = "重置密码")
    @PreAuthorize("hasAnyAuthority('user:edit')")
    public ApiResponse restPassword(@RequestParam String ids) {
        userService.restPassword(ids.split(","));
        return success("重置成功");
    }

    @PatchMapping
    @ApiOperation(value = "修改用户信息")
    public ApiResponse updateUserInfo(@RequestBody User user) {
        user.setId(UserUtils.getLoginUser().getId());
        userService.updateUserInfo(user);
        return success("修改用户信息成功");
    }

    @GetMapping("/roles/{id}")
    @ApiOperation(value = "根据用户id获取角色ids")
    public ApiResponse<List<Long>> getRolIdsByUserId(@PathVariable Long id) {
        return success(userService.findRolIdsByUserId(id));
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出用户信息")
    public void export(User user, HttpServletResponse response) throws Exception{
        userService.exportExcel(user,response);
    }
}

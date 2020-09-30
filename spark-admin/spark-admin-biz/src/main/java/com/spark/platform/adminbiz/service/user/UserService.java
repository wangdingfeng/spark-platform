package com.spark.platform.adminbiz.service.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.adminapi.dto.UserDTO;
import com.spark.platform.adminapi.entity.user.User;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.service.user
 * @ClassName: UserService
 * @Description: 用户管理
 * @Version: 1.0
 */
public interface UserService extends IService<User> {

    /**
     * 登录接口使用 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return User
     */
    UserDTO loadUserByUserName(String username);

    /**
     * 根据用户id查询用户
     *
     * @param userId 用户id
     * @return User
     */
    User loadUserByUserId(Long userId);

    /**
     * 获取用户信息
     * @return
     */
    UserDTO getUserInfo(String username);

    /**
     * 保存用户信息
     * @param user
     */
    User saveUser(User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    boolean updateUser(User user);

    /**
     * 分页查询数据
     * @param user
     * @param page
     * @return
     */
    IPage findPage(User user, Page page);

    /**
     * 批量重置密码
     * @param ids
     */
    void restPassword(String[] ids);

    /**
     * 修改用户信息
     */
    void updateUserInfo(User user);

    /**
     * 查询用户角色ids
     * @param userId 用户id
     * @return
     */
    List<Long> findRolIdsByUserId(Long userId);

    /**
     * 校验用户名
     * @param username
     * @param id
     */
    void validateUserName(String username,Long id);

    /**
     * 根据角色id查询用户
     * @param roleId
     * @return
     */
    List<User> findUsersByRoleId(Long roleId);

    /**
     * 导出excel
     * @param user 用户
     * @param response
     */
    void exportExcel(User user, HttpServletResponse response) throws Exception;
}

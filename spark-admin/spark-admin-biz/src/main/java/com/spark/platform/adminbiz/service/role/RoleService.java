package com.spark.platform.adminbiz.service.role;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.adminapi.entity.role.Role;

import java.util.List;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.service.role
 * @ClassName: RoleService
 * @Description:
 * @Version: 1.0
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据用户id查询用户的角色
     *
     * @param userId 用户id
     * @return Role
     */
    List<Role> getRoleByUserId(Long userId);

    /**
     * 分页数据
     * @param role
     * @param page
     * @return
     */
    IPage findPage(Role role, Page page);

    /**
     * 保存角色权限
     * @param role
     */
    void saveRoleAuth(Role role);

    /**
     * 获取所有的角色
     * @return
     */
    List<Role> findAllRole();
}

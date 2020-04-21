package com.spark.platform.adminbiz.dao.role;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spark.platform.adminapi.entity.role.RoleMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.dao.role
 * @ClassName: RoleMenuDao
 * @Author: wangdingfeng
 * @Description: 角色权限
 * @Date: 2020/3/20 16:36
 * @Version: 1.0
 */
@Repository
public interface RoleMenuDao extends BaseMapper<RoleMenu> {
    /**
     * 删除角色权限
     * @param roleId 角色id
     * @return
     */
    @Delete(" DELETE FROM sys_role_menu WHERE role_id=#{roleId}")
    int delteRoleAuth(@Param("roleId") Long roleId);
}

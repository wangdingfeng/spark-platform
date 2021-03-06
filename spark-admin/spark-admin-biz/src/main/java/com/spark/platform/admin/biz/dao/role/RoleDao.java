package com.spark.platform.admin.biz.dao.role;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spark.platform.admin.api.entity.role.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.dao.role
 * @ClassName: RoleDao
 * @Description:
 * @Version: 1.0
 */
@Repository
public interface RoleDao extends BaseMapper<Role> {

    /**
     * 查询角色code
     * @param id
     * @return
     */
    @Select("SELECT role_code FROM sys_role WHERE id=#{id}")
    String getRoleCode(@Param(value = "id") Long id);

    /**
     * 根据用户id查询用户的角色
     *
     * @param userId 用户id
     * @return Role
     */
    List<Role> getRoleByUserId(@Param(value = "userId") Long userId);

    /**
     * 获取所有的角色
     * @return
     */
    @Select("SELECT id,role_name FROM sys_role WHERE del_flag='0'")
    @ResultType(Role.class)
    List<Role> findAllRole();


}

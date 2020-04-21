package com.spark.platform.adminbiz.dao.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spark.platform.adminapi.entity.user.UserRole;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;

/**
 * @author: wangdingfeng
 * @Date: 2020/3/21 18:20
 * @Description:
 */
@Repository
public interface UserRoleDao extends BaseMapper<UserRole> {

    /**
     * 删除用户角色
     * @param roleId
     * @return
     */
    @Delete("DELETE FROM sys_user_role WHERE user_id=#{roleId}")
    int deleteByUserId(Long roleId);
}

package com.spark.platform.adminbiz.dao.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spark.platform.adminapi.entity.user.UserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 批量插入
     * @param userId 用户id
     * @param roleIds 角色ids
     * @return
     */
    @Insert({"<script>",
            "INSERT INTO sys_user_role (user_id,role_id) VALUES",
            "<foreach collection='roleIds' item='roleId' index='index' separator=','>",
            "(#{userId},#{roleId})",
            "</foreach>",
            "</script>"
    })
    int insertBatch(@Param("userId") Long userId,@Param("roleIds") List<Long> roleIds);
}

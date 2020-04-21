package com.spark.platform.adminbiz.dao.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spark.platform.adminapi.entity.user.User;
import com.spark.platform.adminapi.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.dao.user
 * @ClassName: UserDao
 * @Description:
 * @Version: 1.0
 */
@Repository
public interface UserDao extends BaseMapper<User> {

    /**
     * 根据用户名称查询用户
     * @param username 用户名称
     * @return User
     */
    User findByUserName(@Param(value = "username") String username);

    /**
     * 根据用户id查询用户
     * @param userId 用户id
     * @return User
     */
    User findByUserId(@Param(value = "userId") Long userId);
}

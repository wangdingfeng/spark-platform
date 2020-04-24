package com.spark.platform.adminbiz.dao.log;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spark.platform.adminapi.entity.log.LogLogin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 登录日志 Mapper 接口
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-04-18
 */
public interface LoginLogDao extends BaseMapper<LogLogin> {
    /**
     * 查询最新10条的登录日志
     * @param username 用户名
     * @return
     */
    @Select("SELECT * FROM sys_log_login WHERE username=#{username} ORDER BY login_time DESC LIMIT 10")
    List<LogLogin> findLately(@Param("username") String username);

}

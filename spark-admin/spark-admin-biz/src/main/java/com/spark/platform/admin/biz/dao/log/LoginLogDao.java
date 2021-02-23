package com.spark.platform.admin.biz.dao.log;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spark.platform.admin.api.entity.log.LogLogin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

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

    /**
     * 获取今日登陆ip数
     * @return
     */
    @Select("SELECT COUNT(DISTINCT ip) FROM sys_log_login WHERE TO_DAYS(login_time)=TO_DAYS(NOW())")
    int countTodayIP();

    /**
     * 获取近七天的统计数据
     * @return
     */
    @Select(" SELECT t1.time,COALESCE (t2.allData, 0) 'allData',COALESCE (t2.myData, 0) 'myData' FROM " +
            " (SELECT @cdate := date_add(@cdate, INTERVAL - 1 DAY) AS time,0 AS count FROM (SELECT @cdate := date_add(CURDATE(), INTERVAL + 1 DAY) FROM sys_log_login) tmp WHERE @cdate > DATE_SUB(CURDATE(), INTERVAL 7 DAY)) t1 " +
            " LEFT JOIN (SELECT DATE_FORMAT(login_time,'%Y-%m-%d') 'time',COUNT(1)  'allData',SUM(CASE WHEN username='admin' THEN 1 ELSE 0 END) 'myData' FROM sys_log_login WHERE DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= DATE(login_time) GROUP BY DATE_FORMAT(login_time,'%Y-%m-%d')) t2 " +
            " ON t1.time=t2.time")
    List<Map <String,Object>> countMapData();

}

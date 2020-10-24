package com.spark.platform.admin.biz.dao.log;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spark.platform.admin.api.entity.log.LogApi;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.dao.log
 * @ClassName: ApiLogDao
 * @Author: wangdingfeng
 * @Description: 日志Dao
 * @Date: 2020/3/24 13:17
 * @Version: 1.0
 */
@Repository
public interface ApiLogDao extends BaseMapper<LogApi> {

}

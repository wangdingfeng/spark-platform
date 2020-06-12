package com.spark.platform.quartz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.quartz.entity.JobLog;

/**
 * <p>
 * 定时任务调度日志表 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-06-09
 */
public interface JobLogService extends IService<JobLog> {

    /**
     * 分页
     * @param jobLog
     * @param page
     * @return
     */
    IPage findPage(JobLog jobLog, Page page);

}

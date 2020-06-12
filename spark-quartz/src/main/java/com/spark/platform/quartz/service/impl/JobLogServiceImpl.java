package com.spark.platform.quartz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spark.platform.quartz.dao.JobLogDao;
import com.spark.platform.quartz.entity.JobLog;
import com.spark.platform.quartz.service.JobLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定时任务调度日志表 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-06-09
 */
@Service
public class JobLogServiceImpl extends ServiceImpl<JobLogDao, JobLog> implements JobLogService {

    @Override
    public IPage findPage(JobLog jobLog, Page page) {
        return super.page(page, Wrappers.query(jobLog));
    }
}

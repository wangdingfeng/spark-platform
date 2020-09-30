package com.spark.platform.quartz.util;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.spark.platform.common.base.constants.BizConstants;
import com.spark.platform.common.base.constants.ScheduleConstants;
import com.spark.platform.common.base.utils.SpringContextHolder;
import com.spark.platform.quartz.entity.Job;
import com.spark.platform.quartz.entity.JobLog;
import com.spark.platform.quartz.service.JobLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.quartz.util
 * @ClassName: AbstractQuartzJob
 * @Author: wangdingfeng
 * @Description: 抽象quartz调用
 * @Date: 2020/6/9 15:35
 * @Version: 1.0
 */
@Slf4j
public abstract class AbstractQuartzJob implements org.quartz.Job {

    /**
     * 线程本地变量
     */
    private static ThreadLocal<Date> threadLocal = new ThreadLocal<>();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Job job = new Job();
        BeanUtils.copyProperties(context.getMergedJobDataMap().get(ScheduleConstants.TASK_PROPERTIES), job);
        try {
            before(context, job);
            if (job != null) {
                doExecute(context, job);
            }
            after(context, job, null);
        } catch (Exception e) {
            log.error("任务执行异常  - ：", e);
            after(context, job, e);
        }
    }

    /**
     * 执行前
     *
     * @param context 工作执行上下文对象
     * @param job     系统计划任务
     */
    protected void before(JobExecutionContext context, Job job) {
        log.info("定时任务：{}，开始运行---", job.getName());
        threadLocal.set(new Date());
    }

    /**
     * 执行后
     *
     * @param context 工作执行上下文对象
     * @param job     系统计划任务
     */
    protected void after(JobExecutionContext context, Job job, Exception e) {
        log.info("定时任务：{}，结束运行---", job.getName());
        Date startTime = threadLocal.get();
        threadLocal.remove();

        final JobLog jobLog = new JobLog();
        jobLog.setJobId(job.getId());
        jobLog.setJobName(job.getName());
        jobLog.setJobGroup(job.getJobGroup());
        jobLog.setInvokeTarget(job.getInvokeTarget());
        jobLog.setStartTime(startTime);
        jobLog.setEndTime(new Date());
        long runMs = jobLog.getEndTime().getTime() - jobLog.getStartTime().getTime();
        jobLog.setTimes(runMs);
        if (e != null) {
            jobLog.setStatus(BizConstants.YES.toString());
            String errorMsg = StringUtils.substring(ExceptionUtil.getMessage(e), 0, 2000);
            jobLog.setExceptionInfo(errorMsg);
        } else {
            jobLog.setStatus(BizConstants.NO.toString());
        }
        jobLog.setCreateTime(new Date());
        // 写入数据库当中
        SpringContextHolder.getBean(JobLogService.class).save(jobLog);
    }

    /**
     * 执行方法，由子类重载
     *
     * @param context 工作执行上下文对象
     * @param job     系统计划任务
     * @throws Exception 执行过程中的异常
     */
    protected abstract void doExecute(JobExecutionContext context, Job job) throws Exception;
}


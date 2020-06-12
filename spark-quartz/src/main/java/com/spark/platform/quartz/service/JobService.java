package com.spark.platform.quartz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.quartz.entity.Job;
import com.baomidou.mybatisplus.extension.service.IService;
import org.quartz.SchedulerException;

/**
 * <p>
 * 定时任务调度表 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-06-09
 */
public interface JobService extends IService<Job> {

    /**
     * 分页
     * @param job
     * @param page
     * @return
     */
    IPage findPage(Job job, Page page);

    /**
     * 保存任务
     * @return
     */
    boolean saveJob(Job job);

    /**
     * 更新任务
     * @param job
     * @return
     */
    boolean updateJob(Job job);

    /**
     * 删除任务后，所对应的trigger也将被删除
     *
     * @param jobId jobId
     */
    boolean deleteJob(Long jobId);

    /**
     * 任务调度状态修改
     *
     * @param status ScheduleConstants.Status
     * @param jobId 调度信息
     * @return 结果
     */
    boolean changeStatus(Long jobId,String status);

    /**
     * 立即运行任务
     *
     * @param jobId
     */
    void run(Long jobId);


    /**
     * 校验cron表达式是否有效
     *
     * @param cronExpression 表达式
     * @return 结果
     */
    boolean checkCronExpressionIsValid(String cronExpression);

}

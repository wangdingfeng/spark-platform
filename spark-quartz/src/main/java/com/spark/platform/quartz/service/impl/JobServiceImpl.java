package com.spark.platform.quartz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spark.platform.common.base.constants.ScheduleConstants;
import com.spark.platform.common.base.exception.BusinessException;
import com.spark.platform.common.base.exception.CommonException;
import com.spark.platform.common.base.support.WrapperSupport;
import com.spark.platform.quartz.dao.JobDao;
import com.spark.platform.quartz.entity.Job;
import com.spark.platform.quartz.service.JobService;
import com.spark.platform.quartz.util.CronUtils;
import com.spark.platform.quartz.util.ScheduleUtils;
import lombok.AllArgsConstructor;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * <p>
 * 定时任务调度表 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-06-09
 */
@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class JobServiceImpl extends ServiceImpl<JobDao, Job> implements JobService {

    private final Scheduler scheduler;

    /**
     * 项目启动时，初始化定时器
     * 主要是防止手动修改数据库导致未同步到定时任务处理（注：不能手动修改数据库ID和任务组名，否则会导致脏数据）
     */
    @PostConstruct
    public void init() throws SchedulerException, CommonException {
        List<Job> jobList = super.list();
        for (Job job : jobList) {
            updateSchedulerJob(job, job.getJobGroup());
        }
    }


    @Override
    public IPage findPage(Job job, Page page) {
        QueryWrapper wrapper = new QueryWrapper<Job>();
        WrapperSupport.putParamsLike(wrapper,job,"name");
        WrapperSupport.putParamsEqual(wrapper,job,"jobGroup","concurrent","status");
        return super.page(page, Wrappers.query(job));
    }

    /**
     * 删除任务后，所对应的trigger也将被删除
     *
     * @param jobId
     */
    @Override
    @Transactional
    public boolean deleteJob(Long jobId) {
        Job job = super.getById(jobId);
        boolean flag = super.removeById(jobId);
        if (flag) {
            try {
                scheduler.deleteJob(ScheduleUtils.getJobKey(jobId, job.getJobGroup()));
            } catch (SchedulerException e) {
                log.error("删除调度失败",e);
                throw new CommonException("删除调度失败");
            }
        }
        return flag;
    }

    @Override
    @Transactional
    public boolean changeStatus(Long jobId,String status) {
        Assert.notNull(ScheduleConstants.Status.getEnum(status),"任务状态不存在");
        boolean flag = false;
        Job job = super.getById(jobId);
        job.setStatus(status);
        if (ScheduleConstants.Status.PAUSE.getValue().equals(status)) {
            //暂停
            flag = pauseJob(job);
        } else if (ScheduleConstants.Status.NORMAL.getValue().equals(status)) {
            //开始
            flag = resumeJob(job);
        }
        return flag;
    }

    /**
     * 暂停任务
     * @param job
     * @return
     */
    private boolean pauseJob(Job job) {
        String jobGroup = job.getJobGroup();
        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        boolean flag = super.updateById(job);
        if (flag) {
            try {
                scheduler.pauseJob(ScheduleUtils.getJobKey(job.getId(), jobGroup));
            } catch (SchedulerException e) {
                log.error("暂停调度失败",e);
                throw new CommonException("暂停调度失败");
            }
        }
        return flag;
    }

    /**
     * 恢复任务
     * @param job
     * @return
     */
    private boolean resumeJob(Job job) {
        String jobGroup = job.getJobGroup();
        job.setStatus(ScheduleConstants.Status.NORMAL.getValue());
        boolean flag = super.updateById(job);
        if (flag) {
            try {
                scheduler.resumeJob(ScheduleUtils.getJobKey(job.getId(), jobGroup));
            } catch (SchedulerException e) {
                log.error(" 恢复调度失败",e);
                throw new CommonException("恢复调度失败");
            }
        }
        return flag;
    }

    @Override
    @Transactional
    public void run(Long jobId){
        Job properties = super.getById(jobId);
        String jobGroup = properties.getJobGroup();
        // 参数
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(ScheduleConstants.TASK_PROPERTIES, properties);
        try {
            scheduler.triggerJob(ScheduleUtils.getJobKey(jobId, jobGroup), dataMap);
        } catch (SchedulerException e) {
            log.error("立即运行失败",e);
            throw new CommonException("立即运行失败");
        }
    }

    @Override
    @Transactional
    public boolean saveJob(Job job) {
        if(!checkCronExpressionIsValid(job.getCronExpression())){
            throw new BusinessException("cron表达式无效");
        }
        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        boolean flag = super.save(job);
        try {
            //保存成功创建调度信息
            if (flag) {
                ScheduleUtils.createScheduleJob(scheduler, job);
            }
        } catch (SchedulerException e) {
            log.error("保存调度信息失败",e);
            throw new CommonException("保存调度信息失败");
        }
        return flag;
    }

    @Override
    @Transactional
    public boolean updateJob(Job job) {
        boolean flag = super.updateById(job);
        String jobGroup = StringUtils.isEmpty(job.getJobGroup()) ? super.getById(job.getId()).getJobGroup() : job.getJobGroup();
        try {
            if (flag) {
                updateSchedulerJob(job, jobGroup);
            }
        } catch (SchedulerException e) {
            log.error("更新调度信息失败",e);
            throw new CommonException("更新调度信息失败");
        }
        return flag;
    }

    /**
     * 更新任务
     *
     * @param job      任务对象
     * @param jobGroup 任务组名
     */
    public void updateSchedulerJob(Job job, String jobGroup) throws SchedulerException {
        // 判断是否存在
        JobKey jobKey = ScheduleUtils.getJobKey(job.getId(), jobGroup);
        if (scheduler.checkExists(jobKey)) {
            // 防止创建时存在数据问题 先移除，然后在执行创建操作
            scheduler.deleteJob(jobKey);
        }
        ScheduleUtils.createScheduleJob(scheduler, job);
    }

    /**
     * 校验cron表达式是否有效
     *
     * @param cronExpression 表达式
     * @return 结果
     */
    @Override
    public boolean checkCronExpressionIsValid(String cronExpression) {
        return CronUtils.isValid(cronExpression);
    }


}

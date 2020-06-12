package com.spark.platform.quartz.util;

import com.spark.platform.quartz.entity.Job;
import org.quartz.JobExecutionContext;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.quartz.util
 * @ClassName: QuartzJobExecution
 * @Author: wangdingfeng
 * @Description: 定时任务 允许并发执行
 * @Date: 2020/6/9 16:17
 * @Version: 1.0
 */
public class QuartzJobExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, Job job) throws Exception {
        JobInvokeUtil.invokeMethod(job);
    }
}


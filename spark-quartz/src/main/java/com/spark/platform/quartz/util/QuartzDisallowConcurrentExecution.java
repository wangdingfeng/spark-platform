package com.spark.platform.quartz.util;

import com.spark.platform.quartz.entity.Job;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.quartz.util
 * @ClassName: QuartzDisallowConcurrentExecution
 * @Author: wangdingfeng
 * @Description: 定时任务 禁止并发执行
 * @Date: 2020/6/9 16:16
 * @Version: 1.0
 */
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, Job job) throws Exception {
        JobInvokeUtil.invokeMethod(job);
    }
}

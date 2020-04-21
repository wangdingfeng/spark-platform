package com.spark.platform.flowable.biz.listener;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.fastjson.JSONObject;
import com.spark.platform.common.utils.SpringContextHolder;
import com.spark.platform.flowable.api.enums.RedisTopicName;
import com.spark.platform.flowable.api.vo.TaskVO;
import com.spark.platform.flowable.biz.service.ActTaskQueryService;
import lombok.extern.slf4j.Slf4j;
import org.flowable.task.api.Task;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.flowable.biz.listener
 * @ClassName: RunnableTask
 * @Author: wangdingfeng
 * @Description: 线程
 * @Date: 2020/4/14 11:07
 * @Version: 1.0
 */
@Slf4j
public class MessageRunnableTask implements Runnable {

    private DelegateTask delegateTask;

    MessageRunnableTask(DelegateTask delegateTask) {
        this.delegateTask = delegateTask;
    }


    @Override
    public void run() {
        getTask(delegateTask);
    }

    /**
     * 递归查询 直到查询到task任务已经落地为止，然后发布消息
     *
     * @param delegateTask
     * @return
     */
    private Task getTask(DelegateTask delegateTask) {
        ActTaskQueryService actTaskQueryService = SpringContextHolder.getBean(ActTaskQueryService.class);
        Task task = actTaskQueryService.createTaskQuery().processInstanceId(delegateTask.getProcessInstanceId()).taskAssignee(delegateTask.getAssignee()).includeProcessVariables().singleResult();
        if (null == task) {
            ThreadUtil.sleep(1000 * 40);
            log.info("休眠一分钟");
            getTask(delegateTask);
        }
        StringRedisTemplate redisTemplate = SpringContextHolder.getBean(StringRedisTemplate.class);
        TaskVO taskVO = new TaskVO();
        BeanUtil.copyProperties(task, taskVO, "variables");
        taskVO.setVariables(task.getProcessVariables());
        redisTemplate.convertAndSend(RedisTopicName.topicName, JSONObject.toJSONString(taskVO));
        return task;
    }
}

package com.spark.platform.flowable.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.flowable.api.request.TaskRequestQuery;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.task.api.history.HistoricTaskInstanceQuery;

import java.util.List;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/4 21:48
 * @Description: 历史流程service
 */
public interface ActHistTaskService {
    /**
     * 历史流程任务查询对象获取
     *
     * @return
     */
    HistoricTaskInstanceQuery createHistoricTaskInstanceQuery();

    /**
     * 通过流程实例id，查询活动任务
     *
     * @param instanceId
     * @return
     */
    HistoricTaskInstance activeTask(String instanceId);

    /**
     * 通过流程任务id，查询活动任务
     *
     * @param taskId 流程任务ID
     * @return
     */
    HistoricTaskInstance finishedTask(String taskId);

    /**
     * 通过流程实例ID，查询所有任务
     *
     * @param instanceId 流程实例ID
     * @return
     */
    List<HistoricTaskInstance> listByInstanceId(String instanceId);

    /**
     * 通过流程实例ID，分页查询所有任务
     *
     * @param instanceId 流程实例ID
     * @param start 查询页数
     * @param limit 查询数量
     * @return
     */
    List<HistoricTaskInstance> pageListByInstanceId(String instanceId, int start, int limit);

    /**
     * 分页查询用户已办任务
     * @param taskRequestQuery
     * @return
     */
    Page pageListByUser(TaskRequestQuery taskRequestQuery);

    /**
     * 查询需要过滤的历史流程
     * @param instanceId 流程实例id
     * @param filterEvents 需要过滤的节点
     * @return
     */
    List<HistoricActivityInstance> listByInstanceIdFilter(String instanceId, List<String> filterEvents);

}

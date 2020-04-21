package com.spark.platform.flowable.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.flowable.api.request.TaskRequestQuery;
import com.spark.platform.flowable.api.vo.TaskVO;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskQuery;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.task.api.history.HistoricTaskInstanceQuery;

import java.util.List;
import java.util.Map;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/4 22:34
 * @Description: 流程任务查询相关Service
 */
public interface ActTaskQueryService {
    /**
     * 创建流程任务查询对象
     *
     * @return TaskQuery  流程查询对象.
     */
    TaskQuery createTaskQuery();

    /**
     * 查询任务变量值
     *
     * @param taskId       流程任务ID.
     * @param variableName 流程变量name.
     * @return String  流程变量Value.
     */
    String findVariableByTaskId(String taskId, String variableName);

    /**
     * 查询任务变量
     *
     * @param taskId
     * @return
     */
    Map<String, Object> getVariableByTaskId(String taskId);

    /**
     * 根据流程实例ID,查询活动任务列表（多实例）
     *
     * @param processInstanceId 流程实例ID.
     */
    List<Task> processInstanceId4Multi(String processInstanceId);

    /**
     * 查询任务业务主键
     *
     * @param taskId 流程任务ID.
     * @return String  业务主键.
     */
    String findBusinessKeyByTaskId(String taskId);

    /**
     * 创建历史任务查询对象
     *
     * @return HistoricTaskInstanceQuery
     */
    HistoricTaskInstanceQuery createHistoricTaskInstanceQuery();

    /**
     * 执行流程任务
     *
     * @param taskId 流程任务ID.
     * @return Task  流程任务.
     */

    Task taskId(String taskId);

    /**
     * 执行流程任务
     *
     * @param taskId 流程任务ID.
     * @return TaskVO  流程任务自定义封装类.
     */

    TaskVO queryTaskVOById(String taskId);

    /**
     * 根据参数查询数据
     * @return
     */
    List<TaskVO> queryByParams(TaskRequestQuery taskRequestQuery);

    /**
     * 查询活动的流程任务
     *
     * @param processInstanceId 流程实例ID.
     * @return Task  流程任务.
     */
    Task processInstanceId(String processInstanceId);


    /**
     * 通过用户标识(任务候选人)，分页查询任务列表
     *
     * @param userId 用户标识.
     * @param page 当前页
     * @param pageSize 页面显示数量
     * @return List<Task>  流程任务列表.
     */
    List<Task> taskCandidateUser(String userId, int page, int pageSize);

    /**
     * 通过用户标识(实际参与者)，分页查询任务列表
     *
     * @param userId 用户标识.
     * @param page 页码
     * @param pageSize 页面显示数量
     * @return List<Task>  流程任务列表.
     */
    List<Task> taskAssignee(String userId, int page, int pageSize);


    /**
     * 通过用户标识(实际参与者、或候选人)，分页查询任务列表
     *
     * @param userId 用户标识.
     * @return List<Task>  流程任务列表.
     */
    List<Task> taskCandidateOrAssigned(String userId);

    /**
     * 通过用户标识(实际参与者、或候选人)或者用户组 查询任务列表
     *
     * @param userId 用户标识.
     * @param groupId  组id
     * @return List<Task>  流程任务列表.
     */
    List<Task> taskCandidateOrAssignedOrGroup(String userId,String groupId);

    /**
     * 通过用户标识(实际参与者、或候选人)或者用户组 查询任务列表 分页
     *
     * @param query 查询参数.
     * @return List<Task>  流程任务列表.
     */
    Page<TaskVO> taskCandidateOrAssignedOrGroupPage(TaskRequestQuery query);

    /**
     * 通过用户标识(或候选人)，分页查询任务列表
     *
     * @param userId    用户标识.
     * @param variables 查询条件map.
     * @param page
     * @param pageSize
     * @return List<Task>  分页流程任务列表.
     */
    List<Task> taskCandidateUserByCondition(String userId, Map<String, Object> variables, int page, int pageSize);

    /**
     * 通过用户标识(实际参与者)，分页查询任务列表
     *
     * @param userId    用户标识.
     * @param variables 查询条件map.
     * @param page
     * @param pageSize
     * @return List<Task>  分页流程任务列表.
     */
    List<Task> taskAssigneeByCondition(String userId, Map<String, Object> variables, int page, int pageSize);

    /**
     * 通过用户标识(实际参与者、或候选人)，分页查询任务列表
     *
     * @param userId    用户标识.
     * @param variables 查询条件map.
     * @param page
     * @param pageSize
     * @return List<Task>  分页流程任务列表.
     */
    List<Task> taskCandidateOrAssignedByCondition(String userId, Map<String, Object> variables, int page, int pageSize);

    /**
     * 通过用户标识(候选人)，统计活动任务数目
     *
     * @param userId 用户标识.
     * @return 活动任务数量.
     */
    long countTaskCandidateUser(String userId);

    /**
     * 通过用户标识(实际参与者)，统计活动任务数目
     *
     * @param userId 用户标识.
     * @return 活动任务数量.
     */
    long countTaskAssignee(String userId);

    /**
     * 通过用户标识(实际参与者、或候选人)，统计活动任务数目（用户参与的）
     *
     * @param userId 用户标识.
     * @return 活动任务数量.
     */
    long countTaskCandidateOrAssigned(String userId);

    /**
     * 通过用户标识(实际参与者、或候选人)，统计活动任务数目（用户参与的）
     *
     * @param userId 用户标识.
     * @return 活动任务数量.
     */
    long countTaskCandidateOrAssignedOrGroup(String userId,String groupId);

    /**
     * 通过用户标识(候选人)及查询条件map，统计活动任务数目（用户参与的）
     *
     * @param userId    用户标识.
     * @param variables 查询条件map.
     * @return 活动任务数量.
     */
    long countTaskCandidateUserByCondition(String userId, Map<String, Object> variables);

    /**
     * 通过用户标识(实际参与者)及查询条件map，统计活动任务数目（用户参与的）
     *
     * @param userId    用户标识.
     * @param variables 查询条件map.
     * @return 活动任务数量.
     */
    long countTaskAssigneeByCondition(String userId, Map<String, Object> variables);

    /**
     * 通过用户标识(实际参与者、候选人)及查询条件map，统计活动任务数目（用户参与的）
     *
     * @param userId    用户标识.
     * @param variables 查询条件map.
     * @return 活动任务数量.
     */
    long countTaskCandidateOrAssignedByCondition(String userId, Map<String, Object> variables);


    /**
     * 通过用户标识(实际参与者)，分页查询历史任务列表
     *
     * @param userId 用户标识.
     * @param page
     * @param pageSize
     * @return List<Task>  历史流程任务列表.
     */
    List<HistoricTaskInstance> taskAssigneeHistory(String userId, int page, int pageSize);

    /**
     * 分页查询活动任务列表
     *
     * @param assignee 用户标识（实际参与者）.
     * @param query    编程方式查询对象.
     * @return 活动任务数量.
     */
    long countTaskAssigneeByTaskQuery(String assignee, TaskQuery query);


    /**
     * 分页查询活动任务列表
     *
     * @param assignee 用户标识（实际参与者）.
     * @param query    编程方式查询对象.
     * @param page
     * @param pageSize
     * @return List<Task>  历史流程任务列表.
     */
    List<Task> taskAssigneeByTaskQuery(String assignee, TaskQuery query, int page, int pageSize);
}

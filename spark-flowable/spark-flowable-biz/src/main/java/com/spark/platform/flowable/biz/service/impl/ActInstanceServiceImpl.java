package com.spark.platform.flowable.biz.service.impl;

import cn.hutool.core.map.MapUtil;
import com.google.common.collect.Maps;
import com.spark.platform.flowable.api.enums.ActionEnum;
import com.spark.platform.flowable.api.enums.VariablesEnum;
import com.spark.platform.flowable.api.vo.TaskVO;
import com.spark.platform.flowable.biz.service.ActInstanceService;
import com.spark.platform.flowable.biz.service.ActTaskQueryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ManagementService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.impl.cmd.AddMultiInstanceExecutionCmd;
import org.flowable.engine.impl.cmd.DeleteMultiInstanceExecutionCmd;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
import org.flowable.task.api.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/4 21:44
 * @Description: 流程实例service
 */
@Service
@Slf4j
public class ActInstanceServiceImpl implements ActInstanceService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ActTaskQueryService actTaskQueryService;

    @Autowired
    private ManagementService managementService;

    @Autowired
    private IdentityService identityService;

    @Override
    public ProcessInstanceQuery createProcessInstanceQuery() {
        return runtimeService.createProcessInstanceQuery();
    }

    @Override
    public ProcessInstance processInstanceId(String processInstanceId) {
        return createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    }

    @Override
    public ProcessInstance processInstanceBusinessKey(String processInstanceBusinessKey) {
        return createProcessInstanceQuery().processInstanceBusinessKey(processInstanceBusinessKey).singleResult();
    }

    @Override
    public boolean hasProcessInstanceFinished(String processInstanceId) {
        ProcessInstance pi = processInstanceId(processInstanceId);
        return null == pi;
    }

    @Override
    public ProcessInstance taskId(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstId = task.getProcessInstanceId();
        return this.processInstanceId(processInstId);
    }

    @Override
    public ProcessInstance startProcessInstanceByKey(String processDefinitionKey) {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionKey);
        log.info("流程实例ID:{}---流程定义ID:{}", pi.getId(), pi.getProcessDefinitionId());
        return pi;
    }

    @Override
    public ProcessInstance startProcessInstanceById(String processDefinitionId, Map<String, Object> variables) {
        ProcessInstance pi = runtimeService.startProcessInstanceById(processDefinitionId, variables);
        log.info("流程实例ID:{}---流程定义ID:{}", pi.getId(), pi.getProcessDefinitionId());
        return pi;
    }

    @Override
    public ProcessInstance startProcessInstanceByKey(String processDefinitionKey,String businessKey,String businessType, String businessName, Map<String, Object> variables) {
        Assert.notNull(businessKey, "请输入业务id");
        Assert.notNull(businessType, "请输入业务类型");
        //系统常量放入variables中
        if(MapUtil.isEmpty(variables)) variables = Maps.newHashMap();
        variables.put(VariablesEnum.businessType.toString(),businessType);
        variables.put(VariablesEnum.businessName.toString(),businessName);
        ProcessInstance processInstance = this.startProcessInstanceByKey(processDefinitionKey,businessKey,variables);
        return processInstance;
    }

    @Override
    public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, Map<String, Object> variables) {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
        log.info("流程实例ID:{}---流程定义ID:{}", pi.getId(), pi.getProcessDefinitionId());
        return pi;
    }

    @Override
    public ProcessInstance startProcessInstanceByKeyAndTenantId(String processDefinitionKey, String tenantId, Map<String, Object> variables) {
        ProcessInstance pi = runtimeService.startProcessInstanceByKeyAndTenantId(processDefinitionKey, variables, tenantId);
        log.info("流程实例ID:{}---流程定义ID:{}", pi.getId(), pi.getProcessDefinitionId());
        return pi;
    }

    @Override
    public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String businessKey, Map<String, Object> variables) {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
        log.info("流程实例ID:{}---流程定义ID:{}", pi.getId(), pi.getProcessDefinitionId());
        return pi;
    }

    @Override
    public ProcessInstance startProcessInstanceByKeyAndTenantId(String processDefinitionKey, String businessKey,
                                                                String tenantId, Map<String, Object> variables) {
        ProcessInstance pi = runtimeService
                .startProcessInstanceByKeyAndTenantId(processDefinitionKey, businessKey, variables, tenantId);
        log.info("流程实例ID:{}---流程定义ID:{}", pi.getId(), pi.getProcessDefinitionId());
        return pi;
    }

    @Override
    public void action(String action, String processInstanceId) {
        ActionEnum actionEnum = ActionEnum.actionOf(action);
        switch (actionEnum){
            case SUSPEND:
                //挂起流程
                this.suspendProcessInstanceById(processInstanceId); ;break;
            case ACTIVATE:
                //激活流程
                this.activateProcessInstanceById(processInstanceId);break;
            default:
                break;
        }
    }


    @Override
    public void suspendProcessInstanceById(String processInstanceId) {
        runtimeService.suspendProcessInstanceById(processInstanceId);
        log.info("成功中断流程实例ID:{}", processInstanceId);
    }

    @Override
    public void deleteProcessInstance(String processInstanceId, String deleteReason) {
        runtimeService.deleteProcessInstance(processInstanceId, deleteReason);
        log.info("成功删除流程实例ID:{}", processInstanceId);
    }

    @Override
    public void setAuthenticatedUserId(String authenticatedUserId) {
        identityService.setAuthenticatedUserId(authenticatedUserId);
    }

    @Override
    public ProcessInstance startInstanceAndExecuteFirstTask(String processDefinitionKey, Map<String, Object> variables, Map<String, Object> actorIds) {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
        log.info("启动流程实例成功，流程实例ID:{}---流程定义ID:{}", pi.getId(), pi.getProcessDefinitionId());
        Task task = taskService.createTaskQuery().processInstanceId(pi.getProcessInstanceId()).active().singleResult();
        taskService.complete(task.getId(), actorIds);
        log.info("第一个流程任务已执行成功taskId:{}", task.getId());
        return pi;
    }

    @Override
    public void activateProcessInstanceById(String processInstanceId) {
        runtimeService.activateProcessInstanceById(processInstanceId);
        log.info("成功激活流程实例ID:{}", processInstanceId);
    }

    @Override
    public Map<String, Object> startInstanceAndExecuteFirstTask(String processDefinitionKey, String tenantId, String userId, Map<String, Object> variables) {
        ProcessInstance pi = null;
        if (StringUtils.isNotBlank(tenantId)) {
            pi = runtimeService.startProcessInstanceByKeyAndTenantId(processDefinitionKey, variables, tenantId);
        } else {
            pi = runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
        }
        String instanceId = pi.getProcessInstanceId();
        log.info("流程实例ID:{}---流程定义ID:{}", instanceId, pi.getProcessDefinitionId());
        Task task = actTaskQueryService.processInstanceId(instanceId);
        String id = task.getId();
        taskService.setAssignee(id, userId);
        taskService.setOwner(id, userId);
        task.setAssignee(userId);
        task.setOwner(userId);
        taskService.complete(id, variables);
        Task activeTask = actTaskQueryService.processInstanceId(instanceId);
        Map<String, Object> map = new HashMap<>(16);
        log.info("旧任务ID{}--新任务ID:{}", id, activeTask.getId());
        //剔除返回懒加载属性，否则json解析报错
        TaskVO taskVO = new TaskVO();
        BeanUtils.copyProperties(task,taskVO);
        TaskVO activeTaskVO = new TaskVO();
        BeanUtils.copyProperties(activeTask, activeTaskVO);

        map.put("finish", taskVO);
        map.put("active", activeTaskVO);

        return map;
    }

    @Override
    public void addMultiInstanceExecution(String activityDefId, String instanceId, Map<String, Object> variables) {
        managementService.executeCommand(new AddMultiInstanceExecutionCmd(activityDefId, instanceId, variables));
    }

    @Override
    public void deleteMultiInstanceExecution(String currentChildExecutionId, boolean flag) {
        managementService.executeCommand(new DeleteMultiInstanceExecutionCmd(currentChildExecutionId, flag));
    }
}

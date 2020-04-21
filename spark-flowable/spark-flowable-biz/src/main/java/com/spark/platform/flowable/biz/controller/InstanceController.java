package com.spark.platform.flowable.biz.controller;

import cn.hutool.core.bean.BeanUtil;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.base.support.BaseController;
import com.spark.platform.flowable.api.enums.ActionEnum;
import com.spark.platform.flowable.biz.service.ActInstanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/5 21:14
 * @Description: 流程实例
 */
@RestController
@RequestMapping("runtime/process-instances")
@Api(value = "Instance", tags = {"流程实例"})
public class InstanceController extends BaseController {

    @Autowired
    private ActInstanceService actInstanceService;


    @PostMapping
    @ApiOperation(value = "启动流程实例__通过流程定义key", notes = "实例启动成功，返回当前活动任务", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "流程定义key", required = true),
            @ApiImplicitParam(name = "businessKey", value = "业务ID(通常为当前业务主键)"),
            @ApiImplicitParam(name = "businessType", value = "业务type(通常为当前业务类型)"),
            @ApiImplicitParam(name = "businessName", value = "业务name(通常为当前业务名称)"),
    })
    public ApiResponse startByKey(@RequestParam(value = "key") String key, @RequestParam(value = "businessKey") String businessKey, @RequestParam(value = "businessType") String businessType,
                                  @RequestParam(value = "businessName") String businessName, @RequestBody Map<String, Object> variables) {
        ProcessInstance pi = actInstanceService.startProcessInstanceByKey(key, businessKey, businessType, businessName, variables);
        return success(pi.getId());
    }

    @GetMapping(value = "/{processInstanceId}")
    @ApiOperation(value = "查询", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processInstanceId", value = "流程实例ID", required = true, dataType = "String")
    })
    public ApiResponse get(@PathVariable String processInstanceId){
        Map<String,Object> objectMap = BeanUtil.beanToMap(actInstanceService.processInstanceId(processInstanceId));
        return success(objectMap);
    }

    @PutMapping(value = "/{processInstanceId}")
    @ApiOperation(value = "操作流程", notes = "suspend: 挂起流程,activate: 激活流程", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "action", value = "任务类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "processInstanceId", value = "流程实例ID", required = true, dataType = "String")
    })
    public ApiResponse actionById(@PathVariable String processInstanceId, @RequestParam("action") String action) {
        actInstanceService.action(action, processInstanceId);
        return success(ActionEnum.actionOf(action).getName());
    }

    @DeleteMapping(value = "/{processInstanceId}")
    @ApiOperation(value = "删除", notes = "删除流程", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processInstanceId", value = "流程实例ID", required = true, dataType = "String")
    })
    public ApiResponse delete(@PathVariable String processInstanceId){
        actInstanceService.deleteProcessInstance(processInstanceId,null);
        return success("删除成功");
    }

    @PostMapping(value = "/{processInstanceId}/identitylinks")
    @ApiOperation(value = "多实例加签", notes = "数据变化：act_ru_task、act_ru_identitylink各生成一条记录", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityDefId", value = "流程环节定义ID", required = true),
            @ApiImplicitParam(name = "instanceId", value = "流程实例ID", required = true)
    })
    public ApiResponse addMultiInstanceExecution(@PathVariable String processInstanceId, @RequestParam("activityDefId") String activityDefId, @RequestBody Map<String, Object> variables) {
        actInstanceService.addMultiInstanceExecution(activityDefId, processInstanceId, variables);
        return success("加签成功");
    }

    @DeleteMapping(value = "/{currentChildExecutionId}/identitylinks")
    @ApiOperation(value = "多实例减签", notes = "数据变化：act_ru_task减1、act_ru_identitylink不变", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentChildExecutionId", value = "子执行流ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "flag", value = "子执行流是否已执行", required = true, dataType = "boolean")
    })
    public ApiResponse deleteMultiInstanceExecution(@PathVariable String currentChildExecutionId, boolean flag) {
        actInstanceService.deleteMultiInstanceExecution(currentChildExecutionId, flag);
        return success("减签成功");
    }

}

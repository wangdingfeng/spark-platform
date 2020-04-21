package com.spark.platform.flowable.biz.listener;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.BaseExecutionListener;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.flowable.biz.listener
 * @ClassName: GlobalListener
 * @Author: wangdingfeng
 * @Description: 执行监听器
 * @Date: 2020/4/8 14:21
 * @Version: 1.0
 */
@Slf4j
public class GlobalListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution delegateExecution) {
        String defId = delegateExecution.getProcessDefinitionId();
        String instanceId = delegateExecution.getProcessInstanceId();
        String eventName = delegateExecution.getEventName();
        log.info("流程定义ID:{}   流程实例ID:{}", defId, instanceId);
        delegateExecution.getCurrentActivityId();
        log.debug("监听器事件名称：{}", eventName);
        switch (eventName){
            case BaseExecutionListener.EVENTNAME_START:
                log.debug("{}事件执行了start", eventName);break;
            case BaseExecutionListener.EVENTNAME_END:
                log.debug("{}事件执行了end", eventName);break;
            case BaseExecutionListener.EVENTNAME_TAKE:
                delegateExecution.setVariable("SYSTEM_JUDGE_SUBMIT_VALUE","pass");
                log.debug("{}事件执行了take", eventName);break;
            default:
                break;
        }
    }
}

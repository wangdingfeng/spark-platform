package com.spark.platform.flowable.biz;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.spark.platform.flowable.api.enums.RedisTopicName;
import com.spark.platform.flowable.api.vo.TaskVO;
import com.spark.platform.flowable.biz.service.ActTaskQueryService;
import org.flowable.engine.RuntimeService;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;


@SpringBootTest
public class SparkFlowableApplicationTests {

    @Autowired
    private ActTaskQueryService actTaskQueryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        Task task = actTaskQueryService.createTaskQuery().taskId("bbbf5cd5-902d-11ea-8325-9822efe77b1d").includeProcessVariables().singleResult();
        TaskVO taskVO = new TaskVO();
        BeanUtil.copyProperties(task,taskVO,"variables");
        taskVO.setVariables(task.getProcessVariables());
        //查询业务主键
        taskVO.setBusinessKey(runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult().getBusinessKey());
        redisTemplate.convertAndSend(RedisTopicName.topicName, JSONObject.toJSONString(taskVO));
    }

}

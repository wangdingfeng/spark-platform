package com.spark.platform.flowable.biz;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.spark.platform.flowable.api.enums.RedisTopicName;
import com.spark.platform.flowable.api.vo.TaskVO;
import com.spark.platform.flowable.biz.service.ActTaskQueryService;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SparkFlowableBizApplication.class)
public class SparkFlowableApplicationTests {

    @Autowired
    private ActTaskQueryService actTaskQueryService;

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        Task task = actTaskQueryService.createTaskQuery().taskId("3d7d3a20-7e03-11ea-8b75-8c164508dfea").includeProcessVariables().singleResult();
        TaskVO taskVO = new TaskVO();
        BeanUtil.copyProperties(task,taskVO,"variables");
        taskVO.setVariables(task.getProcessVariables());
        redisTemplate.convertAndSend(RedisTopicName.topicName, JSONObject.toJSONString(taskVO));
    }

}

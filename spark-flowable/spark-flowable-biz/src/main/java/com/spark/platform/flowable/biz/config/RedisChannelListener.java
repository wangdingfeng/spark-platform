package com.spark.platform.flowable.biz.config;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.spark.platform.common.utils.SpringContextHolder;
import com.spark.platform.flowable.api.enums.RedisTopicName;
import com.spark.platform.flowable.api.vo.TaskVO;
import com.spark.platform.flowable.biz.service.ActTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.flowable.biz.listener
 * @ClassName: MyRedisChannelListener
 * @Author: wangdingfeng
 * @Description: redis 订阅
 * @Date: 2020/4/13 15:45
 * @Version: 1.0
 */
@Component
@Slf4j
public class RedisChannelListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        //接受消息
        byte[] channel = message.getChannel();
        byte[] body = message.getBody();
        try {
            String content = new String(body, "UTF-8");
            String address = new String(channel, "UTF-8");
            log.info("接收到频道{}发来的消息:{}", address, address);
            switch (address) {
                case RedisTopicName.topicName:
                    articleMessage(content);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文章消息处理
     *
     * @param content
     */
    public void articleMessage(String content) {
        log.info("开始处理文章信息");
        TaskVO taskVO = JSONObject.parseObject(content,TaskVO.class);
        //推进任务
        boolean flag = true;
        Iterator<Map.Entry<String, Object>> it = taskVO.getVariables().entrySet().iterator();
        //判断两个主编的最终审核结果 只有当两个主编都审核通过才通过
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            if (entry.getKey().startsWith("multiInstance_result")) {
                if (!(boolean) entry.getValue()) {
                    flag = false;
                    break;
                }
            }
        }
        ActTaskService actTaskService = SpringContextHolder.getBean(ActTaskService.class);
        Map<String, Object> map = ImmutableMap.of("SYSTEM_JUDGE_SUBMIT_VALUE", "SUBMIT_APPROVAL");
        if (flag) {
            map = ImmutableMap.of("SYSTEM_JUDGE_SUBMIT_VALUE", "PASS");
        }
        actTaskService.complete(taskVO.getId(), map);
    }

}

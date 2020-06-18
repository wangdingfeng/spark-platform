package com.spark.platform.cms.config;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.spark.platform.cms.article.constant.ArticleConstant;
import com.spark.platform.cms.article.constant.ArticleProcessKeyNode;
import com.spark.platform.cms.article.entity.Article;
import com.spark.platform.cms.article.service.ArticleService;
import com.spark.platform.common.base.enums.SparkHttpStatus;
import com.spark.platform.common.base.exception.BusinessException;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.utils.SpringContextHolder;
import com.spark.platform.flowable.api.enums.ActionEnum;
import com.spark.platform.flowable.api.enums.RedisTopicName;
import com.spark.platform.flowable.api.feign.client.TaskClient;
import com.spark.platform.flowable.api.request.ExecuteTaskRequest;
import com.spark.platform.flowable.api.vo.TaskVO;
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
            if (entry.getKey().startsWith(ArticleConstant.MULTIINSTANCE_RESULT_PREFIX)) {
                if (!(boolean) entry.getValue()) {
                    flag = false;
                    break;
                }
            }
        }
        TaskClient taskClient = SpringContextHolder.getBean(TaskClient.class);
        ArticleProcessKeyNode articleProcessKeyNode = ArticleProcessKeyNode.getProcessNextKeyNodeByKey(taskVO.getTaskDefinitionKey(),flag);
        Map<String, Object> map = ImmutableMap.of(ArticleConstant.PROCESS_NODE_SYSTEM_JUDGE.toUpperCase()+ArticleConstant.SUBMIT_SUFFIX, articleProcessKeyNode.getTargetNode());
        ExecuteTaskRequest executeTaskRequest = ExecuteTaskRequest.builder().action(ActionEnum.COMPLETE.getAction()).localScope(false).variables(map).build();
        ApiResponse response = taskClient.executeTask(taskVO.getId(), executeTaskRequest);
        if(!SparkHttpStatus.SUCCESS.getCode().equals(response.getCode())){
            log.error("调用工作流失败");
            throw new BusinessException("调用工作流失败");
        }
        //更新文章状态
        ArticleService articleService = SpringContextHolder.getBean(ArticleService.class);
        Article article = new Article();
        article.setId(Long.parseLong(taskVO.getBusinessKey()));
        article.setStatus(flag == true ? ArticleConstant.STATUS_PASS : ArticleConstant.STATUS_BACK_EDIT);
        articleService.updateById(article);
        log.info("系统判断结束");
    }

}

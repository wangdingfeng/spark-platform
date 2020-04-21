package com.spark.platform.flowable.biz.config;

import com.spark.platform.flowable.api.enums.RedisTopicName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.flowable.biz.config
 * @ClassName: RedisMessageListenerConfig
 * @Author: wangdingfeng
 * @Description: redis 发布订阅配置
 * @Date: 2020/4/14 12:25
 * @Version: 1.0
 */
@Configuration
public class RedisMessageListenerConfig {

    @Bean
    MessageListenerAdapter messageListenerAdapter() {
        //放入自定义监听器
        return new MessageListenerAdapter(new RedisChannelListener());
    }

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        // articleChannel 频道名称
        container.addMessageListener(listenerAdapter, new PatternTopic(RedisTopicName.topicName));
        return container;
    }
}

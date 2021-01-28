package com.han.redismq.config;

import com.han.redismq.listener.SubscribeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration //相当于xml中的beans
public class RedisConfig
{
    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);
    public static final String TOPIC_CHAT = "TopicChat";

    /**
     * 需要手动注册RedisMessageListenerContainer加入IOC容器
     *
     * @return
     * @author lijt
     */
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory)
    {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //订阅了一个叫TopicChat 的通道
        container.addMessageListener((message, pattern) -> {
            String msg = new String(message.getBody());
            logger.info(new String(pattern) + "主题发布：" + msg);
        }, new PatternTopic(TOPIC_CHAT));
        return container;
    }

}

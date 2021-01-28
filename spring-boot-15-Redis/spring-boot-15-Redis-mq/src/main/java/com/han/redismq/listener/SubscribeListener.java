package com.han.redismq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import javax.websocket.Session;
import java.io.IOException;

/**
 * 消息订阅监听类
 *      每个客户端（ws连接）会有一个订阅监听
 */
public class SubscribeListener implements MessageListener
{

    private static final Logger logger = LoggerFactory.getLogger(SubscribeListener.class);
    private Session session;

    public Session getSession()
    {
        return session;
    }

    public void setSession(Session session)
    {
        this.session = session;
    }

    /**
     * 接收redis发布的消息
     */
    @Override
    public void onMessage(Message message, byte[] pattern)
    {
        if (null != session && session.isOpen())
        {
            try
            {
                String content = new String(message.getBody());
                session.getBasicRemote().sendText(content);
                String msg = String
                        .format("主题【%s】内容【%s】session【%s】", new String(pattern), content,
                                session.getId());
                logger.info(msg);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}

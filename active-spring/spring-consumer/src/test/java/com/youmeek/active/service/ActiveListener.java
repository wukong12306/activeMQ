package com.youmeek.active.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * Created by songyongchao on 2017/5/11.
 */
@Component
public class ActiveListener implements SessionAwareMessageListener<Message> {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Destination queue;
    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        final TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("获取到的消息： "+ textMessage.getText());  //就是实际的代码
        } catch (Exception e){
            //有了问题，返回 jms 错误信息,就是重新将这个消息发回去
            jmsTemplate.send(queue, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(textMessage.getText());
                }
            });
        }

        
    }
}

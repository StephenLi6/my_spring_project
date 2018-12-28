package com.theshy.springactivemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/12/2819:23
 * com.theshy.springactivemqMyDailyProject
 */
@Component
public class TopicProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Destination topicTextDestination;

    /**
     * 发送文本消息
     * @param text
     */
    public void sendTextMessage(final String text){
        jmsTemplate.send(topicTextDestination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(text);
            }
        });
    }

}

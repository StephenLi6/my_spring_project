package com.theshy.springactivemq;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/12/2818:51
 * com.theshy.springactivemqMyDailyProject
 */
public class Demo1Consumer implements MessageListener {

    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("spring測試接受到的數據是："+textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

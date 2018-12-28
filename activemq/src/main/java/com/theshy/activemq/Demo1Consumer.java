package com.theshy.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/12/2810:26
 * com.theshy.activemqMyDailyProject
 */
public class Demo1Consumer {
    public static void main(String[] args) throws JMSException, IOException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.130:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue theShyQueue = session.createQueue("TheShyQueue");
        MessageConsumer consumer = session.createConsumer(theShyQueue);
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("提取TheShy的消息:"+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }
}

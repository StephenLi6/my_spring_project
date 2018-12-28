package com.theshy.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/12/2810:13
 * com.theshy.activemqMyDailyProject
 */
public class Demo1Producer {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.130:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue theShyQueue = session.createQueue("TheShyQueue");
        MessageProducer producer = session.createProducer(theShyQueue);
        TextMessage textMessage = session.createTextMessage("歡迎來到神器的TheShy世界");
        producer.send(textMessage);
        producer.close();
        session.close();
        connection.close();
    }
}

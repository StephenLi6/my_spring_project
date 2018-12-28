package com.theshy.springactivemq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/12/2817:27
 * com.theshy.springactivemqMyDailyProject
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-jms-producer.xml")
public class TestQueue {
    @Autowired
    private Demo1Producer queue;

    @Autowired
    private TopicProducer producer;
    @Test
    public void testSend(){
        queue.sendTextMessage("Spring-jms-點對點發送");
    }

    @Test
    public void testSendTopic(){
        producer.sendTextMessage("Spring-jms-topic-推送");
    }
}

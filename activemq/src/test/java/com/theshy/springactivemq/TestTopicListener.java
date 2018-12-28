package com.theshy.springactivemq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/12/2819:43
 * com.theshy.springactivemqMyDailyProject
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-jms-topic-consumer.xml")
public class TestTopicListener {
    @Test
    public void testTopic(){
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

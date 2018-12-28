package com.theshy.springactivemq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/12/2819:14
 * com.theshy.springactivemqMyDailyProject
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-jms-consumer.xml")
public class TestQueuelistener {
    @Test
    public void testQueue() throws IOException {
        System.in.read();
    }
}

package com.theshy.test;

import com.theshy.config.SpringConfig;
import com.theshy.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/2316:40
 * com.theshy.testMyDailyProject
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = SpringConfig.class)
@ContextConfiguration(locations = "classpath:SpringContext.xml")
public class AOPTest {
    @Autowired
    private IAccountService a;

    @Test
    public void test1(){
        a.updateAccount(1);
    }
}

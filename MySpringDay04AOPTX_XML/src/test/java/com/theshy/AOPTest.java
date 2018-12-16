package com.theshy;

import com.theshy.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/2320:03
 * com.theshyMyDailyProject
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:SpringContext.xml")
public class AOPTest {
    @Autowired
    private IAccountService iAccountService;
    @Test
    public void test1(){
        iAccountService.transfer("aaa", "bbb", 100f);
    }
}

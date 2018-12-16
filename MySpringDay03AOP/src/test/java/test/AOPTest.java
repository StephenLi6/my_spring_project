package test;

import com.theshy.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/2220:40
 * testMyDailyProject
 */
public class AOPTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringContext.xml");
        IAccountService accountService = (IAccountService) applicationContext.getBean("accountService");
        accountService.updateAccount(1);
    }
}

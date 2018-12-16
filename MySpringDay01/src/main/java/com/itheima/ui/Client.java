package com.itheima.ui;

import com.itheima.dao.IAccountDao;
import com.itheima.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/1919:07
 * com.itheima.uiMyDailyProject
 */
public class Client {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringContext.xml");
        IAccountService iAccountService = (IAccountService) applicationContext.getBean("accountService");
        iAccountService.saveAccount();

        IAccountService iAccountService1 = applicationContext.getBean("accountService",IAccountService.class);

        IAccountService iAccountService2 = (IAccountService) applicationContext.getBean("AccountServiceImpl3");
        iAccountService2.saveAccount();
        System.out.println(iAccountService2);

    }
}

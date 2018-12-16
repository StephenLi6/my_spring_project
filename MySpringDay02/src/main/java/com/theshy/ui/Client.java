package com.theshy.ui;

import com.theshy.config.SpringConfig;
import com.theshy.service.IAccountService;
import com.theshy.service.impl.AccountServiceImpl;
import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/2110:33
 * com.theshy.uiMyDailyProject
 */
public class Client {
    public static void main(String[] args) {
        /*ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("SpringContext.xml");
        IAccountService accountService = classPathXmlApplicationContext.getBean("accountServiceImpl", AccountServiceImpl.class);
        System.out.println(accountService.findAllAccount());
        classPathXmlApplicationContext.close();*/
        ApplicationContext ac = new AnnotationConfigApplicationContext("com.theshy.config");
        IAccountService iAccountService = ac.getBean("accountServiceImpl",AccountServiceImpl.class);
        System.out.println(iAccountService.findAllAccount());
    }
}

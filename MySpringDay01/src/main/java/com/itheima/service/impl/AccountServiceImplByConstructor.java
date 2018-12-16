package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.service.IAccountService;

import java.util.Date;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/1919:06
 * com.itheima.service.implMyDailyProject
 */
public class AccountServiceImplByConstructor implements IAccountService {
    private IAccountDao accountDao;
    private String name;
    private Integer age;
    private Date birthday;

    public AccountServiceImplByConstructor( String name, Integer age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public void saveAccount() {
        //accountDao.saveAccount();
        System.out.println(name+age+birthday);
    }
}

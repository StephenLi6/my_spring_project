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
public class AccountServiceImplBySet implements IAccountService {

    private String name;
    private Integer age;
    private Date birthday;



    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void saveAccount() {
        //accountDao.saveAccount();
        System.out.println(name+age+birthday);
    }
}

package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import org.springframework.stereotype.Repository;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/1919:05
 * com.itheima.dao.implMyDailyProject
 */
@Repository
public class AccountDaoImpl implements IAccountDao {
    public void saveAccount() {
        System.out.println("保存了賬戶");
    }
}

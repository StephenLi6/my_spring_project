package com.itheima.factory;

import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountServiceImpl;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/1919:27
 * com.itheima.factoryMyDailyProject
 */
public class InstanceFactory {
    public IAccountService getAccountService(){
        return new AccountServiceImpl();
    }
}

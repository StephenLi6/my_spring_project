package com.theshy.service.impl;

import com.theshy.service.IAccountService;
import org.springframework.stereotype.Service;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/2316:33
 * com.theshy.service.implMyDailyProject
 */
@Service
public class IAccountServiceImpl implements IAccountService {

    @Override
    public void saveAccount() {
        System.out.println("保存方法執行了");
        int i= 1/0;
    }

    @Override
    public void updateAccount(int i) {
        System.out.println("更新方法執行了");
    }

    @Override
    public int deleteAccount() {
        System.out.println("刪除方法執行了");
        return 0;
    }
}

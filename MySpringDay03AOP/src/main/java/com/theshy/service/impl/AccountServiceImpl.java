package com.theshy.service.impl;

import com.theshy.service.IAccountService;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/2220:09
 * com.theshy.service.implMyDailyProject
 */
public class AccountServiceImpl  implements IAccountService {
    @Override
    public void saveAccoumt() {
        System.out.println("保存已經執行了");
    }

    @Override
    public void updateAccount(int i) {
        System.out.println("執行了update");
//        i = 1/0;
    }

    @Override
    public int deleteAccount() {
        System.out.println("執行了刪除");
        return 0;
    }
}

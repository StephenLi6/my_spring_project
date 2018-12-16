package com.theshy.service;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/2316:32
 * com.theshy.serviceMyDailyProject
 */
public interface IAccountService {
    /**
     * 模拟保存账户
     */
    void saveAccount();

    /**
     * 模拟更新账户
     * @param i
     */
    void updateAccount(int i);

    /**
     * 删除账户
     * @return
     */
    int  deleteAccount();
}

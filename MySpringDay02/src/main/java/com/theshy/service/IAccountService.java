package com.theshy.service;

import com.theshy.domain.Account;

import java.util.List;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/2110:33
 * com.theshy.serviceMyDailyProject
 */
public interface IAccountService {
    void saveAccount();

    /**
     * 查询所有
     * @return
     */
    List<Account> findAllAccount();

    /**
     * 查询一个
     * @return
     */
    Account findAccountById(Integer accountId);

    /**
     * 保存
     * @param account
     */
    void saveAccount(Account account);

    /**
     * 更新
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 删除
     * @param acccountId
     */
    void deleteAccount(Integer acccountId);


}

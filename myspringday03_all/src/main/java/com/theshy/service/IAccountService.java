package com.theshy.service;

import com.theshy.domain.Account;

import java.util.List;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/2819:24
 * com.theshy.serviceMyDailyProject
 */
public interface IAccountService {
    public void saveAccount(Account account);

    public List<Account> findAll();
}

package com.theshy.service.impl;

import com.theshy.dao.IAccountDao;
import com.theshy.domain.Account;
import com.theshy.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/2819:23
 * com.theshy.service.implMyDailyProject
 */
@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
        int i = 1/0;
    }

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }
}

package com.theshy.service.impl;

import com.theshy.dao.IAccountDao;
import com.theshy.domain.Account;
import com.theshy.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.List;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/2110:33
 * com.theshy.service.implMyDailyProject
 */
@Service
//@Scope("prototype")
public class AccountServiceImpl  implements IAccountService {
    /*@Autowired
    @Qualifier("fuckyou")*/
    @Resource(name="fuckyou")
    private IAccountDao accountDao;

    @PostConstruct
    public void first(){
        System.out.println("這是在方法執行之前");
    }

    @PreDestroy
    public void second(){
        System.out.println("這是在方法執行之后");
    }

    @Override
    public void saveAccount() {
        accountDao.SaveAccount();
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    @Override
    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer acccountId) {
        accountDao.deleteAccount(acccountId);
    }

}


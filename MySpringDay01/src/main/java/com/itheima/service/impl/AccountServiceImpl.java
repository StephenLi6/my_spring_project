package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/1919:06
 * com.itheima.service.implMyDailyProject
 * 用於創建對象的
 *            他們的作用就和xml配置文件中編寫一個<bean>標籤實現一樣的功能
 *            @Component
 *            @Service
 *            @Controller
 *            @Repository
 * 用於諸注入數據的
 *            他們的作用就和xml配置文件中的bean標籤寫一個<properties>標籤的作用是一樣的
 *            Autowired:
 *            Qualifier
 *            Resource name = ""
 *            value用於注入基本類型和String類型的屬性
 * 用於改變作用範圍的
 *              Scope
 *            他們的作用就和在bena標籤中使用scope屬性實現的功能是一樣的
 * 和生命週期相關
 *
 *            他們的作用就和在bean標籤中使用init-method和destroy-method的作用是一樣的
 */
@Service("accountService")
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountDao accountDao;

    public void saveAccount() {
        accountDao.saveAccount();
    }
}

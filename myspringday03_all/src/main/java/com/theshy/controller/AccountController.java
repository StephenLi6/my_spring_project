package com.theshy.controller;

import com.theshy.domain.Account;
import com.theshy.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/2819:46
 * com.theshy.controllerMyDailyProject
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @RequestMapping("/findAll")
    public String findAll(Model model){
        System.out.println(accountService.findAll());
        model.addAttribute("account", accountService.findAll());
        return "success";
    }

    @RequestMapping("/save")
    public String save(Account user){
       accountService.saveAccount(user);
        return "forward:/account/findAll";
    }
}

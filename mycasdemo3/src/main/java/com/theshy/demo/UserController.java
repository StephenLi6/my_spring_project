package com.theshy.demo;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2019/1/519:12
 * com.theshy.demoMyDailyProject
 */
@RestController
public class UserController {
    @RequestMapping("/findLoginUser")
    public void  findLoginUser(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(name);
    }
}


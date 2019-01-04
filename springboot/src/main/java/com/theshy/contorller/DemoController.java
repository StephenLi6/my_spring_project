package com.theshy.contorller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/12/2920:08
 * com.theshy.contorllerMyDailyProject
 */
@RestController
public class DemoController {
    @RequestMapping("/info")
    public String info(){
        return "HelloSpringBoot";
    }
}

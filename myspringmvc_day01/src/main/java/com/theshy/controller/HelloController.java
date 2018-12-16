package com.theshy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/2520:50
 * com.theshy.controllerMyDailyProject
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String sayHello(){
        System.out.println("hello SpringMVC");
        return "success";
    }

    @RequestMapping(path = "/testRequstMapping",method = RequestMethod.GET,params = "fuck",headers = "Accept")
    public String testRequestMapping(){
        System.out.println("testRequestMapping");
        return "success";
    }

    @RequestMapping(path = "/testRestful/{aa}",method = RequestMethod.GET)
    public String testRequest(@PathVariable("aa") String name){
        System.out.println(name);
        return "success";
    }
}

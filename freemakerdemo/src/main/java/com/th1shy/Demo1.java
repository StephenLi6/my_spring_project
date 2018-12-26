package com.th1shy;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/12/2619:19
 * com.th1shyMyDailyProject
 */
public class Demo1 {
    public static void main(String[] args) throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setDirectoryForTemplateLoading(new File("E:\\Mars\\workspace\\MyDailyProject\\freemakerdemo\\src\\main\\resources"));
        configuration.setDefaultEncoding("utf-8");
        Template template = configuration.getTemplate("demo1.ftl");
        Map map = new HashMap();
        map.put("name", "張三");
        map.put("message", "歡飲來到平有夠世界");
        map.put("success", false);

        List goodsList=new ArrayList();
        Map goods1=new HashMap();
        goods1.put("name", "苹果");
        goods1.put("price", 5.8);
        Map goods2=new HashMap();
        goods2.put("name", "香蕉");
        goods2.put("price", 2.5);
        Map goods3=new HashMap();
        goods3.put("name", "橘子");
        goods3.put("price", 3.2);
        goodsList.add(goods1);
        goodsList.add(goods2);
        goodsList.add(goods3);
        map.put("goodsList", goodsList);

        List alList = new ArrayList();
        alList.add("1");
        alList.add("2");
        alList.add("3");
        map.put("alList", alList);

        map.put("today", new Date());
        map.put("point", 102920122);


        Writer out = new FileWriter(new File("C:\\Users\\GoGoing.000\\Desktop\\demo1.html"));
        template.process(map, out);
        out.close();
    }
}

package com.theshy.spider.httpclient;

import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.nio.charset.Charset;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/12/220:29
 * com.theshy.spider.httpclientMyDailyProject
 */
public class HttpFluentApi {
    public static void main(String[] args) throws IOException {
        String content = Request.Get("http://www.itcast.cn").execute().returnContent().asString(Charset.forName("utf-8"));
       // System.out.println(content);

        String postResult = Request.Post("http://www.itcast.cn")
                            .bodyForm(Form.form().add("username", "zhangsan").build())
                            .execute()
                .returnContent().asString(Charset.forName("utf-8"));
        System.out.println(postResult);
    }
}

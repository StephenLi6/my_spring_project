package com.theshy.spider.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/12/220:04
 * com.theshy.spider.httpclientMyDailyProject
 */
public class HttpClientGet {
    public static void main(String[] args) throws IOException {
        String itcast = "http://www.xx40.co/";
        HttpGet httpGet = new HttpGet(itcast);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity, Charset.forName("gbk"));
        System.out.println(content);
    }
}

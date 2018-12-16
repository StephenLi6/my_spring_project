package com.theshy.spider.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.nio.charset.Charset;
import java.util.ArrayList;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/12/310:05
 * com.theshy.spider.testMyDailyProject
 */
public class HomeWork2 {
    public static void main(String[] args) throws Exception{
        String url = "http://bbs.itheima.com/forum.php?mod=viewthread&tid=411914";

        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String html = EntityUtils.toString(entity, Charset.forName("utf-8"));

        //通過jsoup解析html
        Document document = Jsoup.parse(html);
        Elements elements = document.select("tbody font[color=#ff0000] strong");
        Elements scores = document.select("tbody tr td div");
        Elements colspan = document.select("td[width=121]");
        for (Element element : colspan) {
            System.out.println(element.text());
        }
    }
}

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

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/12/39:58
 * com.theshy.spider.testMyDailyProject
 */
public class HomeWork1 {
    public static void main(String[] args) throws Exception{
            String url = "http://www.itcast.cn/";

            HttpGet httpGet = new HttpGet(url);
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String html = EntityUtils.toString(entity, Charset.forName("utf-8"));

            //通過jsoup解析html
            Document document = Jsoup.parse(html);
            Elements elements = document.select("div[class=nav_txt] ul li a");
        for (Element element : elements) {
            System.out.println(element.text());
        }
    }
}

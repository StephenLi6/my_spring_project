package com.theshy.spider.qidian;

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

import java.io.IOException;
import java.nio.charset.Charset;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/12/221:32
 * com.theshy.spider.qidianMyDailyProject
 */
public class QiDianSpider {
    public static void main(String[] args) throws IOException {
        String url = "https://read.qidian.com/chapter/_h17RCSkeXScikCo3ZPkrg2/_LrKPdZirvtMs5iq0oQwLQ2";

        while (url != null) {
             HttpGet httpGet = new HttpGet(url);
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String html = EntityUtils.toString(entity, Charset.forName("utf-8"));

            //通過jsoup解析html
            Document document = Jsoup.parse(html);
            Element title = document.select("h3[class=j_chapterName]").get(0);

            Elements message = document.select("div[class=read-content j_readContent] p");
            for (Element element : message) {
                System.out.println(element.text());
            }
            url = "http:"+document.select("a[id=j_chapterNext]").get(0).attr("href");
        }
    }
}

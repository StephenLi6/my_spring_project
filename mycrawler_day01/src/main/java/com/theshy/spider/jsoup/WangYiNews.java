package com.theshy.spider.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.MalformedURLException;
import java.net.URL;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/12/221:08
 * com.theshy.spider.jsoupMyDailyProject
 */
public class WangYiNews {
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.parse(new URL("http://www.163.com"),2000);
        Elements elements = doc.select("div[class=cm_area ns_area_top] div.bd ul a");
        for (Element element : elements) {
            System.out.println(element.text());
            System.out.println(element.attr("href"));
        }
        System.out.println("");
    }
}

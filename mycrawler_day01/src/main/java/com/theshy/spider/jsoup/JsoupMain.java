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
 * Date:2018/12/220:35
 * com.theshy.spider.jsoupMyDailyProject
 */
public class JsoupMain {
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.parse(new URL("http://www.itcast.cn"),2000);
        Elements elements = doc.select("a");

        Elements ids = doc.select("#webim_link");

        Elements classes = doc.select(".footer-bot");

        //具備某個屬性
        Elements cls = doc.select("[src]");


        //Elements

        //已某個字符開頭
        for (Element element : cls) {
            System.out.println(element);
        }
    }
}

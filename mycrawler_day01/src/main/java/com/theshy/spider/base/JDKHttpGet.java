package com.theshy.spider.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/12/219:37
 * com.theshy.spider.baseMyDailyProject
 */
public class JDKHttpGet {
    public static void main(String[] args) throws IOException {
        String zy = "http://www.itcast.cn";

        URL url = new URL(zy);
        URLConnection conn = url.openConnection();

        InputStream inputStream = conn.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,Charset.forName("UTF-8")));

        String line = null;
        while ((line = bufferedReader.readLine()) != null){
            System.out.println(line);
        }
    }
}

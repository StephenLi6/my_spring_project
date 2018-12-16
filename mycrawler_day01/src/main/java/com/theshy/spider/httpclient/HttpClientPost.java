package com.theshy.spider.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/12/220:19
 * com.theshy.spider.httpclientMyDailyProject
 */
public class HttpClientPost {
    public static void main(String[] args) throws IOException {
        String itcast = "http://www.itcast.cn";
        HttpPost httpPost = new HttpPost(itcast);
        CloseableHttpClient httpClient = HttpClients.createDefault();

        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("username", "zhangsan"));
        httpPost.setEntity(new UrlEncodedFormEntity(parameters));

        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();

        String content = EntityUtils.toString(entity, Charset.forName("utf-8"));
        System.out.println(content);
    }
}

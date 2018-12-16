package com.theshy.spider.base;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/12/219:54
 * com.theshy.spider.baseMyDailyProject
 */
public class JDKHttpPost {
    public static void main(String[] args) throws Exception{
        String zy = "http://www.itcast.cn";

        URL url = new URL(zy);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");

        conn.setDoOutput(true);
        // 3. 发送数据  useranem=zhangsan&password=lisi
        OutputStream outputStream = conn.getOutputStream();
        outputStream.write("useranem=zhangsan&password=lisi".getBytes());
        outputStream.flush();
        outputStream.close();

        // 4. 得到一个响应的数据，输入流（html文档的二进制文件，字符流）
        InputStream inputStream = conn.getInputStream();
        // 5. 打印结果（bufferreader 一行一行的读取）
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("utf-8")));
        String line = null;
        while((line = bufferedReader.readLine())!=null){
            System.out.println(line);
        }
    }
}

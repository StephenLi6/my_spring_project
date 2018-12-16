package com.theshy.spider.day02;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
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
import java.util.ArrayList;
import java.util.List;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/12/48:58
 * com.theshy.spider.day02MyDailyProject
 */
public class JDDemo1 {
    public static void main(String[] args) throws Exception{
        ProductDao productDao = new ProductDao();


        for (int i = 1; i <= 1000; i++) {
            String url = "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=%E6%89%8B%E6%9C%BA&cid2=653&cid3=655&page="+(2*i-1);
            //通過jsoup解析html
            Document document = Jsoup.parse(parserIndex(url));
            Elements elements = document.select("#J_goodsList li[data-pid]");
            for (Element element : elements) {
                //https://item.jd.com/100001172674.html
                String id = element.attr("data-pid");
                String pUrl = "https://item.jd.com/" + id + ".html";
                Document document2 = Jsoup.parse(parserIndex(pUrl));
                //封裝Product對象
                Product product = new Product();
                Elements informations = document2.select("ul[class=parameter2 p-parameter-list] li");
                //封裝商品名稱對象
                for (Element information : informations) {
                    if (information.text().contains("商品名称")) {
                        String name = information.attr("title");
                        product.setName(name);
                        break;
                    }
                }
                //封裝手機品牌
               /* Elements elements1 = document2.select("div[class=hide data-tab=itme] div[class=Ptable-item] dl dl[class=clearfix] dd");
                System.out.println(elements1);
                for (int i = 0; i < elements1.size(); i++) {
                    if (i == 0){
                        continue;
                    }
                    product.setXinghao(elements1.text());
                    break;
                }*/

                //封裝型號
                String pingpai = document2.select("ul[id=parameter-brand] li a").text();
                product.setPinpai(pingpai);

                //封裝標題
                String title = document2.select(".sku-name").text();
                product.setTitle(title);
                //封裝pid和rul
                product.setId(id);
                product.setUrl(pUrl);

                //封裝Ajax中的價格和賣點信息！
                String priceUrl = "http://p.3.cn/prices/mgets?&type=1&area=1_72_2799_0&pdtk=&pduid=2098855974&pdpin=&pin=null&pdbp=0&ext=11000000&source=item-pc&skuIds=J_"+id;
                String priceJson = parserIndex(priceUrl);
                    try {
                        List<JSONObject> object = JSON.parseObject(priceJson,ArrayList.class);
                        product.setPrice(object.get(0).getString("p"));
                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                    String detailUrl = "https://cd.jd.com/promotion/v2?area=1_72_2799_0&shopId=1000000127&venderId=1000000127&cat=9987%2C653%2C655&isCanUseDQ=isCanUseDQ-0&isCanUseJQ=isCanUseJQ-0&platform=0&orgType=2&jdPrice=5599.00&appid=1&_=1543889879481&skuId=" + id;
                HttpGet maidianHttpGet = new HttpGet(detailUrl);
                CloseableHttpClient maidianHttpClient = HttpClients.createDefault();
                CloseableHttpResponse maidianRes = maidianHttpClient.execute(maidianHttpGet);
                if (200 == maidianRes.getStatusLine().getStatusCode()) {
                    String detailJson = EntityUtils.toString(maidianRes.getEntity(), Charset.forName("gbk"));
                    JSONObject object = JSON.parseObject(detailJson);
                    try {
                        List<JSONObject> object2 = JSON.parseObject(object.getString("ads"), ArrayList.class);
                        product.setMaidian(object2.get(0).getString("ad"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                productDao.saveProduct(product);
                System.out.println(product);
            }
        }
        }

    private static String parserIndex(String indexUrl) throws Exception {
        //获取首页的html文档字符串
        String indexHtml = getHtml(indexUrl);
        //获取主页的商品信息
        return indexHtml;
    }

    private static String getHtml(String indexUrl) throws IOException, ClientProtocolException {
        // 2.将url对象封装成httpget对象
        HttpGet indexHttpGet = new HttpGet(indexUrl);
        // 3.使用httpclient发起一个请求
        CloseableHttpClient indexHttpClient = HttpClients.createDefault();
        CloseableHttpResponse indexRes = indexHttpClient.execute(indexHttpGet);
        // 4.从响应结果中，获得首页的html文档
        if (200 == indexRes.getStatusLine().getStatusCode()) {
            // 5.获得首页的信息，从首页中找出商品的列表
            return EntityUtils.toString(indexRes.getEntity(), Charset.forName("utf-8"));
        }
        return null;
    }
}

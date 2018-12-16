package com.theshy.spider.day02;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.ParseException;
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

import com.google.gson.Gson;

/**
 * 第一个事情：解析首页的信息，得到商品列表 
 * 第二个事情：解析分页的信息，得到商品列表
 * 
 * @author maoxiangyi
 *
 */
public class JdSpider {
	private final static ArrayBlockingQueue<String> ARRAY_BLOCKING_QUEUE = new ArrayBlockingQueue<>(1000);
	private final static ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);
	private final static ProductDao PRODUCT_DAO = new ProductDao();


	public static void main(String[] args) throws Exception {
		//使用線程技術消費對隊列的數據
		EXECUTOR_SERVICE.execute(new Runnable() {
			@Override
			public void run() {
				while (true){
					System.out.println("當前線程剩餘爬取數量"+ARRAY_BLOCKING_QUEUE.size());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		for (int i = 0; i < 10; i++) {
			EXECUTOR_SERVICE.execute(new Runnable() {
				@Override
				public void run() {
					while (true){
						try {
							String pid = ARRAY_BLOCKING_QUEUE.take();
							parserProductDetail(pid);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			});
		}

		// 第一个事情：解析首页的信息，得到商品列表
		parserIndex();
		// 第二个事情：解析分页的信息，得到商品列表
		dopaging();
	}

	private static void dopaging() throws Exception {
		int page = 2;
		while (page <= 100) {
			String url = "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=%E6%89%8B%E6%9C%BA&cid2=653&cid3=655&page="
					+ (2 * page - 1);
			String pagingResult = getHtml(url);
			getSearchResultInfo(pagingResult);
			page++;
		}
	}

	private static void parserIndex() throws Exception {
		// 1.指定url
		String indexUrl = "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&wq=%E6%89%8B%E6%9C%BA&pvid=4462d633d7774a1dafc55419260fae59";
		//获取首页的html文档字符串
		String indexHtml = getHtml(indexUrl);
		//获取主页的商品信息
		getSearchResultInfo(indexHtml);
	}

	private static void getSearchResultInfo(String indexHtml) {
		if (indexHtml != null) {
			Document indexDoc = Jsoup.parse(indexHtml);
			// 6.定位到商品列表
			Elements liList = indexDoc.select("#J_goodsList li[data-pid]");
			for (Element li : liList) {
				// 7.依次每个商品的详情页，并解析出数据
				try {
					ARRAY_BLOCKING_QUEUE.put(li.attr("data-pid"));
				} catch (Exception e) {
					System.out.println("商品url访问失败！   " + li.attr("data-pid") + e);
				}
			}
		}
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

	private static void parserProductDetail(String pId) throws Exception {
		// 1.指定url
		// https://item.jd.com/3367822.html
		String pUrl = "https://item.jd.com/" + pId + ".html";
		// 2.封装成一个get请求
		HttpGet httpGet = new HttpGet(pUrl);
		// 3.使用httpclient发起请求
		CloseableHttpClient detailHttpClient = HttpClients.createDefault();
		CloseableHttpResponse detailRes = detailHttpClient.execute(httpGet);
		// 4.得到响应结果
		if (200 == detailRes.getStatusLine().getStatusCode()) {
			String detailHtml = EntityUtils.toString(detailRes.getEntity(), Charset.forName("utf-8"));
			// 5.解析文档
			Document detailDoc = Jsoup.parse(detailHtml);
			// 6.一次解析出我们想要的数据
			Product product = getProductInfo(detailDoc);
			product.setId(pId);
			product.setUrl(pUrl);
			
			// 7. 补全价格信息
			// 1）指定url，2）封装httpget请求 3）发起期请求 4） 得到值
			String priceUrl = "http://p.3.cn/prices/mgets?&type=1&area=1_72_2799_0&pdtk=&pduid=2098855974&pdpin=&pin=null&pdbp=0&ext=11000000&source=item-pc&skuIds=J_" + pId;
			HttpGet priceHttpGet = new HttpGet(priceUrl);
			CloseableHttpClient priceHttpClient = HttpClients.createDefault();
			CloseableHttpResponse priceRes = priceHttpClient.execute(priceHttpGet);
			if (200 == priceRes.getStatusLine().getStatusCode()) {
				String priceJson = EntityUtils.toString(priceRes.getEntity(), Charset.forName("utf-8"));
				// [{"op":"1999.00","m":"3000.00","id":"J_3367822","p":"1999.00"}]
				// Gson 谷歌提欧专用于解析json，将json串转化成一个对象。
				// 使用必须导入pom依赖。
				Gson gson = new Gson();
				ArrayList<Map> resultList = gson.fromJson(priceJson, ArrayList.class);
				Map<String, String> map = (Map<String, String>) resultList.get(0);
				// 获取价格数据
				String price = map.get("op");
				product.setPrice(price);
			}
			
			// 8. 补全卖点信息
			// 1）指定url，2）封装httpget请求 3）发起期请求 4） 得到值
			String maidianUrl = "https://cd.jd.com/promotion/v2?area=1_72_2799_0&shopId=1000000127&venderId=1000000127&cat=9987%2C653%2C655&isCanUseDQ=isCanUseDQ-0&isCanUseJQ=isCanUseJQ-0&platform=0&orgType=2&jdPrice=5599.00&appid=1&_=1543889879481&skuId=" + pId;
			HttpGet maidianHttpGet = new HttpGet(maidianUrl);
			CloseableHttpClient maidianHttpClient = HttpClients.createDefault();
			CloseableHttpResponse maidianRes = maidianHttpClient.execute(maidianHttpGet);
			if (200 == maidianRes.getStatusLine().getStatusCode()) {
				String maindianJson = EntityUtils.toString(maidianRes.getEntity(), Charset.forName("gbk"));
				// {"quan":{"actUrl":"https://sale.jd.com/act/7lAdi60QwSbs.html","title":"消费满10元返配件优惠券","activityId":8826},"ads":[{"id":"AD_100000651175","ad":"K1现货开售，享3期免息，购机送自拍杆，千元屏下指纹解锁，2500万AI智慧美颜<a href='http://item.jd.com/100000651175.html?from_saf=1' target='_blank'>购买K1请点击</a>"}],"skuCoupon":[],"adsStatus":200,"quanStatus":200,"couponLimit":[{"cid":655,"sku":100000651175,"global":false,"limitCouponDesc":"不可使用东券","venderId":0,"isCanUseDong":-1,"isCanUseJing":-1,"limitCouponType":[]}],"promStatus":200,"prom":{"hit":0,"ending":0,"carGift":0,"tags":[{"st":"1538143242","code":"10","gifts":[{"gs":2,"nm":"OPPO时尚单品自拍杆ZP107 晴空蓝","sid":"3146254","ss":1,"gt":2,"mp":"jfs/t2599/61/3100666573/36023/be6e0a1d/577f5aa0N8a0043cb.jpg","num":1}],"pid":"236521502_4","content":"（赠完即止）","tr":6071206,"d":"1546264801","name":"赠品","customtag":{"1":"赠品"}}],"etg":"5","giftPool":[],"pickOneTag":[]}}
				// Gson 谷歌提欧专用于解析json，将json串转化成一个对象。
				// 使用必须导入pom依赖。
				Gson gson = new Gson();
				Map<String, Object> map = gson.fromJson(maindianJson, Map.class);
				// 获取价格数据
				List<Map<String, String>> adsList = (List<Map<String, String>>) map.get("ads");
				Map<String, String> ads = adsList.get(0);
				String maidian = ads.get("ad");
				product.setMaidian(maidian);
			}

			PRODUCT_DAO.saveProduct(product);
			System.out.println(product);
			
			Thread.sleep(1000);
		}

	}

	private static Product getProductInfo(Document detailDoc) {
		/**
		 * private String name; private String title; private String price;
		 * private String maidian; private String pinpai; private String
		 * xinghao;
		 */
		Product product = new Product();
		// 获取商品名称
//		String name = detailDoc.select("[class=parameter2 p-parameter-list] li").get(0).text();
//		product.setName(name);
		Elements informations = detailDoc.select("[class=parameter2 p-parameter-list] li");
		for (Element information : informations) {
			if (information.text().contains("商品名称")) {
				String name = information.attr("title");
				product.setName(name);
			}
		}
		// 获取标题
		String title = detailDoc.select(".sku-name").get(0).text();
		product.setTitle(title);
		// // // 获取卖点信息-----------------拿不到卖点信息？？？？？？？
		// String maidian = detailDoc.select("#p-ad").get(0).attr("title");
		// product.setMaidian(maidian);
		// 补全其它信息…………………………………………………………………………………………
		return product;
	}
}

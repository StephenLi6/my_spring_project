package com.theshy.spider.day02;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/12/420:33
 * com.theshy.spider.day02MyDailyProject
 */
public class Json {
    public static void main(String[] args) {
        String json = "{\n" +
                "\t\"quan\": {\n" +
                "\t\t\"actUrl\": \"https://sale.jd.com/act/7lAdi60QwSbs.html\",\n" +
                "\t\t\"title\": \"消费满10元返配件优惠券\",\n" +
                "\t\t\"activityId\": 10836\n" +
                "\t},\n" +
                "\t\"ads\": [{\n" +
                "\t\t\"id\": \"AD_100001906474\",\n" +
                "\t\t\"ad\": \"【X23幻彩版抢购】6.41寸灵动水滴屏，2480万超大广角AI相机，屏幕指纹，面部识别！<a href='https://sale.jd.com/act/YwJvQOrbPKaLE2.html' target='_blank'>点击进入更多优惠、购机攻略</a>\"\n" +
                "\t}],\n" +
                "\t\"skuCoupon\": [],\n" +
                "\t\"adsStatus\": 200,\n" +
                "\t\"quanStatus\": 200,\n" +
                "\t\"couponLimit\": [{\n" +
                "\t\t\"sku\": 100001906474,\n" +
                "\t\t\"venderId\": 1000000127,\n" +
                "\t\t\"limitCouponDesc\": \"不可使用京券东券\",\n" +
                "\t\t\"isCanUseDong\": 0,\n" +
                "\t\t\"cid\": 655,\n" +
                "\t\t\"global\": false,\n" +
                "\t\t\"isCanUseJing\": 0,\n" +
                "\t\t\"jdPrice\": 5599,\n" +
                "\t\t\"limitCouponType\": []\n" +
                "\t}],\n" +
                "\t\"promStatus\": 200,\n" +
                "\t\"prom\": {\n" +
                "\t\t\"hit\": 0,\n" +
                "\t\t\"pickOneTag\": [],\n" +
                "\t\t\"carGift\": 0,\n" +
                "\t\t\"tags\": [{\n" +
                "\t\t\t\"st\": \"1543807800\",\n" +
                "\t\t\t\"code\": \"80\",\n" +
                "\t\t\t\"gifts\": [{\n" +
                "\t\t\t\t\"gs\": 1,\n" +
                "\t\t\t\t\"nm\": \"乐心智能手环\",\n" +
                "\t\t\t\t\"sid\": \"4839204\",\n" +
                "\t\t\t\t\"ss\": 1,\n" +
                "\t\t\t\t\"gt\": 2,\n" +
                "\t\t\t\t\"mp\": \"jfs/t10807/297/1706028944/113861/1d0b4744/59e5516bNed4c4bf8.jpg\",\n" +
                "\t\t\t\t\"num\": 1\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"gs\": 2,\n" +
                "\t\t\t\t\"nm\": \"熊本雨伞\",\n" +
                "\t\t\t\t\"sid\": \"100000151925\",\n" +
                "\t\t\t\t\"ss\": 1,\n" +
                "\t\t\t\t\"gt\": 2,\n" +
                "\t\t\t\t\"mp\": \"jfs/t24058/24/2670337577/128611/f3c2613f/5b89fdafN1c9387d3.jpg\",\n" +
                "\t\t\t\t\"num\": 1\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"gs\": 1,\n" +
                "\t\t\t\t\"nm\": \"5000mAh移动电源 赠品（两款型号随机发货）\",\n" +
                "\t\t\t\t\"sid\": \"4877535\",\n" +
                "\t\t\t\t\"ss\": 1,\n" +
                "\t\t\t\t\"gt\": 2,\n" +
                "\t\t\t\t\"mp\": \"jfs/t9163/188/5483922/81456/cc8ec74c/599e9d5aN3b1321a7.jpg\",\n" +
                "\t\t\t\t\"num\": 1\n" +
                "\t\t\t}],\n" +
                "\t\t\t\"pid\": \"247178033_4\",\n" +
                "\t\t\t\"content\": \"（PLUS会员专享，赠完即止）\",\n" +
                "\t\t\t\"tr\": 2345469,\n" +
                "\t\t\t\"d\": \"1546271999\",\n" +
                "\t\t\t\"name\": \"PLUS赠品\",\n" +
                "\t\t\t\"customtag\": {\n" +
                "\t\t\t\t\"1\": \"赠品\"\n" +
                "\t\t\t}\n" +
                "\t\t}, {\n" +
                "\t\t\t\"st\": \"1543629600\",\n" +
                "\t\t\t\"code\": \"10\",\n" +
                "\t\t\t\"gifts\": [{\n" +
                "\t\t\t\t\"gs\": 1,\n" +
                "\t\t\t\t\"nm\": \"乐心智能手环\",\n" +
                "\t\t\t\t\"sid\": \"4839204\",\n" +
                "\t\t\t\t\"ss\": 1,\n" +
                "\t\t\t\t\"gt\": 2,\n" +
                "\t\t\t\t\"mp\": \"jfs/t10807/297/1706028944/113861/1d0b4744/59e5516bNed4c4bf8.jpg\",\n" +
                "\t\t\t\t\"num\": 1\n" +
                "\t\t\t}],\n" +
                "\t\t\t\"pid\": \"247042703_4\",\n" +
                "\t\t\t\"content\": \"（赠完即止）\",\n" +
                "\t\t\t\"tr\": 2467870,\n" +
                "\t\t\t\"d\": \"1546394400\",\n" +
                "\t\t\t\"name\": \"赠品\",\n" +
                "\t\t\t\"customtag\": {\n" +
                "\t\t\t\t\"1\": \"赠品\"\n" +
                "\t\t\t}\n" +
                "\t\t}],\n" +
                "\t\t\"giftPool\": [],\n" +
                "\t\t\"ending\": 0\n" +
                "\t}\n" +
                "}";
        JSONObject object = JSON.parseObject(json);
        List<JSONObject> object2 = JSON.parseObject(object.getString("ads"), ArrayList.class);
        System.out.println(object2.get(0).getString("ad"));
    }
}

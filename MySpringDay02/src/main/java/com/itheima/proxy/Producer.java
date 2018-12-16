package com.itheima.proxy;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/2219:25
 * com.itheima.proxyMyDailyProject
 */
public class Producer implements IProducer{
    @Override
    public void saleProduct(float money) {
        System.out.println("銷售產品，並拿到"+money);
    }

    @Override
    public void afterService(float money) {
        System.out.println("提供售後，並拿到"+money);
    }
}

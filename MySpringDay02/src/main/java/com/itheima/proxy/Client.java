package com.itheima.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/2219:25
 * com.itheima.proxyMyDailyProject
 */
public class Client {
    public static void main(String[] args) {
        Producer producer = new Producer();

        IProducer proxyProducer = (IProducer) Proxy.newProxyInstance(producer.getClass().getClassLoader(),
                producer.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("before");
                        if ("saleProduct".equals(method.getName())){
                                 System.out.println("after");
                            return  method.invoke(producer,(Float)(args[0])*0.8f);
                        }else{
                            return method.invoke(producer,args[0]);
                        }
                    }
                });
        proxyProducer.saleProduct(1000);
        proxyProducer.afterService(100);
    }
}

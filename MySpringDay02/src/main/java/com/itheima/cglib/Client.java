package com.itheima.cglib;

import com.itheima.proxy.Producer;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/2219:47
 * com.itheima.cglibMyDailyProject
 */
public class Client {
    public static void main(String[] args) {

    Producer producer = new Producer();

    Producer cglibProducer = (Producer) Enhancer.create(producer.getClass(), new MethodInterceptor() {
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            //提供增强的代码
            Object returnValue = null;

            //1.获取方法执行的参数
            Float money = (Float)objects[0];
            //2.判断当前方法是不是销售
            if("saleProduct".equals(method.getName())) {
                returnValue = method.invoke(producer, money*0.8f);
            }
            return returnValue;
        }
    });
    cglibProducer.saleProduct(1000);
    cglibProducer.afterService(100);
    }
}

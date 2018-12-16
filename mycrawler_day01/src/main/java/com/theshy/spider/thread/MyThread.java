package com.theshy.spider.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/12/610:55
 * com.theshy.spider.threadMyDailyProject
 */
public class MyThread {
    public static void main(String[] args) {
  /*      new Thread(new Thread(){
            @Override
            public  void run(){
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                while (true){
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();*/

        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            public Integer call() throws Exception {
                int i = new Random().nextInt(1000);
                System.out.println(Thread.currentThread().getName() +"+++++++"+ i);
                return i;
            }
        });

        new Thread(futureTask).start();

        // 获取返回值
        try {
            Thread.sleep(5000);// 可能做一些事情
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

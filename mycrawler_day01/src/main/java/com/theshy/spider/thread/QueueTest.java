package com.theshy.spider.thread;

import java.util.ArrayDeque;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/12/614:45
 * com.theshy.spider.threadMyDailyProject
 */
public class QueueTest {
    public static void main(String[] args) {
        test1();
        final ArrayDeque<String> arrayDeque = new ArrayDeque<String>();
        for (int i = 0; i <= 99; i++) {
            arrayDeque.offer("element" + i);
        }

        // for (String element : arrayDeque) {
        // System.out.println(element);
        // }

        new Thread(new Runnable() {

            public void run() {
                while (true) {
                    String element = arrayDeque.poll();
                    System.out.println("线程编号："+Thread.currentThread().getId() +"  "+element);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        new Thread(new Runnable() {

            public void run() {
                while (true) {
                    String element = arrayDeque.poll();
                    System.out.println("线程编号："+Thread.currentThread().getId() +"  "+element);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        }).start();

    }

    private static void test1() {
        ArrayDeque<String> arrayDeque = new ArrayDeque<String>();
        // 插入一个元素
        arrayDeque.offer("element01");
        arrayDeque.offer("element02");
        arrayDeque.offer("element03");
        arrayDeque.offer("element04");
        // 获取元素
        String element = arrayDeque.poll();
        System.out.println(element);
        element = arrayDeque.poll();
        System.out.println(element);
    }
}

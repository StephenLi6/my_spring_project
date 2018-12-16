package com.theshy.spider.thread;

import java.util.concurrent.ArrayBlockingQueue;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/12/614:57
 * com.theshy.spider.threadMyDailyProject
 */
public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        final ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(100);

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    // remove poll take(线程安全)
                    String element;
                    try {
                        element = arrayBlockingQueue.take(); //如果阻塞队列中没有元素，线程在此处阻塞
                        System.out.println("线程编号1"+element);

                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    // remove poll take(线程安全)
                    String element;
                    try {
                        element = arrayBlockingQueue.take();
                        System.out.println("线程编号2"+element);

                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        // 创建阻塞队列
        for (int i = 0; i <= 200; i++) {
            // add offer put（线程安全的）
            arrayBlockingQueue.put("element" + i);
            System.out.println("put element " + i);
        }

    }
}

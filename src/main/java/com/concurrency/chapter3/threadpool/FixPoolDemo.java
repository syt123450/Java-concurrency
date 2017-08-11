package com.concurrency.chapter3.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ss on 2017/8/5.
 */
public class FixPoolDemo {

    public static class DemoTask implements Runnable {

        @Override
        public void run() {
            //Thread.currentThread().getId()可以输出当前线程在JVM中的ID
            System.out.println(System.currentTimeMillis() + ":Thread ID: " + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        DemoTask demoTask = new DemoTask();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            //同一个任务可以被多次提交
            executorService.submit(demoTask);
        }
        //executorService 如果不shutdown的话程序不会停止
        executorService.shutdown();
    }
}

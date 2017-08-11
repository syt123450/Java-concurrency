package com.concurrency.chapter2;

/**
 * Created by ss on 2017/7/15.
 */
public class TestJoin {

    public volatile static int i = 0;

    public static class adder implements Runnable{

        @Override
        public void run() {
            for (i = 0; i < 100; i++);
        }
    }

    public static void main(String[] args) {
        Thread adderThread = new Thread(new adder());
        adderThread.start();

        //join也需要检查是否被中断
        try {
            adderThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i);
    }
}

package com.concurrency.chapter2;

import java.util.HashMap;

/**
 * Created by ss on 2017/7/16.
 */
public class BadHashMap {

    //hashMap不是线程安全的，如果多个线程一起写的话有可能会出现问题
    private static HashMap<Integer, Integer> map = new HashMap<>();

    public static class Writer implements Runnable {

        private int start;

        public Writer(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < 10000; i += 2) {
                map.put(i, i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Writer(0));
        Thread thread2 = new Thread(new Writer(1));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(map.size());
    }
}

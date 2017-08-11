package com.concurrency.chapter2;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by ss on 2017/7/16.
 */
public class BadArrayList implements Runnable {

    //使用线程安全的vector就可以避免这个问题
    private static ArrayList<Integer> commonList = new ArrayList<>();

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            commonList.add(1);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new BadArrayList());
        Thread thread2 = new Thread(new BadArrayList());

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(commonList.size());
    }
}

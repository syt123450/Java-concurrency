package com.concurrency.chapter2;

/**
 * Created by ss on 2017/7/16.
 */
public class AccountingSync2 implements Runnable {

    private static int i = 0;

    //锁住静态方法也是锁住了整个class，这个效果和锁住class的静态成员变量是一个道理
    private static synchronized void addi() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            addi();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new AccountingSync2());
        Thread thread2 = new Thread(new AccountingSync2());

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(i);
    }
}

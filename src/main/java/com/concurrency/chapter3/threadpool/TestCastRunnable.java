package com.concurrency.chapter3.threadpool;

/**
 * Created by ss on 2017/8/5.
 */
public class TestCastRunnable {

    public static class TestTask implements Runnable {

        public int name = 1;

        @Override
        public void run() {

        }
    }

    public static void main(String[] args) {

        Runnable task = new TestTask();
        System.out.println(((TestTask) task).name);
    }
}

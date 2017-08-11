package com.concurrency.chapter2;

/**
 * Created by ss on 2017/7/15.
 */
public class TestThread extends Thread {

    public void testFunction() {
        System.out.println("In function.");
    }

    public void Run() {
        System.out.println("run");
    }

    public static void main(String[] args) {
        TestThread testThread = new TestThread();
        testThread.testFunction();
    }
}

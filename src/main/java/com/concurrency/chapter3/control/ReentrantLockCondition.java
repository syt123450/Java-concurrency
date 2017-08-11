package com.concurrency.chapter3.control;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ss on 2017/7/20.
 */
public class ReentrantLockCondition implements Runnable {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println("Begin to wait.");
            condition.await();
            System.out.println("Continue to execute.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new ReentrantLockCondition());

        thread.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {}

        lock.lock();
        System.out.println("Come into main.");
        condition.signal();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {}
        System.out.println("Main release lock.");
        lock.unlock();
    }
}

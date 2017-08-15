package com.concurrency.chapter6.stampLock;

import java.util.concurrent.locks.StampedLock;

/**
 * Created by ss on 2017/8/14.
 */
public class StampedLockDemo {

    private double x, y;
    private final StampedLock stampedLock = new StampedLock();

    public void move(double deltaX, double deltaY) {

        long stamp = stampedLock.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    public double distanceFromOrigin() {

        long stamp = stampedLock.tryOptimisticRead();
        double currentX = x, currentY = y;
        if (!stampedLock.validate(stamp)) {
            stamp = stampedLock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }

        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    public static void main(String[] args) {

        StampedLockDemo stampedLockDemo = new StampedLockDemo();
        stampedLockDemo.move(12, 14);
        System.out.println(stampedLockDemo.distanceFromOrigin());
    }
}

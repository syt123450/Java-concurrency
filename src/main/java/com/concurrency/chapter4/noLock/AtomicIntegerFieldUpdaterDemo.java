package com.concurrency.chapter4.noLock;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by ss on 2017/8/6.
 */


//score for a candidate

public class AtomicIntegerFieldUpdaterDemo {

    public static final AtomicIntegerFieldUpdater<Candidate> scoreUpdater = AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");
    public static AtomicInteger allScore = new AtomicInteger(0);
    private static final Candidate stu = new Candidate();

    public static class Candidate {
        int id;
        //使用updater的int域必须是volatile的
        volatile int score;
    }

    public static class Voter implements Runnable {

        @Override
        public void run() {
            if (Math.random() > 0.4) {
                scoreUpdater.incrementAndGet(stu);
                allScore.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[10000];

        for (int i = 0; i < 10000; i++) {
            threads[i] = new Thread(new Voter());
        }

        for (int i = 0; i < 10000; i++) {
            threads[i].start();
        }

        for (int i = 0; i < 10000; i++) {
            threads[i].join();
        }

        System.out.println("score: " + stu.score);
        System.out.println("allScore: " + allScore);

        
    }
}

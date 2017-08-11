package com.concurrency.chapter3.threadpool;

import java.util.concurrent.*;

/**
 * Created by ss on 2017/8/5.
 */
public class ThreadFactoryDemo {

    public static class DemoTask implements Runnable {

        private int i;

        public DemoTask(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println("Run task " + i);
        }
    }

    public static void main(String[] args) {

        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        System.out.println("Create thread.");
                        return new Thread(r);
                    }
                });

        for (int i = 0; i < 20; i++) {
            executorService.submit(new DemoTask(i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

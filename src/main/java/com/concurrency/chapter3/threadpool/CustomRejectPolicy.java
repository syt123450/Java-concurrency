package com.concurrency.chapter3.threadpool;

import java.util.concurrent.*;

/**
 * Created by ss on 2017/8/5.
 */
public class CustomRejectPolicy {

    public static class DemoTask implements Runnable {

        private int i;

        public DemoTask(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println("Execute task " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS,
                //除了在执行的5个任务外还会在保存10个任务
                new LinkedBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("Reject Thread.");
                    }
                });
        for (int i = 0; i < 30; i++) {
            executorService.submit(new DemoTask(i));
        }
        executorService.shutdown();
    }
}

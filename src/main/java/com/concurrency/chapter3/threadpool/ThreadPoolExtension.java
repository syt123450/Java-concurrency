package com.concurrency.chapter3.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by ss on 2017/8/5.
 */
public class ThreadPoolExtension {

    public static class DemoTask implements Runnable {

        public int i;

        public DemoTask(int i) {
            this.i = i;
        }

        public int getI() {
            return i;
        }

        @Override
        public void run() {
            System.out.println("Executing task.");
        }
    }

    public static void main(String[] args) {

        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>()) {

            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println(((DemoTask) r).i);
                System.out.println("Begin to execute task ");
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("Finish execute task ");
            }

            @Override
            protected void terminated() {
                System.out.println("Terminate Thread pool.");
            }
        };

        for (int i = 0; i < 1; i++) {
            DemoTask demoTask = new DemoTask(i);
            //这里使用execute就可以将在before中使用类型强制转换
            executorService.execute(demoTask);
            //如果使用submit就不能使用强制转换
//            executorService.submit(demoTask);
        }

        executorService.shutdown();
    }
}

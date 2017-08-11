package com.concurrency.chapter3.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by ss on 2017/8/5.
 */
public class SchedulePoolDemo {

    public static class DemoTask implements Runnable {

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end");
        }
    }

    public static void main(String[] args) {

        DemoTask demoTask = new DemoTask();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        //只在3秒之后延迟调度一次，这个是阻塞的，可以在执行完之后，关闭线程池
        executorService.schedule(demoTask, 3, TimeUnit.SECONDS);

        //每次调度时会使用线程池中不同的线程，即5个中任意选择一个，非阻塞的，如果关闭线程池的话什么都不会执行
        //如果周期太短，比如间隔周期短与任务周期，任务会在完成之后直接在此被调用
//        executorService.scheduleAtFixedRate(demoTask, 0, 3, TimeUnit.SECONDS);

        //任务执行完一次之后再固定的时间之后在此被执行
//        executorService.scheduleWithFixedDelay(demoTask, 0, 3, TimeUnit.SECONDS);

        //如果在此处关闭的话
        executorService.shutdown();
    }
}

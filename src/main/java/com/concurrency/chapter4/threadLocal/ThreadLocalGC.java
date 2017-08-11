package com.concurrency.chapter4.threadLocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ss on 2017/8/5.
 */
public class ThreadLocalGC {

    private static volatile ThreadLocal<SimpleDateFormat> t1 = new ThreadLocal<SimpleDateFormat>() {

        protected void finalize() throws Throwable {
            System.out.println(this.toString() + " is GC.");
        }
    };

    static volatile CountDownLatch cd = new CountDownLatch(1000);
    public static class ParseDate implements Runnable {

        private int i;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                if (t1.get() == null) {
                    t1.set(new SimpleDateFormat("yyyy-MM-dd") {

                        //一次都没有打出来
                        protected void finalize() throws Throwable {
                            System.out.println(this.toString() + " is GC.");
                        }
                    });
                }
                System.out.println(Thread.currentThread().getId() + ":create SimpleDateFormat.");
                Date t = t1.get().parse("2017-08-" + i % 30);
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
                cd.countDown();
            }
        }
    }

    //为什么我的结果是线程会不停地创建新的SimpleDateFormat?理论上应该是只需要创建一次就可以了
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new ParseDate(i));
        }
        cd.await();
        System.out.println("Mission complete.");
        t1 = null;
        System.gc();
        System.out.println("First gc complete.");

        t1 = new ThreadLocal<>();
        cd = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new ParseDate(i));
        }
        cd.await();
        Thread.sleep(1000);
        System.gc();
        System.out.println("Second GC complete.");
        executorService.shutdown();
    }

}

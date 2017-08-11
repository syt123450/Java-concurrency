package com.concurrency.chapter4.threadLocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ss on 2017/8/5.
 */
public class TestThreadLocal1 {

    private static ThreadLocal<SimpleDateFormat> t1 = new ThreadLocal<>();

    public static class ParseDate implements Runnable {

        private int i;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {

            try {
                if (t1.get() == null) {
                    t1.set(new SimpleDateFormat("yyyy-MM-dd"));
                }
                Date t = t1.get().parse("2017-08-" + i % 30);
                System.out.println(t.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            executorService.submit(new ParseDate(i));
        }
        executorService.shutdown();
    }
}

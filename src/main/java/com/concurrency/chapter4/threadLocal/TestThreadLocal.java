package com.concurrency.chapter4.threadLocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ss on 2017/8/5.
 */
public class TestThreadLocal {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static class ParseDate implements Runnable {

        private int i;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {

            try {
                Date t = sdf.parse("2017-08-" + i %30);
                System.out.println(t.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new ParseDate(i));
        }
    }
}

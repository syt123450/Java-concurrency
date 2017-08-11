package com.concurrency.chapter3.threadpool;

import java.util.concurrent.*;

/**
 * Created by ss on 2017/8/5.
 */
public class ExceptionInPool {

    public static class DivTask implements Runnable {

        int a, b;

        public DivTask(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(a / b);
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 1; i++) {
            //submit 会吃掉线程的异常，而execute不会
//            executorService.submit(new DivTask(100, i));
            executorService.execute(new DivTask(100, i));

            //使用future方式也可以得到异常信息
//            Future result = executorService.submit(new DivTask(100, i));
//            result.get();
        }
    }
}

package com.concurrency.chapter5.future;

import java.util.concurrent.*;

/**
 * Created by ss on 2017/8/10.
 */
public class JDKFuture {

    public static class RealData implements Callable<String> {

        private String para;

        public RealData(String para) {
            this.para = para;
        }

        @Override
        public String call() {

            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
                sb.append(para);
            }

            return sb.toString();
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

//        FutureTask<String> future = new FutureTask<>(new RealData("a"));
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        executorService.submit(future);
//
//        System.out.println("Finish request.");
//
//        System.out.println(future.get());

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<String> future = executorService.submit(new RealData("a"));

        System.out.println("Finish request.");

        System.out.println(future.isDone());
        System.out.println(future.cancel(true));

//        System.out.println(future.get());
    }
}

package com.concurrency.chapter6.completableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * Created by ss on 2017/8/12.
 */
public class AsyncCal {

    public static Integer cal(int a) {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //这个是在一个fork join的线程中运行，如果main执行完毕，如果这个线程没有执行完毕，这个线程也会自动退出，因为蛇者线程池是daemon
        System.out.println(222);

        return a * a;
    }

    public static void main(String[] args) throws Exception {

        final CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> AsyncCal.cal(5));
        System.out.println("111");
    }
}

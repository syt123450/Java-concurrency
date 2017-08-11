package com.concurrency.chapter6.functional;

/**
 * Created by ss on 2017/8/10.
 */

@FunctionalInterface
public interface DefaultInterface {

    void handle();
    default void run() {
        System.out.println("Running......");
    }
}

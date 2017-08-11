package com.concurrency.chapter5.singleton;

/**
 * Created by ss on 2017/8/7.
 */
public class LazySingletonDemo {

    public static int i = 12;

    private static LazySingletonDemo instance;

    private LazySingletonDemo() {
        System.out.println("Singleton is created");
    }

    public static synchronized LazySingletonDemo getInstance() {
        if (instance == null) {
            instance = new LazySingletonDemo();
        }
        return instance;
    }
}

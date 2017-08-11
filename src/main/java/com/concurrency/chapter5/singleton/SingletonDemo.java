package com.concurrency.chapter5.singleton;

/**
 * Created by ss on 2017/8/7.
 */
public class SingletonDemo {

    public static int i = 12;

    private SingletonDemo() {
        System.out.println("Singleton is created.");
    }

    private static SingletonDemo instance = new SingletonDemo();

    public static SingletonDemo getInstance() {
        return instance;
    }
}

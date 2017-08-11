package com.concurrency.chapter5.singleton;

import com.concurrency.chapter5.singleton.InnerClassSingleton;

/**
 * Created by ss on 2017/8/7.
 */
public class TestClass {

    public static void main(String[] args) throws InterruptedException {

        //test SingletonDemo
//        Thread.sleep(3000);
//
//        System.out.println(SingletonDemo.i);


        //test LazySingletonDemo
//        Thread.sleep(3000);
//
//        System.out.println(LazySingletonDemo.i);
//
//        Thread.sleep(3000);
//
//        LazySingletonDemo.getInstance();

        //test InnerClassSingleton
        Thread.sleep(3000);

        System.out.println(InnerClassSingleton.i);

        Thread.sleep(3000);

        InnerClassSingleton.getInstance();
    }
}
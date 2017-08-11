package com.concurrency.chapter6.functional;

/**
 * Created by ss on 2017/8/10.
 */
public class subClass implements class1, class2 {

    @Override
    public void run() {
        class1.super.run();
        class2.super.run();
        System.out.println("run sub class.");
    }

    public static void main(String[] args) {
        subClass subClass = new subClass();
        subClass.run();
    }
}

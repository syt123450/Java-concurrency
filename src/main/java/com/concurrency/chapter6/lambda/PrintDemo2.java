package com.concurrency.chapter6.lambda;

import java.util.function.Function;

/**
 * Created by ss on 2017/8/11.
 */
public class PrintDemo2 {

    public static void main(String[] args) {

//        final int num = 2;

        //就算使用到的值不是final，Java会自动转化成final
        int num = 2;
        Function<Integer, Integer> stringConverter = (from) -> from * num;

        //但是这个值不能被改变
//        num++;

        System.out.println(stringConverter.apply(3));
    }
}

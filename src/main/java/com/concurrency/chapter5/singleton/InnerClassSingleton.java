package com.concurrency.chapter5.singleton;

/**
 * Created by ss on 2017/8/7.
 */
public class InnerClassSingleton {

    public static int i = 12;

    static {
        System.out.println("Call static area.");
    }

    private InnerClassSingleton() {
        System.out.println("Singleton is created.");
    }

//    {
//        System.out.println("initiated.");
//    }

    private static class SingletonHolder {

        static {
            System.out.println("Holder static area.");
        }

        private static InnerClassSingleton instance = new InnerClassSingleton();

        public SingletonHolder() {
            System.out.println("Create Singleton holder.");
        }


    }

    public static InnerClassSingleton getInstance() {
        return SingletonHolder.instance;
    }
}

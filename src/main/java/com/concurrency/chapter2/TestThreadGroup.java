package com.concurrency.chapter2;

/**
 * Created by ss on 2017/7/15.
 */
public class TestThreadGroup {

    private static class GroupMember implements Runnable {

        @Override
        public void run() {

            String groupAndName = Thread.currentThread().getThreadGroup().getName() +
                    "-" + Thread.currentThread().getName();

            while (true) {
                System.out.println(groupAndName);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //threadgroup可以计算有几个thread,给出每个thread的详细信息
    public static void main(String[] args) {

        ThreadGroup threadGroup = new ThreadGroup("testThreadGroup");
        Thread thread1 = new Thread(threadGroup, new GroupMember(), "testMember1");
        Thread thread2 = new Thread(threadGroup, new GroupMember(), "testMember2");

        thread1.start();
        thread2.start();

        System.out.println(threadGroup.activeCount());
        threadGroup.list();
    }
}

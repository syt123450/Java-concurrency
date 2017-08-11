package com.concurrency.chapter2;

import lombok.Data;

/**
 * Created by ss on 2017/7/15.
 */
public class StopInconformity {

    public static User user = new User();

    @Data
    public static class User {
        private int id;
        private int name;

        @Override
        public String toString() {
            return "" + id + "===" + name;
        }
    }

    public static class ChangeObjectThread extends Thread {

        @Override
        public void run() {

            while (true) {

                synchronized (user) {
                    user.setId(user.getId() + 1);
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    user.setName(user.getId());
                }
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread extends Thread {

        @Override
        public void run() {
            while (true) {
                synchronized (user) {
                    if (user.getId() != user.getName()) {
                        System.out.println(user.toString());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread readThread = new Thread(new ReadObjectThread());
        readThread.start();

        while (true) {
            Thread changeThread = new ChangeObjectThread();
            changeThread.start();
            try {
                Thread.sleep(150);
            } catch(Exception e) {
                e.printStackTrace();
            }
            changeThread.stop();

        }
    }
}

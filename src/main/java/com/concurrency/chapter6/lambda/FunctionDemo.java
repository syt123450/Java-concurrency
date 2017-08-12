package com.concurrency.chapter6.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ss on 2017/8/11.
 */
public class FunctionDemo {

    public static class User {
        private int id;

        public User(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        @Override
        public String toString() {
            return "User:" + id;
        }
    }

    public static void main(String[] args) {

        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(new User(i));
        }
        users.stream().map(User::toString).forEach(System.out::println);
    }
}

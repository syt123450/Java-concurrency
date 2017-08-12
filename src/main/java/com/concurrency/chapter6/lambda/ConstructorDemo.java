package com.concurrency.chapter6.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ss on 2017/8/12.
 */
public class ConstructorDemo {

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

    interface UserFactory<U extends User> {
        U create(int id);
    }

    static UserFactory<User> userUserFactory = User::new;

    public static void main(String[] args) {

        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            userList.add(userUserFactory.create(i));
        }
        userList.stream().map(User::toString).forEach(System.out::println);
    }
}

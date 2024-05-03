package com.example.h_item.cache;

import org.springframework.stereotype.Component;

@Component
public class LoginUtil {

    private final static ThreadLocal<String> userLocal = new ThreadLocal<>();

    public static void put(String token) {
        userLocal.set(token);
    }

    public static String get() {
        return userLocal.get();
    }

    public static void remove() {
        userLocal.remove();
    }
}

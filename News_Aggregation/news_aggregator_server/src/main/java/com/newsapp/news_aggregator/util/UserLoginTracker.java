package com.newsapp.news_aggregator.util;

public class UserLoginTracker {
    private static boolean loggedIn = false;

    public static boolean isLoggedIn() {
        return loggedIn;
    }

    public static void setLoggedIn(boolean status) {
        loggedIn = status;
    }
}

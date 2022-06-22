package com.example.movieReviewsApp.models;

/**
 * Subscriber class.
 */
public class Subscriber {
    private String hookUrl;
    private String username;

    public Subscriber () {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setHookUrl(String hookUrl) {
        this.hookUrl = hookUrl;
    }

    public String getHookUrl() {
        return hookUrl;
    }
}

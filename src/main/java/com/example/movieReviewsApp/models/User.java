package com.example.movieReviewsApp.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * User class.
 */
@Document("Users")
public class User {
    
    @Id 
    private String id;

    private String username;
    private String password;

    public User(String username, String password) {

        this.username = username;

        this.password = password;
    }

    public String getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

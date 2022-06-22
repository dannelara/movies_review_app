package com.example.movieReviewsApp.models.Links;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Subscribe implements Link{

    String subscribe;

    public Subscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getSubscribe() {
        return subscribe;
    }

    @JsonIgnore
    @Override
    public String getLink() {
        return null;
    }
    
}

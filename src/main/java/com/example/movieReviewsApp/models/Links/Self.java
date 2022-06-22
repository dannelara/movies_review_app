package com.example.movieReviewsApp.models.Links;

public class Self implements Link {
    private String href;

    public Self(String href) {
        this.href = href;
    }

    public String getLink() {
        return href;
    }
}
    
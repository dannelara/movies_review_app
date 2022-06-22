package com.example.movieReviewsApp.models;

/**
 * Review class.
 */
public class Review {

    private String writtenBy;
    private String review;

    public Review () {
    }

    public String getReview() {
        return review;
    }
    
    public String getWrittenBy() {
        return writtenBy;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setWrittenBy(String writtenBy) {
        this.writtenBy = writtenBy;
    }

    @Override
    public String toString() {
        return "Review {" +
            " writtenBy:" + writtenBy + "," + 
            "review: " + review +
        "}";
    }
}

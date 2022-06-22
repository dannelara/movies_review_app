package com.example.movieReviewsApp.services.Responses.ResponseModels;

import java.util.ArrayList;

import com.example.movieReviewsApp.models.Review;
import com.example.movieReviewsApp.models.Links.AddReviewLink;


public class Reviews {
    ArrayList<Review> reviews;
    ArrayList<AddReviewLink> links = new ArrayList<>();

    public Reviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public ArrayList<AddReviewLink> getLinks() {
        return links;
    }

    public void setLinks(AddReviewLink link) {
        this.links.add(link);
    }
}

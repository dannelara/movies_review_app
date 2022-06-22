package com.example.movieReviewsApp.services.Helpers;


import com.example.movieReviewsApp.models.Movie;
import com.example.movieReviewsApp.models.Review;
import com.example.movieReviewsApp.models.Subscriber;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Helper class to avoid code duplication inside of other classes.
 */
@Component
public class MoviesHelper {
    public boolean reviewExists(Movie m, Review r) {

        for (Review review : m.getReviews()) {
            if (review.getWrittenBy().equals(r.getWrittenBy())) {
                return true;
            }      
        }   
        return false;
    }


    public boolean subExists(Movie m, String username) {

        for (Subscriber sub : m.getSubscribers()) {
            if (sub.getUsername().equals(username)) {
                return true;
            }
            
        }
        return false;
    }

    public Review getReview(Movie m, String username) {
        Review reviewToFind = null;
        for (Review review : m.getReviews()) {
            if (review.getWrittenBy().equals(username)) {
                reviewToFind = review;
            }
            
        }
        
        return reviewToFind;
    }

    public void notifySubscribers(Movie movie) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        for (Subscriber sub : movie.getSubscribers()) {
            
            HttpEntity<Movie> request = new HttpEntity<Movie>(movie, headers);

            new RestTemplate().postForEntity( sub.getHookUrl(), request , String.class );

        }
    }
}

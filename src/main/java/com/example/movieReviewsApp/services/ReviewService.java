package com.example.movieReviewsApp.services;


import com.example.movieReviewsApp.models.Movie;
import com.example.movieReviewsApp.models.Review;
import com.example.movieReviewsApp.services.Responses.ResponseModels.Reviews;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
    public ResponseEntity<Movie> addReviewToMovie(String movieId, Review review);
    public ResponseEntity<Movie> removeMovieReview(String movieId, String username);
    public ResponseEntity<Movie> updateMovieReview(String movieId, Review movie, String username);
    public ResponseEntity<Reviews> getReviews(String movieId, String baseUrl);
}

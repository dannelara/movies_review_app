package com.example.movieReviewsApp.services.Responses;

import java.util.List;

import com.example.movieReviewsApp.models.Movie;
import com.example.movieReviewsApp.services.Responses.ResponseModels.Reviews;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseFactory {
    
    public ResponseEntity<List<Movie>> responseWithMultipleItems(List<Movie> data, HttpStatus status) {
        HttpHeaders responseHeaders = new HttpHeaders();

        responseHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<List<Movie>>(
            data,
            responseHeaders,
            status
            );
    }

    public ResponseEntity<Movie> responseWithSingleItem(Movie data, HttpStatus status) {
        HttpHeaders responseHeaders = new HttpHeaders();

        responseHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<Movie>(
            data,
            responseHeaders,
            status
            );
    }

    public ResponseEntity<Reviews> responseWithReviews(Reviews data, HttpStatus status) {
        HttpHeaders responseHeaders = new HttpHeaders();

        responseHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<Reviews>(
            data,
            responseHeaders,
            status
            );
    }

}

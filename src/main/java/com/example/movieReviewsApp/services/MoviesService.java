package com.example.movieReviewsApp.services;

import java.util.List;

import com.example.movieReviewsApp.models.Movie;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public interface MoviesService {
    public ResponseEntity<List<Movie>> getAllMovies();
    public ResponseEntity<List<Movie>> addMovie(Movie movie, String baseUrl);
    public ResponseEntity<Movie> getMovieById(String movieId);
    public ResponseEntity<Movie> getMovieByTitle(String movieId);
    public ResponseEntity<List<Movie>> getCategory(String category);
}

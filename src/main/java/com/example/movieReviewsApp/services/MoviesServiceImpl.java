package com.example.movieReviewsApp.services;


import java.util.List;

import com.example.movieReviewsApp.models.Movie;

import com.example.movieReviewsApp.repositories.MoviesRepository;
import com.example.movieReviewsApp.services.Helpers.MoviesHelper;
import com.example.movieReviewsApp.services.Responses.ResponseFactory;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class MoviesServiceImpl implements MoviesService{

    @Autowired MoviesHelper helper;
    @Autowired ResponseFactory responseFactory;
    @Autowired MoviesRepository moviesRepo;


     private boolean isValidMovie(Movie m) {
         if (m.getTitle() == null || m.getCategory() == null)
         return false;
         else return true;
     }

    @Override
    public ResponseEntity<Movie> getMovieByTitle(String movieTitle) {

         Movie movie = this.moviesRepo.findItByTitle(movieTitle);

         HttpStatus status = movie != null ?  HttpStatus.OK  : HttpStatus.NOT_FOUND;

        return responseFactory.responseWithSingleItem(movie, status);
    }

    
    @Override
    public ResponseEntity<Movie> getMovieById(String movieId) {
        Movie movie = this.moviesRepo.findItById(movieId);

        HttpStatus status = movie != null ?  HttpStatus.OK  : HttpStatus.NOT_FOUND;

        return responseFactory.responseWithSingleItem(movie, status);
    }


    @Override
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies =  moviesRepo.findAll();         
        return responseFactory.responseWithMultipleItems(movies, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<List<Movie>> addMovie(Movie movie, String baseUrl) {
        
        Movie existingMovie = this.moviesRepo.findItByTitle(movie.getTitle().toLowerCase());

        // If movie doesn't exists create it, add the url links and return updated movies. // this will be a webhook.

      if(isValidMovie(movie)) {

        if(existingMovie == null) {

             movie.setCategory(movie.getCategory().toLowerCase());
             movie.setTitle(movie.getTitle().toLowerCase()); 
             moviesRepo.save(movie);
             Movie m = moviesRepo.findItByTitle(movie.getTitle());
             m.createLinks(baseUrl);
             moviesRepo.save(m);
            return this.getAllMovies();
 
         } else 
         return responseFactory.responseWithMultipleItems(null, HttpStatus.CONFLICT);

      }

      return responseFactory.responseWithMultipleItems(null, HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<List<Movie>> getCategory(String category) {
        
        List<Movie> movies = this.moviesRepo.findItByCategory(category);

        HttpStatus status = movies.size() != 0 ?  HttpStatus.OK  : HttpStatus.NOT_FOUND;

        movies = movies.size() > 0 ? movies : null;

        return responseFactory.responseWithMultipleItems(movies, status);

    }


}

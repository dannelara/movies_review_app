package com.example.movieReviewsApp.controllers;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.movieReviewsApp.models.Movie;
import com.example.movieReviewsApp.models.Review;
import com.example.movieReviewsApp.models.User;
import com.example.movieReviewsApp.services.MoviesServiceImpl;
import com.example.movieReviewsApp.services.ReviewsServiceImpl;
import com.example.movieReviewsApp.services.Responses.ResponseModels.Reviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


/**
 * Movies controller class. Handles routes requests.
 */
@RestController
@RequestMapping("api/v1/movies")
    public class MovieController {
    
    @Autowired MoviesServiceImpl moviesService;
    @Autowired ReviewsServiceImpl reviewsService;

    private String formatRequestParam(String param) {
        return param.toLowerCase();
    }

    

    // Make this a webhook.
    @PostMapping("")
        public ResponseEntity<List<Movie>> createNewMovie(HttpServletRequest request,@RequestBody Movie movie) {
            //Get the baseUrl for the creation of the movie urls.
            String baseUrl = ServletUriComponentsBuilder.fromRequestUri((HttpServletRequest) request)
            .replacePath(null)
            .build()
            .toUriString();

        return moviesService.addMovie(movie, baseUrl);
    }

    // Gets all movies.
    @GetMapping("")
        public  ResponseEntity<List<Movie>> getAllMovies() {
        return moviesService.getAllMovies();
    }

    // gets all moveis in a category.
    @GetMapping("/category/{categoryame}")
        public ResponseEntity<List<Movie>> getCategory(@PathVariable(name = "categoryame") String category) {
            category = formatRequestParam(category);

        return moviesService.getCategory(category);
    }


    // Gets a movie by its id.
    @GetMapping("/movie/{id}")
        public ResponseEntity<Movie> getMovie(@PathVariable("id") String movieID) {
        return moviesService.getMovieById(movieID);
    }

  
    // Gets a movie by its title.
    @GetMapping("/movie")
        public ResponseEntity<Movie> getMovieByTitle(@RequestParam(name = "title") String movieTitle) {
            movieTitle = formatRequestParam(movieTitle);
        return moviesService.getMovieByTitle(movieTitle);
    }


    // Returns all reviews for a given movie.
    @GetMapping("/movie/{id}/reviews")
    public ResponseEntity<Reviews> getReviewsFromMovie(HttpServletRequest request, @PathVariable("id") String movieID) {
          //Get the baseUrl for the creation of the movie urls.
          String baseUrl = ServletUriComponentsBuilder.fromRequestUri((HttpServletRequest) request)
          .replacePath(null)
          .build()
          .toUriString();

        return reviewsService.getReviews(movieID, baseUrl);
    }       
    //----------------------------------Reviews routes--------------------------------------//



    // Creates a new review for a specific movie.
    @PostMapping("/movie/{id}/reviews")
        public ResponseEntity<Movie> addReview(@PathVariable("id") String movieID, @RequestBody Review review, Authentication auth) {
            // Retrive the username of the authenticated user and add it to the requiest.
            User user = (User) auth.getPrincipal();
            review.setWrittenBy(user.getUsername());
        return reviewsService.addReviewToMovie(movieID, review);
    }

    // Updates user review.
     @PutMapping("/movie/{id}/reviews")
         public ResponseEntity<Movie> updateMovieReview(@PathVariable("id") String movieID, @RequestBody Review review, Authentication auth) {
            User user = (User) auth.getPrincipal();
         return reviewsService.updateMovieReview(movieID, review, user.getUsername());
     }
    
    // Removes the user review from the movie.
    @DeleteMapping("/movie/{id}/reviews")
        public ResponseEntity<Movie> deleteReview(@PathVariable("id") String movieID, Authentication auth) {
            User user = (User) auth.getPrincipal();
        return reviewsService.removeMovieReview(movieID, user.getUsername());
    }


}

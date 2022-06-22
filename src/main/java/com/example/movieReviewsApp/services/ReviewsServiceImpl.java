package com.example.movieReviewsApp.services;

import com.example.movieReviewsApp.models.Movie;
import com.example.movieReviewsApp.models.Review;
import com.example.movieReviewsApp.models.Links.AddReviewLink;
import com.example.movieReviewsApp.repositories.MoviesRepository;
import com.example.movieReviewsApp.services.Helpers.MoviesHelper;
import com.example.movieReviewsApp.services.Responses.ResponseFactory;
import com.example.movieReviewsApp.services.Responses.ResponseModels.Reviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ReviewsServiceImpl implements ReviewService {

    @Autowired MoviesHelper helper;
    @Autowired ResponseFactory responseFactory;
    @Autowired MoviesRepository moviesRepo;

    @Override
    public ResponseEntity<Movie> addReviewToMovie(String movieId, Review review) {
        Movie movie = this.moviesRepo.findItById(movieId);

        // To avoid having to retrive the updated movie from the db, create the status code before actualy updating the movie.
        HttpStatus status = movie != null && !helper.reviewExists(movie, review) ?  HttpStatus.OK  : HttpStatus.CONFLICT;

        // Check that movie and review exists.
            if (movie != null) {
                if (!helper.reviewExists(movie, review)) {
                    movie.addReview(review);
                    this.moviesRepo.save(movie);
                    helper.notifySubscribers(movie);
                }
            }

        return responseFactory.responseWithSingleItem(movie, status);
        
    }

    @Override
    public ResponseEntity<Movie> updateMovieReview(String movieId, Review review, String username) {

        Movie movie = this.moviesRepo.findItById(movieId);
        Review movieReview = helper.getReview(movie, username);
        
        // Check that movie and review exists.
            if (movie != null && movieReview != null) {
                movieReview.setReview(review.getReview());
                this.moviesRepo.save(movie);

            }

        HttpStatus status = movie != null && movieReview != null ?  HttpStatus.OK  : HttpStatus.CONFLICT;

        return responseFactory.responseWithSingleItem(movie, status);
    }


    @Override
    public ResponseEntity<Movie> removeMovieReview(String movieId, String username) {
         Movie movieObj = moviesRepo.findItById(movieId);
         boolean removed = false;

         if ( movieObj != null) {

            removed = movieObj.removeReview(username);

            moviesRepo.save(movieObj);
         }

        HttpStatus status = movieObj != null && removed == true ?  HttpStatus.NO_CONTENT  : HttpStatus.NOT_EXTENDED;

        return responseFactory.responseWithSingleItem(null, status);
    }


    @Override
    public ResponseEntity<Reviews> getReviews(String movieId, String baseUrl) {
        Movie movie = moviesRepo.findItById(movieId);

        HttpStatus status = movie != null ?  HttpStatus.OK  : HttpStatus.NOT_FOUND;

        // Check that the movie is not null with the help of the status code.
        if (status != HttpStatus.NOT_FOUND) {
            Reviews reviewsObj = new Reviews(movie.getReviews());

            // Create add review link.
            reviewsObj.setLinks(new AddReviewLink(movie.getLinks().get(1).getLink()));
     
             return responseFactory.responseWithReviews(reviewsObj, status);
        } 

        return responseFactory.responseWithReviews(null, status);
    }

}

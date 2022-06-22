package com.example.movieReviewsApp.services;

import com.example.movieReviewsApp.models.Movie;
import com.example.movieReviewsApp.models.Subscriber;
import com.example.movieReviewsApp.models.User;
import com.example.movieReviewsApp.repositories.MoviesRepository;
import com.example.movieReviewsApp.services.Helpers.MoviesHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class WebHookServiceImpl implements WebHooksService  {

    @Autowired MoviesHelper helper;

    @Autowired MoviesRepository moviesRepo;


    @Override
    public ResponseEntity<String> addSubscriber(String movieId, Subscriber webHookUrl) {

        Movie movie = moviesRepo.findItById(movieId);

        // To avoid having to retrive the updated movie from the db, create the status code before actualy updating the movie.
        HttpStatus status = movie != null && !helper.subExists(movie, webHookUrl.getUsername()) ?  HttpStatus.OK  : HttpStatus.CONFLICT;

        if (movie != null) {

            if (!helper.subExists(movie, webHookUrl.getUsername())) {
                movie.addSubscriber(webHookUrl);
                moviesRepo.save(movie);
            }

        }

        return new ResponseEntity<>(status);
    }
    


    @Override
    public ResponseEntity<String> removeSubbscriber(String movieId, User user) {
        Movie movie = moviesRepo.findItById(movieId);

        // To avoid having to retrive the updated movie from the db, create the status code before actualy updating the movie.
        HttpStatus status = movie != null && helper.subExists(movie, user.getUsername()) ?  HttpStatus.NO_CONTENT  : HttpStatus.NOT_FOUND;

        if (movie != null){

            if (helper.subExists(movie, user.getUsername())) {
                movie.removeSub(user.getUsername());
                moviesRepo.save(movie);
            }
        }
        
        return new ResponseEntity<>(status);
    }

    
}

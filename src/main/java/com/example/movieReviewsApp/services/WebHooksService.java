package com.example.movieReviewsApp.services;

import com.example.movieReviewsApp.models.Subscriber;
import com.example.movieReviewsApp.models.User;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface WebHooksService {
    public ResponseEntity<String> addSubscriber(String movieId, Subscriber webHookUrl);
    public ResponseEntity<String> removeSubbscriber(String movieId, User user);
}

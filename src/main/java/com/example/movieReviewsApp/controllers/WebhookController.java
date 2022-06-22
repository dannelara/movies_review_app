package com.example.movieReviewsApp.controllers;

import com.example.movieReviewsApp.models.Subscriber;
import com.example.movieReviewsApp.models.User;
import com.example.movieReviewsApp.services.WebHookServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Webhooks controller calss. Handles webhooks routes requests.
 */
@RestController
@RequestMapping("api/v1/webhooks")
public class WebhookController {
    
    @Autowired WebHookServiceImpl webHookService;

    // Adds a webhook subscriber to a specifik movie.
    @PostMapping("/movie/{id}")
    public ResponseEntity<String> subscribeToMovie(@PathVariable("id") String movieID, @RequestBody Subscriber sub, Authentication auth) {
        User user = (User) auth.getPrincipal();
        sub.setUsername(user.getUsername());
        return webHookService.addSubscriber(movieID, sub);

    }

    // Removies the user as a subscriber from the movie.
    @DeleteMapping("/movie/{id}")
    public ResponseEntity<String> removeSubscribeFromMovie(@PathVariable("id") String movieID, Authentication auth) {
        User user = (User) auth.getPrincipal();
        return webHookService.removeSubbscriber(movieID, user);
    }

}

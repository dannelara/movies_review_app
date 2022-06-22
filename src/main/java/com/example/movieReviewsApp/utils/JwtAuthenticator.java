package com.example.movieReviewsApp.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.movieReviewsApp.models.User;
import com.example.movieReviewsApp.repositories.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticator {

    @Value("${jwt.secret}")
    private String secret;

    @Autowired UsersRepository userRepo;

    public User authenticateUser(String token) {
    
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();

        DecodedJWT jwt = verifier.verify(token);

        String username = jwt.getSubject();

        return userRepo.findItByUsername(username);

    }
}

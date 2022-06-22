package com.example.movieReviewsApp.repositories;

import com.example.movieReviewsApp.models.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends MongoRepository<User, String> {
    public User findItByUsername(String name);
    public User findItById(String id);
}

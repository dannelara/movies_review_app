package com.example.movieReviewsApp.repositories;

import java.util.List;

import com.example.movieReviewsApp.models.Movie;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends MongoRepository<Movie, String> {
    public List<Movie> findAll();
    public Movie findItByTitle(String title);
    public Movie findItById(String id);
    public List<Movie> findItByCategory(String category);
}

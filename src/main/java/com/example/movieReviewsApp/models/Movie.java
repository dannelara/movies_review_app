package com.example.movieReviewsApp.models;

import java.util.ArrayList;

import com.example.movieReviewsApp.models.Links.Link;
import com.example.movieReviewsApp.models.Links.Self;
import com.example.movieReviewsApp.models.Links.Subscribe;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("Movies_list")
public class Movie {

    @Id private String id;
    private String title;
    private String category;
    private ArrayList<Link> links = new ArrayList<Link>();

    private ArrayList<Review> reviews = new ArrayList<>();
    private ArrayList<Subscriber> subscribers = new ArrayList<>();

    public Movie(String title, String category) {
        this.title = title;
        this.category = category;
    }


    public String getId() {
        return this.id;
    }

    
    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    @JsonIgnore
    public ArrayList<Review> getReviews() {
        return this.reviews;
    }

    public ArrayList<Link> getLinks() {
        return this.links;
    }



    public void addReview(Review review) {
        this.reviews.add(review);
    }

     public void addSubscriber(Subscriber WebhookUrl) {
        this.subscribers.add(WebhookUrl);
    }

    @JsonIgnore
    public ArrayList<Subscriber> getSubscribers() {
        return this.subscribers;
    }


    public boolean removeReview(String username) {
        boolean removed = false;

        for ( Review r : this.reviews) {
            if (r.getWrittenBy().equals(username)) {
                this.reviews.remove(r);
                removed = true;
                break;
            }
        }

        return removed;
    }

    public boolean removeSub(String username) {
        boolean removed = false;

        for ( Subscriber sub : this.subscribers) {
            if (sub.getUsername().equals(username)) {
                this.subscribers.remove(sub);
                removed = true;
                break;
            }
        }

        return removed;
    }


    public void createLinks(String baseUrl) {

        this.links.add(new Self(baseUrl + "/api/v1/movies/movie/" + id));
        this.links.add(new Self(baseUrl + "/api/v1/movies/movie/" + id + "/reviews"));
        this.links.add(new Subscribe(baseUrl + "/api/v1/webhooks/movie/" + id));

    }

    @Override
    public String toString() {
        return "{" +
        " id: " + id + "," + 
        "title: " + title +
        "category: " + category +
        "reviews: " + category +
        "urls: " + "[" + "]" +
    "}";
    }

}

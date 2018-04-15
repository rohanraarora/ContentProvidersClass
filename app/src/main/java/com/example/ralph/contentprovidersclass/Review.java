package com.example.ralph.contentprovidersclass;

/**
 * Created by ralph on 15/04/18.
 */

public class Review {

    private long id;
    private long movieId;
    private String review;

    public Review(long movieId, String review) {
        this.movieId = movieId;
        this.review = review;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}

package com.example.ralph.contentprovidersclass;

/**
 * Created by ralph on 15/04/18.
 */

public class Movie {

    private String title;
    private long id;

    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

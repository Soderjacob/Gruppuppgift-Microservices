package com.example.Filmrecensioner;

import jakarta.persistence.Entity;


public class User {
    private Long id;
    private String username;
    private String genre;

    // Getters och setters
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

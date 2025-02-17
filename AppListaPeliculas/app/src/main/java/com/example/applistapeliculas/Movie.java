package com.example.applistapeliculas;

public class Movie {
    private String title;
    private int year;
    private String director;
    private String description;
    private String url;

    // Constructor vacío obligatorio para Firebase
    public Movie() {
    }

    // Constructor completo
    public Movie(String title, int year, String director, String description, String url) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.description = description;
        this.url = url;
    }

    // Getters y setters (¡Todos deben existir!)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
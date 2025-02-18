package com.example.applistapeliculas;

public class User {
    private String uid;
    private String name;
    private String email;
    private String phone;
    private String address;

    public User() {} // Required for Firebase

    public User(String uid, String name, String email, String phone, String address) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    // Getters and setters
}
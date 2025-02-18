package com.example.applistapeliculas.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.applistapeliculas.models.Movie;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MovieRepository {
    private DatabaseReference databaseRef;

    public MovieRepository() {
        databaseRef = FirebaseDatabase.getInstance().getReference();
    }

    public void getMovies(MutableLiveData<List<Movie>> moviesLiveData) {
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Movie> movies = new ArrayList<>();
                for (DataSnapshot movieSnapshot : snapshot.getChildren()) {
                    Movie movie = movieSnapshot.getValue(Movie.class);
                    movies.add(movie);
                }
                moviesLiveData.setValue(movies);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
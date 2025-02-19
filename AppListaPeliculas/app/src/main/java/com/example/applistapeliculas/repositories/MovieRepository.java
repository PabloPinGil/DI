package com.example.applistapeliculas.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
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
    private MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();

    public MovieRepository() {
        databaseRef = FirebaseDatabase.getInstance().getReference("movies");
        loadMovies();
    }

    public LiveData<List<Movie>> getMovies() { // Ahora no necesita parámetro
        return moviesLiveData;
    }

    private void loadMovies() {
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Movie> movies = new ArrayList<>();
                for (DataSnapshot movieSnapshot : snapshot.getChildren()) {
                    Movie movie = movieSnapshot.getValue(Movie.class);
                    movie.setId(movieSnapshot.getKey());
                    movies.add(movie);
                }
                moviesLiveData.setValue(movies);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                moviesLiveData.setValue(null);
            }
        });
    }
}
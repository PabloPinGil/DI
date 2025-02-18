package com.example.applistapeliculas.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.applistapeliculas.models.Movie;
import com.example.applistapeliculas.repositories.MovieRepository;

import java.util.List;

public class DashboardViewModel extends ViewModel {
    private MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();
    private MovieRepository movieRepository;

    public DashboardViewModel() {
        movieRepository = new MovieRepository();
        loadMovies();
    }

    public LiveData<List<Movie>> getMoviesLiveData() {
        return moviesLiveData;
    }

    private void loadMovies() {
        movieRepository.getMovies(moviesLiveData);
    }
}
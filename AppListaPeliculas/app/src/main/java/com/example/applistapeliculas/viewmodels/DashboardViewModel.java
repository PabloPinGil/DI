package com.example.applistapeliculas.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.applistapeliculas.models.Movie;
import com.example.applistapeliculas.repositories.MovieRepository;

import java.util.List;

public class DashboardViewModel extends ViewModel {
    private LiveData<List<Movie>> moviesLiveData;
    private MovieRepository movieRepository;

    public DashboardViewModel() {
        movieRepository = new MovieRepository();
        moviesLiveData = movieRepository.getMovies(); // Usamos el nuevo método
    }

    public LiveData<List<Movie>> getMoviesLiveData() {
        return moviesLiveData;
    }
}
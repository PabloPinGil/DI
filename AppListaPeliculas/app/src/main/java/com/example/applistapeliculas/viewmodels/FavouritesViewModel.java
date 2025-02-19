package com.example.applistapeliculas.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.applistapeliculas.models.Movie;
import com.example.applistapeliculas.repositories.FavoritesRepository;
import com.example.applistapeliculas.repositories.MovieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FavouritesViewModel extends ViewModel {
    private LiveData<List<Movie>> favoriteMovies;
    private FavoritesRepository favoritesRepo;
    private MovieRepository movieRepo;

    public FavouritesViewModel(String userId) {
        favoritesRepo = new FavoritesRepository(userId);
        movieRepo = new MovieRepository();
        loadFavorites();
    }

    private void loadFavorites() {
        // Creamos un MediatorLiveData que combine ambas fuentes
        MediatorLiveData<List<Movie>> mediator = new MediatorLiveData<>();

        LiveData<List<String>> favoriteIdsLiveData = favoritesRepo.getFavorites();
        LiveData<List<Movie>> allMoviesLiveData = movieRepo.getMovies();

        mediator.addSource(favoriteIdsLiveData, favoriteIds -> {
            combineLatest(mediator, favoriteIds, allMoviesLiveData.getValue());
        });

        mediator.addSource(allMoviesLiveData, movies -> {
            combineLatest(mediator, favoriteIdsLiveData.getValue(), movies);
        });

        favoriteMovies = mediator;
    }

    private void combineLatest(MediatorLiveData<List<Movie>> mediator, List<String> favoriteIds, List<Movie> movies) {
        if (favoriteIds == null || movies == null) {
            mediator.setValue(new ArrayList<>());
            return;
        }
        List<Movie> filtered = new ArrayList<>();
        for (Movie movie : movies) {
            if (favoriteIds.contains(movie.getId())) {
                filtered.add(movie);
            }
        }
        Log.d("FavouritesVM", "Favoritos filtrados: " + filtered.size());
        mediator.setValue(filtered);
    }

    public LiveData<List<Movie>> getFavoriteMovies() {
        return favoriteMovies;
    }
}


package com.example.applistapeliculas.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FavouritesViewModelFactory implements ViewModelProvider.Factory {
    private final String userId;

    public FavouritesViewModelFactory(String userId) {
        this.userId = userId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FavouritesViewModel.class)) {
            return (T) new FavouritesViewModel(userId);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
package com.example.applistapeliculas.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.applistapeliculas.models.AppPreferences;

public class SettingsViewModelFactory implements ViewModelProvider.Factory {
    private final AppPreferences appPreferences;

    public SettingsViewModelFactory(AppPreferences appPreferences) {
        this.appPreferences = appPreferences;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SettingsViewModel.class)) {
            return (T) new SettingsViewModel(appPreferences);
        }
        throw new IllegalArgumentException("Clase ViewModel desconocida: " + modelClass.getName());
    }
}
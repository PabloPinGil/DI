package com.example.applistapeliculas.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.applistapeliculas.models.AppPreferences;

public class SettingsViewModel extends ViewModel {
    private final MutableLiveData<Boolean> darkModeEnabled = new MutableLiveData<>();
    private final AppPreferences appPreferences;

    public SettingsViewModel(AppPreferences appPreferences) {
        this.appPreferences = appPreferences;
        darkModeEnabled.setValue(appPreferences.isDarkModeEnabled());
    }

    public LiveData<Boolean> getDarkModeEnabled() {
        return darkModeEnabled;
    }

    public void toggleDarkMode() {
        boolean newState = !darkModeEnabled.getValue();

        // Solo actualizar si hay cambio real
        if (newState != darkModeEnabled.getValue()) {
            appPreferences.setDarkMode(newState);
            darkModeEnabled.setValue(newState);
        }
    }
}
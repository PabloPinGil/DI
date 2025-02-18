package com.example.applistapeliculas.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.applistapeliculas.repositories.UserRepository;

public class MainViewModel extends ViewModel {
    private final UserRepository repository = new UserRepository();
    private final MutableLiveData<Boolean> navigateToDashboard = new MutableLiveData<>();

    public void checkAuthStatus() {
        navigateToDashboard.setValue(repository.isUserLoggedIn());
    }

    public LiveData<Boolean> getNavigateToDashboard() { return navigateToDashboard; }
}
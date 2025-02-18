package com.example.applistapeliculas.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.applistapeliculas.repositories.UserRepository;

public class LoginViewModel extends ViewModel {
    private final UserRepository repository = new UserRepository();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loginSuccess = new MutableLiveData<>();

    public void login(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            errorMessage.setValue("Complete todos los campos");
            return;
        }

        repository.login(email, password, errorMessage, loginSuccess);
    }

    // Getters para observables
    public LiveData<String> getErrorMessage() { return errorMessage; }
    public LiveData<Boolean> getLoginSuccess() { return loginSuccess; }
}
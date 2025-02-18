package com.example.applistapeliculas.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.applistapeliculas.models.User;
import com.example.applistapeliculas.repositories.UserRepository;

public class RegisterViewModel extends ViewModel {
    private final UserRepository repository = new UserRepository();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> registerSuccess = new MutableLiveData<>();

    public void register(User user, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            errorMessage.setValue("Las contraseñas no coinciden");
            return;
        }

        repository.register(user, password, errorMessage, registerSuccess);
    }

    // Getters para observables
    public LiveData<String> getErrorMessage() { return errorMessage; }
    public LiveData<Boolean> getRegisterSuccess() { return registerSuccess; }
}

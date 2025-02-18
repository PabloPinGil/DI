package com.example.applistapeliculas.repositories;


import androidx.lifecycle.MutableLiveData;

import com.example.applistapeliculas.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRepository {
    private final FirebaseAuth auth;
    private final DatabaseReference usersRef;

    public UserRepository() {
        auth = FirebaseAuth.getInstance();
        usersRef = FirebaseDatabase.getInstance().getReference("users");
    }

    // Login
    public void login(String email, String password, MutableLiveData<String> error, MutableLiveData<Boolean> success) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        success.postValue(true);
                    } else {
                        error.postValue(task.getException().getMessage());
                    }
                });
    }

    // Registro
    public void register(User user, String password, MutableLiveData<String> error, MutableLiveData<Boolean> success) {
        auth.createUserWithEmailAndPassword(user.getEmail(), password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = auth.getCurrentUser();
                        if (firebaseUser != null) {
                            user.setUid(firebaseUser.getUid());
                            saveUser(user, error, success);
                        }
                    } else {
                        error.postValue(task.getException().getMessage());
                    }
                });
    }

    private void saveUser(User user, MutableLiveData<String> error, MutableLiveData<Boolean> success) {
        usersRef.child(user.getUid()).setValue(user)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        success.postValue(true);
                    } else {
                        error.postValue("Error guardando usuario: " + task.getException().getMessage());
                    }
                });
    }

    // Verificar autenticación
    public boolean isUserLoggedIn() {
        return auth.getCurrentUser() != null;
    }
}
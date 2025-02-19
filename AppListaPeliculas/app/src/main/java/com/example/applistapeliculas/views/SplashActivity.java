package com.example.applistapeliculas.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applistapeliculas.R;
import com.google.firebase.auth.FirebaseAuth;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Usa un layout simple para la splash (opcional, o configura el fondo desde el tema)
        setContentView(R.layout.activity_splash);

        // Simula un retardo de 2 segundos para mostrar la splash
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            // Verificar si el usuario está autenticado
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            } else {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
            finish();
        }, 2000);
    }
}

package com.example.applistapeliculas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applistapeliculas.DashboardActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            // Inicializar Firebase
            mAuth = FirebaseAuth.getInstance();

            // Verificar estado de autenticación
            if (mAuth.getCurrentUser() != null) {
                // Usuario ya autenticado, ir al Dashboard
                startActivity(new Intent(MainActivity.this, DashboardActivity.class));
            } else {
                // Usuario no autenticado, ir al Login
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }

            // Cerrar MainActivity
            finish();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error de inicialización: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}
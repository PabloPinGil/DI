package com.example.applistapeliculas.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.applistapeliculas.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.checkAuthStatus();
        viewModel.getNavigateToDashboard().observe(this, navigate -> {
            Intent intent = new Intent(this, navigate ? DashboardActivity.class : LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
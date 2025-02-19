package com.example.applistapeliculas.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.applistapeliculas.R;
import com.example.applistapeliculas.databinding.ActivityDashboardBinding;
import com.example.applistapeliculas.viewmodels.DashboardViewModel;
import com.example.applistapeliculas.viewmodels.SettingsViewModel;
import com.example.applistapeliculas.viewmodels.SettingsViewModelFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.example.applistapeliculas.models.AppPreferences;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    private ActivityDashboardBinding binding;
    private DashboardViewModel dashboardViewModel;
    private SettingsViewModel settingsViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Aplicar el tema antes de inflar el layout
        AppPreferences appPreferences = new AppPreferences(this);
        AppCompatDelegate.setDefaultNightMode(
                appPreferences.isDarkModeEnabled() ?
                        AppCompatDelegate.MODE_NIGHT_YES :
                        AppCompatDelegate.MODE_NIGHT_NO
        );

        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);

        SettingsViewModelFactory factory = new SettingsViewModelFactory(appPreferences);
        settingsViewModel = new ViewModelProvider(this, factory).get(SettingsViewModel.class);
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        binding.btnThemeToggle.setOnClickListener(v -> settingsViewModel.toggleDarkMode());

        settingsViewModel.getDarkModeEnabled().observe(this, isDarkMode -> {
            // Verificar si el modo actual es diferente al nuevo
            if (isDarkMode != (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)) {
                AppCompatDelegate.setDefaultNightMode(
                        isDarkMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
                );
                recreate();
            }
        });

        setupRecyclerView();
        setupUI();
        binding.logoutButton.setOnClickListener(v -> logout());
    }

    private void setupRecyclerView() {
        binding.moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 1. Crear el adapter con la lista vacía y el listener de clicks
        MovieAdapter adapter = new MovieAdapter(
                new ArrayList<>(),
                movie -> {
                    // Mantener todos los extras del Intent como los tenías
                    Intent intent = new Intent(DashboardActivity.this, DetailActivity.class);
                    intent.putExtra("movieId", movie.getId());
                    intent.putExtra("title", movie.getTitle());
                    intent.putExtra("year", movie.getYear());
                    intent.putExtra("director", movie.getDirector());
                    intent.putExtra("description", movie.getDescription());
                    intent.putExtra("url", movie.getUrl());
                    startActivity(intent);
                }
        );

        // 2. Configurar el adapter en el RecyclerView
        binding.moviesRecyclerView.setAdapter(adapter);

        // 3. Observar los cambios en la lista de películas del ViewModel
        dashboardViewModel.getMoviesLiveData().observe(this, movies -> {
            if (movies != null) {
                adapter.setMovies(movies); // Actualizar datos del adapter
            }
        });
    }

    private void setupUI() {
        binding.btnFavorites.setOnClickListener(v -> {
            startActivity(new Intent(DashboardActivity.this, FavouritesActivity.class));
        });
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
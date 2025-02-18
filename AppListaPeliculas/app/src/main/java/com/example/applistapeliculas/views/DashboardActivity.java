package com.example.applistapeliculas.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.applistapeliculas.R;
import com.example.applistapeliculas.databinding.ActivityDashboardBinding;
import com.example.applistapeliculas.viewmodels.DashboardViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.example.applistapeliculas.models.Movie;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    private ActivityDashboardBinding binding;
    private DashboardViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);

        setupRecyclerView();
        initViewModel();
        binding.logoutButton.setOnClickListener(v -> logout());
    }

    private void setupRecyclerView() {
        binding.moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MovieAdapter adapter = new MovieAdapter(new ArrayList<>(), movie -> {
            // Al hacer click en una película
            Intent intent = new Intent(DashboardActivity.this, DetailActivity.class);

            // Pasar datos individuales
            intent.putExtra("title", movie.getTitle());
            intent.putExtra("year", movie.getYear());
            intent.putExtra("director", movie.getDirector());
            intent.putExtra("description", movie.getDescription());
            intent.putExtra("url", movie.getUrl());

            startActivity(intent);
        });

        binding.moviesRecyclerView.setAdapter(adapter);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        viewModel.getMoviesLiveData().observe(this, movies -> {
            MovieAdapter adapter = (MovieAdapter) binding.moviesRecyclerView.getAdapter();
            if (adapter != null) {
                adapter.setMovies(movies);
            }
        });
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
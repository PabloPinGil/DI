package com.example.applistapeliculas.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.applistapeliculas.R;
import com.example.applistapeliculas.databinding.ActivityFavouritesBinding;
import com.example.applistapeliculas.viewmodels.FavouritesViewModel;
import com.example.applistapeliculas.viewmodels.FavouritesViewModelFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.example.applistapeliculas.models.Movie;

import java.util.ArrayList;

public class FavouritesActivity extends AppCompatActivity {
    private ActivityFavouritesBinding binding;
    private FavouritesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favourites);

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        viewModel = new ViewModelProvider(this, new FavouritesViewModelFactory(userId))
                .get(FavouritesViewModel.class);
        setupRecyclerView();
        observeData();
    }

    private void setupRecyclerView() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MovieAdapter adapter = new MovieAdapter(new ArrayList<>(), movie -> {
        });
        binding.recyclerView.setAdapter(adapter);
    }

    private void navigateToDetail(Movie movie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("movieId", movie.getId());
        // ... (resto de extras)
        startActivity(intent);
    }

    private void observeData() {
        viewModel.getFavoriteMovies().observe(this, movies -> {
            Log.d("FavouritesActivity", "Películas recibidas: " + movies.size()); // Debug

            MovieAdapter adapter = (MovieAdapter) binding.recyclerView.getAdapter();
            if (adapter == null) {
                adapter = new MovieAdapter(movies, this::navigateToDetail);
                binding.recyclerView.setAdapter(adapter);
            } else {
                adapter.setMovies(movies); // Actualizar siempre el adaptador
            }
        });
    }

}

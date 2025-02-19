package com.example.applistapeliculas.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.bumptech.glide.Glide;
import com.example.applistapeliculas.R;
import com.example.applistapeliculas.repositories.FavoritesRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private FavoritesRepository favoritesRepo;
    private String movieId;
    private boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Inicializar vistas
        ImageView ivPoster = findViewById(R.id.ivPosterDetail);
        TextView tvTitle = findViewById(R.id.tvTitleDetail);
        TextView tvYear = findViewById(R.id.tvYearDetail);
        TextView tvDirector = findViewById(R.id.tvDirectorDetail);
        TextView tvDescription = findViewById(R.id.tvDescriptionDetail);
        FloatingActionButton fab = findViewById(R.id.fabFavorite);

        // Obtener datos de la película desde el Intent
        Intent intent = getIntent();
        movieId = intent.getStringExtra("movieId"); // Asegúrate de pasar este dato al iniciar la actividad
        String title = intent.getStringExtra("title");
        int year = intent.getIntExtra("year", 0);
        String director = intent.getStringExtra("director");
        String description = intent.getStringExtra("description");
        String url = intent.getStringExtra("url");

        // Mostrar datos de la película
        tvTitle.setText(title != null ? title : "");
        tvYear.setText(year > 0 ? String.valueOf(year) : "");
        tvDirector.setText(director != null ? director : "");
        tvDescription.setText(description != null ? description : "");

        if (url != null && !url.isEmpty()) {
            Glide.with(this).load(url).into(ivPoster);
        }

        // Obtener userId del usuario autenticado
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        favoritesRepo = new FavoritesRepository(userId);

        // Configurar el botón de favoritos
        fab.setOnClickListener(v -> toggleFavorite());

        // Verificar si la película ya está en favoritos
        checkFavoriteStatus();
    }

    private void toggleFavorite() {
        isFavorite = !isFavorite;
        updateFabIcon();
        favoritesRepo.toggleFavorite(movieId, !isFavorite); // Invertir porque ya cambiamos el estado
    }


    private void checkFavoriteStatus() {
        // CORREGIR: Observar directamente el LiveData del repositorio
        favoritesRepo.getFavorites().observe(this, favorites -> {
            isFavorite = favorites != null && favorites.contains(movieId);
            updateFabIcon();
        });
    }

    private void updateFabIcon() {
        FloatingActionButton fab = findViewById(R.id.fabFavorite);
        int iconRes = isFavorite ?
                R.drawable.ic_favorite_filled :
                R.drawable.ic_favorite_border;

        fab.setImageResource(iconRes);
        fab.setContentDescription(isFavorite ?
                "Quitar de favoritos" :
                "Añadir a favoritos");
    }
}

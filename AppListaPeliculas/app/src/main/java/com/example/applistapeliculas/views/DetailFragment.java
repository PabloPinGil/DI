package com.example.applistapeliculas.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.applistapeliculas.R;
import com.example.applistapeliculas.databinding.FragmentDetailBinding;
import com.example.applistapeliculas.repositories.FavoritesRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    private FavoritesRepository favoritesRepo;
    private String movieId;
    private boolean isFavorite = false;

    /**
     * Método de fábrica para crear una nueva instancia del fragmento
     * con los argumentos necesarios.
     */
    public static DetailFragment newInstance(String movieId, String title, int year,
                                             String director, String description, String url) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString("movieId", movieId);
        args.putString("title", title);
        args.putInt("year", year);
        args.putString("director", director);
        args.putString("description", description);
        args.putString("url", url);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Recuperar los datos enviados como argumentos
        if (getArguments() != null) {
            movieId = getArguments().getString("movieId");
            String title = getArguments().getString("title");
            int year = getArguments().getInt("year", 0);
            String director = getArguments().getString("director");
            String description = getArguments().getString("description");
            String url = getArguments().getString("url");

            // Mostrar los datos en las vistas
            binding.tvTitleDetail.setText(title != null ? title : "");
            binding.tvYearDetail.setText(year > 0 ? String.valueOf(year) : "");
            binding.tvDirectorDetail.setText(director != null ? director : "");
            binding.tvDescriptionDetail.setText(description != null ? description : "");

            if (url != null && !url.isEmpty()) {
                Glide.with(requireContext()).load(url).into(binding.ivPosterDetail);
            }
        }

        // Obtener el userId del usuario autenticado y configurar el repositorio de favoritos
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        favoritesRepo = new FavoritesRepository(userId);

        // Configurar el botón de favoritos
        binding.fabFavorite.setOnClickListener(v -> toggleFavorite());

        // Verificar si la película ya se encuentra en favoritos
        checkFavoriteStatus();
    }

    private void toggleFavorite() {
        // Invertir el estado actual
        isFavorite = !isFavorite;
        updateFabIcon();
        // Se invierte el valor para reflejar el estado anterior a la llamada
        favoritesRepo.toggleFavorite(movieId, !isFavorite);
    }

    private void checkFavoriteStatus() {
        favoritesRepo.getFavorites().observe(getViewLifecycleOwner(), favorites -> {
            isFavorite = favorites != null && favorites.contains(movieId);
            updateFabIcon();
        });
    }

    private void updateFabIcon() {
        int iconRes = isFavorite ? R.drawable.ic_favorite_filled : R.drawable.ic_favorite_border;
        binding.fabFavorite.setImageResource(iconRes);
        binding.fabFavorite.setContentDescription(isFavorite ?
                "Quitar de favoritos" : "Añadir a favoritos");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

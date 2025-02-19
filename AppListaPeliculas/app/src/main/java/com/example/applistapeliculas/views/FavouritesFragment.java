package com.example.applistapeliculas.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.applistapeliculas.R;
import com.example.applistapeliculas.databinding.FragmentFavouritesBinding;
import com.example.applistapeliculas.models.Movie;
import com.example.applistapeliculas.viewmodels.FavouritesViewModel;
import com.example.applistapeliculas.viewmodels.FavouritesViewModelFactory;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class FavouritesFragment extends Fragment {

    private FragmentFavouritesBinding binding;
    private FavouritesViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infla el layout usando Data Binding.
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourites, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtener el ID del usuario actual.
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        // Inicializar el ViewModel con su factory.
        viewModel = new ViewModelProvider(this, new FavouritesViewModelFactory(userId))
                .get(FavouritesViewModel.class);

        setupRecyclerView();
        observeData();
    }

    private void setupRecyclerView() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        // Se crea un adapter con lista vacía y un listener vacío (se actualizará al observar datos).
        MovieAdapter adapter = new MovieAdapter(new ArrayList<>(), movie -> {
            // Listener por defecto vacío.
        });
        binding.recyclerView.setAdapter(adapter);
    }

    private void navigateToDetail(Movie movie) {
        Intent intent = new Intent(requireContext(), DetailActivity.class);
        intent.putExtra("movieId", movie.getId());
        // Agrega el resto de extras que necesites
        startActivity(intent);
    }

    private void observeData() {
        viewModel.getFavoriteMovies().observe(getViewLifecycleOwner(), movies -> {
            Log.d("FavouritesFragment", "Películas recibidas: " + movies.size());
            MovieAdapter adapter = (MovieAdapter) binding.recyclerView.getAdapter();
            if (adapter == null) {
                adapter = new MovieAdapter(movies, this::navigateToDetail);
                binding.recyclerView.setAdapter(adapter);
            } else {
                adapter.setMovies(movies); // Actualizar siempre el adaptador
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}


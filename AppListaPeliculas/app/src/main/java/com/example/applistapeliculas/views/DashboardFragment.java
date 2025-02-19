package com.example.applistapeliculas.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.applistapeliculas.R;
import com.example.applistapeliculas.databinding.FragmentDashboardBinding;
import com.example.applistapeliculas.models.AppPreferences;
import com.example.applistapeliculas.viewmodels.DashboardViewModel;
import com.example.applistapeliculas.viewmodels.SettingsViewModel;
import com.example.applistapeliculas.viewmodels.SettingsViewModelFactory;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private DashboardViewModel dashboardViewModel;
    private SettingsViewModel settingsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Aplicar el tema antes de inflar el layout
        AppPreferences appPreferences = new AppPreferences(requireContext());
        AppCompatDelegate.setDefaultNightMode(
                appPreferences.isDarkModeEnabled() ?
                        AppCompatDelegate.MODE_NIGHT_YES :
                        AppCompatDelegate.MODE_NIGHT_NO
        );

        // Infla el layout utilizando data binding.
        // Nota: Renombra el archivo XML a fragment_dashboard.xml para seguir la convención.
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Configuración de ViewModels
        SettingsViewModelFactory factory = new SettingsViewModelFactory(new AppPreferences(requireContext()));
        settingsViewModel = new ViewModelProvider(this, factory).get(SettingsViewModel.class);
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        // Configurar botón de cambio de tema
        binding.btnThemeToggle.setOnClickListener(v -> settingsViewModel.toggleDarkMode());

        settingsViewModel.getDarkModeEnabled().observe(getViewLifecycleOwner(), isDarkMode -> {
            if (isDarkMode != (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)) {
                AppCompatDelegate.setDefaultNightMode(
                        isDarkMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
                );
                requireActivity().recreate();
            }
        });

        setupRecyclerView();
        setupUI();

        binding.logoutButton.setOnClickListener(v -> logout());
    }

    private void setupRecyclerView() {
        binding.moviesRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Crear el adapter con la lista vacía y el listener de clicks
        MovieAdapter adapter = new MovieAdapter(new ArrayList<>(), movie -> {
            Intent intent = new Intent(requireContext(), DetailActivity.class);
            intent.putExtra("movieId", movie.getId());
            intent.putExtra("title", movie.getTitle());
            intent.putExtra("year", movie.getYear());
            intent.putExtra("director", movie.getDirector());
            intent.putExtra("description", movie.getDescription());
            intent.putExtra("url", movie.getUrl());
            startActivity(intent);
        });

        // Configurar el adapter en el RecyclerView
        binding.moviesRecyclerView.setAdapter(adapter);

        // Observar cambios en la lista de películas del ViewModel
        dashboardViewModel.getMoviesLiveData().observe(getViewLifecycleOwner(), movies -> {
            if (movies != null) {
                adapter.setMovies(movies);
            }
        });
    }

    private void setupUI() {
        binding.btnFavorites.setOnClickListener(v -> {
            startActivity(new Intent(requireContext(), FavouritesActivity.class));
        });
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(requireContext(), LoginActivity.class));
        requireActivity().finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}


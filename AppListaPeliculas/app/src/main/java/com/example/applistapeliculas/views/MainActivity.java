package com.example.applistapeliculas.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.applistapeliculas.R;
import com.example.applistapeliculas.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;  // Usamos Data Binding

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Verificar autenticación: si el usuario no está logueado, redirigir a LoginActivity
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        // Infla el layout que contiene el Navigation Drawer, Toolbar y contenedor de fragments
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Configurar la Toolbar definida en el layout
        setSupportActionBar(binding.toolbar);

        // Configurar el ActionBarDrawerToggle para sincronizar el DrawerLayout con la Toolbar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                binding.drawerLayout,
                binding.toolbar,
                R.string.navigation_drawer_open,  // Asegúrate de tener estos strings en res/values/strings.xml
                R.string.navigation_drawer_close
        );
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Configurar el listener del NavigationView
        binding.navigationView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_dashboard) {
                openFragment(new DashboardFragment());
            } else if (itemId == R.id.nav_favourites) {
                openFragment(new FavouritesFragment());
            } else if (itemId == R.id.nav_profile) {
                openFragment(new ProfileFragment());
            } else if (itemId == R.id.nav_logout) {
                logoutUser();
            }

            // Cierra el drawer después de seleccionar un ítem
            binding.drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // Cargar por defecto el DashboardFragment si es la primera vez
        if (savedInstanceState == null) {
            openFragment(new DashboardFragment());
        }
    }

    /**
     * Reemplaza el fragmento en el contenedor del Drawer.
     */
    private void openFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }

    /**
     * Cierra sesión del usuario y lo redirige a LoginActivity.
     */
    private void logoutUser() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}


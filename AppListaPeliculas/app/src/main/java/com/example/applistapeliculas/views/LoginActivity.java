package com.example.applistapeliculas.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.applistapeliculas.R;
import com.example.applistapeliculas.databinding.ActivityLoginBinding;
import com.example.applistapeliculas.viewmodels.LoginViewModel;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        setupObservers();
        setupUI();
    }

    private void setupObservers() {
        viewModel.getErrorMessage().observe(this, error ->
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show());

        viewModel.getLoginSuccess().observe(this, success -> {
            if (success) {
                startActivity(new Intent(this, DashboardActivity.class));
                finish();
            }
        });
    }

    private void setupUI() {
        binding.loginButton.setOnClickListener(v -> {
            String email = binding.emailEditText.getText().toString().trim();
            String password = binding.passwordEditText.getText().toString().trim();
            viewModel.login(email, password);
        });

        binding.goToRegisterButton.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));
    }
}
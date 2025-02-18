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
import com.example.applistapeliculas.databinding.ActivityRegisterBinding;
import com.example.applistapeliculas.models.User;
import com.example.applistapeliculas.viewmodels.RegisterViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        setupObservers();
        setupUI();
    }

    private void setupObservers() {
        viewModel.getErrorMessage().observe(this, error ->
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show());

        viewModel.getRegisterSuccess().observe(this, success -> {
            if (success) {
                startActivity(new Intent(this, DashboardActivity.class));
                finish();
            }
        });
    }

    private void setupUI() {
        binding.registerButton.setOnClickListener(v -> {
            User user = new User(
                    "", // UID se generará después
                    binding.nameEditText.getText().toString().trim(),
                    binding.emailEditText.getText().toString().trim(),
                    binding.phoneEditText.getText().toString().trim(),
                    binding.addressEditText.getText().toString().trim()
            );

            String password = binding.passwordEditText.getText().toString().trim();
            String confirmPassword = binding.confirmPasswordEditText.getText().toString().trim();

            viewModel.register(user, password, confirmPassword);
        });
    }
}

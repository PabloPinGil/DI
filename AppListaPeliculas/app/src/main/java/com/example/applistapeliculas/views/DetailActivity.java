package com.example.applistapeliculas.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.applistapeliculas.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        try {
            ImageView ivPoster = findViewById(R.id.ivPosterDetail);
            TextView tvTitle = findViewById(R.id.tvTitleDetail);
            TextView tvYear = findViewById(R.id.tvYearDetail);
            TextView tvDirector = findViewById(R.id.tvDirectorDetail);
            TextView tvDescription = findViewById(R.id.tvDescriptionDetail);

            Intent intent = getIntent();
            String title = intent.getStringExtra("title");
            int year = intent.getIntExtra("year", 0);
            String director = intent.getStringExtra("director");
            String description = intent.getStringExtra("description");
            String url = intent.getStringExtra("url");

            // Verificar nulls antes de asignar
            tvTitle.setText(title != null ? title : "");
            tvYear.setText(String.valueOf(year));
            tvDirector.setText(director != null ? director : "");
            tvDescription.setText(description != null ? description : "");

            if (url != null && !url.isEmpty()) {
                Glide.with(this).load(url).into(ivPoster);
            }
        } catch (Exception e) {
            Log.e("DetailActivity", "Error loading movie details", e);
            Toast.makeText(this, "Error loading movie details", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
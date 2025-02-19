package com.example.applistapeliculas.repositories;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FavoritesRepository {
    private DatabaseReference favoritesRef;

    public FavoritesRepository(String userId) {
        favoritesRef = FirebaseDatabase.getInstance().getReference()
                .child("users").child(userId).child("favorites");
    }

    public void toggleFavorite(String movieId, boolean isFavorite) {
        if (isFavorite) {
            favoritesRef.child(movieId).removeValue();
        } else {
            favoritesRef.child(movieId).setValue(true);
        }
    }

    public LiveData<List<String>> getFavorites() {
        MutableLiveData<List<String>> liveData = new MutableLiveData<>();
        favoritesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> favorites = new ArrayList<>();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    favorites.add(ds.getKey());
                }
                liveData.setValue(favorites);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return liveData;
    }
}

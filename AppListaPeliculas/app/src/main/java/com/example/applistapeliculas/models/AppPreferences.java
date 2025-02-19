package com.example.applistapeliculas.models;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {
    private final SharedPreferences sharedPref;

    public AppPreferences(Context context) {
        sharedPref = context.getSharedPreferences("AppConfig", Context.MODE_PRIVATE);
    }

    public boolean isDarkModeEnabled() {
        return sharedPref.getBoolean("darkMode", false);
    }

    public void setDarkMode(boolean enabled) {
        boolean current = sharedPref.getBoolean("darkMode", false);

        if (current != enabled) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("darkMode", enabled);
            editor.apply(); // Usar apply() en lugar de commit()
        }
    }
}
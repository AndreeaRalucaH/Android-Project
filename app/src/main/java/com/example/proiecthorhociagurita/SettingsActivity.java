package com.example.proiecthorhociagurita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_settings);

        addPreferencesFromResource(R.layout.activity_settings);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        String username = pref.getString("etUsername", null);
        String pass = pref.getString("etParola", null);
        String email = pref.getString("etEmail", null);
        boolean sex = pref.getBoolean("cbSex", false);
        boolean limba = pref.getBoolean("switchLimba", false);

    }
}
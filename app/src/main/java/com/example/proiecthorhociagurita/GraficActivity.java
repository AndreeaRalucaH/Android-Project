package com.example.proiecthorhociagurita;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class GraficActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafic);
    }

    public void clickCheltuieli(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameID, new CheltuieliFragment()).commit();
    }

    public void clickVenituri(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameID, new VenituriFragment()).commit();
    }
}
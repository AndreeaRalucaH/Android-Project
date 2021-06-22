package com.example.proiecthorhociagurita;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class Istoric extends AppCompatActivity {

    private ListView listViewCheltuieli;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_istoric);

        listViewCheltuieli = findViewById(R.id.listViewCheltuieli);

        drawList();
    }

    public void drawList(){
        CheltuialaAdapter adapter = new CheltuialaAdapter(getApplicationContext(), R.layout.elemente_istoric, MainPage.listaCheltuieli, getLayoutInflater());
        listViewCheltuieli.setAdapter(adapter);
    }
}
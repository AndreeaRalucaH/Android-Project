package com.example.proiecthorhociagurita;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class MainPage extends AppCompatActivity {

    private Button btnIstoric;
    private Button btnAdaugaVenit;
    private Button btnAdaugaCheltuiala;
    private Button btnGrafice;
    private Button btnLogOut;
    private Button btnSettings;
    private Button btnListaFacturi;
    private Button btnIstoricVenituri;
    private Button btnIstoricCheltuieli;


    public static List<Cheltuiala> listaCheltuieli = new ArrayList<Cheltuiala>();


    public static final int REQUEST_CODE_CH = 200;

    private void initializeButtons(){
        btnIstoric = findViewById(R.id.btnIstoric);
        btnAdaugaVenit = findViewById(R.id.btnVenituri);
        btnAdaugaCheltuiala = findViewById(R.id.btnCheltuieli);
        btnGrafice = findViewById(R.id.btnGrafice);
        btnLogOut = findViewById(R.id.btnLogOut);
        btnSettings = findViewById(R.id.btnSettings);
        btnListaFacturi = findViewById(R.id.btnListaFacturi);
        btnIstoricCheltuieli = findViewById(R.id.btnCheltuieliShow);
        btnIstoricVenituri = findViewById(R.id.btnVenituriShow);

    }

    private void initializeClickListeners(){

        //btnIstoric here
        btnIstoric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(getApplicationContext(), Istoric.class);
                startActivity(intentReg);
            }
        });
        //---
        btnAdaugaVenit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(getApplicationContext(), AdaugaVenit.class);
                startActivity(intentReg);
            }
        });


        btnGrafice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(getApplicationContext(), GraficActivity.class);
                startActivity(intentReg);
            }
        });

        btnAdaugaCheltuiala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(getApplicationContext(), AdaugareCheltuiala.class);
                startActivityForResult(intentReg, REQUEST_CODE_CH);
            }
        });

        btnListaFacturi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AfiseazaFactura.class);
                startActivity(intent);
            }
        });

        btnIstoricCheltuieli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), IstoricCheltuieli.class);
                startActivity(intent);
            }
        });

        btnIstoricVenituri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), IstoricVenituri.class);
                startActivity(intent);
            }
        });

        //btnLogout here
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLog = new Intent(getApplicationContext(), PaginaPrincipala.class);
                startActivity(intentLog);
            }
        });

        //btnSettings here
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSet = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intentSet);
            }
        });
    }


//    //language
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(LocalHelper.onAttach(newBase, "ro"));
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        initializeButtons();
        initializeClickListeners();

//
//        Paper.init(this);
//        String language = Paper.book().read("language");
//        if(language == null){
//            Paper.book().write("language", "ro");
//        }
//        updateView((String)Paper.book().read("language"));
    }

//    private void updateView(String language){
//        Context context = LocalHelper.setLocale(this,language);
//        Resources resources = context.getResources();
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_CH && resultCode == RESULT_OK && data!=null){
            Cheltuiala cheltuiala = (Cheltuiala) data.getSerializableExtra(AdaugareCheltuiala.ADD_CHELTUIALA);

            if(cheltuiala != null){
                Toast.makeText(getApplicationContext(), cheltuiala.toString(), Toast.LENGTH_LONG).show();
                listaCheltuieli.add(cheltuiala);
            }
        }
    }
}
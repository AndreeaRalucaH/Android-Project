package com.example.proiecthorhociagurita;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class PaginaPrincipala extends AppCompatActivity {

    private void initializeTVLinks(){
        TextView t1  = findViewById(R.id.link1);
        t1.setMovementMethod(LinkMovementMethod.getInstance());

        TextView t2  = findViewById(R.id.link2);
        t2.setMovementMethod(LinkMovementMethod.getInstance());

        TextView t3  = findViewById(R.id.link3);
        t3.setMovementMethod(LinkMovementMethod.getInstance());

        TextView t4  = findViewById(R.id.link4);
        t4.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private Button btnRo;
    private Button btnEn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();

        setContentView(R.layout.activity_pagina_principala);

        btnRo = findViewById(R.id.btnRomana);
        btnEn = findViewById(R.id.btnEngleza);

        btnRo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("ro");
                recreate();
            }
        });

        btnEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("en");
                recreate();
            }
        });


//        btnLang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setLocale("ro");
//                recreate();
//            }
//        });
//        swchLang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){
//                    setLocale("en");
//                    recreate();
//                }else
//                {
//                    setLocale("ro");
//                    recreate();
//                }
//            }
//        });
//        if(swchLang.isChecked()){
//            setLocale("ro");
//            recreate();
//
//        }

        Button btLogin = findViewById(R.id.btnLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLog = new Intent(getApplicationContext(), LoginActivityMe.class);
                startActivity(intentLog);
            }
        });

        Button btSignin = findViewById(R.id.btnSignin);
        btSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(getApplicationContext(), Inregistrare.class);
                startActivity(intentReg);
            }
        });

        initializeTVLinks();

    }

    private void setLocale(String language){
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("Language", MODE_PRIVATE).edit();
        editor.putString("Language", language);
        editor.apply();

    }

    public void loadLocale(){
        SharedPreferences preferences = getSharedPreferences("Language", Activity.MODE_PRIVATE);
        String lang = preferences.getString("Language", "");
        setLocale(lang);
    }
}
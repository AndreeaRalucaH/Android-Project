package com.example.proiecthorhociagurita;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivityMe extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPass;
    private Button btnOk;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_me);

        etEmail = findViewById(R.id.editTextEmailLogin);
        etPass = findViewById(R.id.editTextParolaLogin);
        btnOk = findViewById(R.id.btnOkLogin);

        preferences = getSharedPreferences("Login", Context.MODE_PRIVATE);

//        String email = preferences.getString("etEmail", "");
//        String pass = preferences.getString("etParola", "");


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();

//
                String email = etEmail.getText().toString().trim();
                String pass = etPass.getText().toString().trim();

                editor.putString("etEmail", etEmail.getText().toString());
                editor.putString("etParola",etPass.getText().toString());
                editor.commit();


                AplicatieDB aplicatieDB = AplicatieDB.getInstanta(getApplicationContext());
                UserDao userDao = aplicatieDB.getUserDao();

                User user = userDao.getUser(email,pass);
                if(user != null){
                    Intent intent = new Intent(getApplicationContext(), MainPage.class);
                    intent.putExtra("User", user);
                    Global.loggedUserId = user.getId();
                    startActivity(intent);
                    finish();


                }else{
                    Toast.makeText(getApplicationContext(), "Emailul sau parola gresite", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
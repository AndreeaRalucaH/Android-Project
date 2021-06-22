package com.example.proiecthorhociagurita;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class Inregistrare extends AppCompatActivity {

    public static final String TAG = "AdaugaUser";

    private TextView tvData;
    private DatePickerDialog.OnDateSetListener tvDataSetListener;
    private void configureDatePicker(){
        tvData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(Inregistrare.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        tvDataSetListener,
                        year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        tvDataSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month+=1;
                //Log.d(TAG, "onDateSet: dd/mm/yyy: " + dayOfMonth + "/" + month + "/" + year);

                String date = dayOfMonth + "/" + month + "/" + year;
                tvData.setText(date);
            }
        };
    }

    private EditText etNume;
    private EditText etPrenume;
    private EditText etMail;
    private EditText etTelefon;
    private EditText etParola;
    private EditText etConfirmaParola;
    private Button btnRegister;
    private boolean okPassword;

    RadioButton genderRadioButton;
    RadioGroup radioGroup;
    private String gender;

    private CheckBox cbTermeni;
    private CheckBox cbNewsletter;

    private Intent intent;

    public void transferUser(){
        if(etNume.getText() == null || etNume.getText().toString().trim().isEmpty()){
            etNume.setError("Introduceti numele!");
        }
        else if(etPrenume.getText() == null || etPrenume.getText().toString().trim().isEmpty()){
            etPrenume.setError("Introduceti prenumele!");
        }
        else if(etMail.getText() == null || etMail.getText().toString().trim().isEmpty()){
            etMail.setError("Introduceti mail!");
        }
        else if(etParola.getText() == null || etParola.getText().toString().trim().isEmpty()){
            etParola.setError("Introduceti o parola!");
        }
        else if(!okPassword){
            Toast.makeText(getApplicationContext(), etConfirmaParola.getText().toString() + etParola.getText().toString(), Toast.LENGTH_LONG).show();
            etConfirmaParola.setError("Parolele nu corespund! Look:" + etConfirmaParola.getText().toString() + etParola.getText().toString());
        }
        else if(etTelefon.getText() == null || etTelefon.getText().toString().trim().isEmpty()){
            etTelefon.setError("Introduceti numarul de telefon!");
        }
        else{
            User user = new User(etNume.getText().toString(), etPrenume.getText().toString(), etParola.getText().toString(), etMail.getText().toString(), etTelefon.getText().toString(), gender, tvData.getText().toString());
            Log.d(TAG, user.toString());

            //Database insert
            AplicatieDB aplicatieDB = AplicatieDB.getInstanta(getApplicationContext());
            aplicatieDB.getUserDao().insert(user);

            setResult(RESULT_OK, intent);
            finish(); //am distrus activitatea
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inregistrare);

        tvData = findViewById(R.id.tv_Birthday);
        configureDatePicker();

        etNume = findViewById(R.id.et_Username);
        etPrenume = findViewById(R.id.et_Surname);
        etMail = findViewById(R.id.et_Mail);
        etTelefon = findViewById(R.id.et_Phone);
        etParola = findViewById(R.id.et_Password);
        etConfirmaParola = findViewById(R.id.et_RepeatPassword);
        radioGroup = findViewById(R.id.rg_gender);
        btnRegister = findViewById(R.id.btn_Register);
        cbTermeni = findViewById(R.id.cb_Terms);
        cbNewsletter = findViewById(R.id.cb_Newsletter);

        //prindem intent-ul
        intent = getIntent();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                genderRadioButton = (RadioButton)findViewById(selectedId);
                gender=genderRadioButton.getText().toString();

                okPassword = etConfirmaParola.getText().toString().equals(etParola.getText().toString());
                transferUser();
            }
        });

        cbTermeni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cbTermeni.isChecked()){
                    btnRegister.setEnabled(false);
                    Toast.makeText(getApplicationContext(), "Trebuie sa fiti de acord cu termenii si conditiile", Toast.LENGTH_LONG).show();
                }
                else btnRegister.setEnabled(true);
            }
        });

    }
}
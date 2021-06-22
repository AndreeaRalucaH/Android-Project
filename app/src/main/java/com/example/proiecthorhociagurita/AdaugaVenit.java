package com.example.proiecthorhociagurita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.BufferUnderflowException;
import java.util.Calendar;

public class AdaugaVenit extends AppCompatActivity {

    public static final String TAG = "AdaugaVenit";
    private FirebaseDatabase firebaseDatabase;

    private TextView tvDataVenit;
    private DatePickerDialog.OnDateSetListener tvDataVenitSetListener;
    private void configurareDatePicker(){
        tvDataVenit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AdaugaVenit.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, tvDataVenitSetListener, year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        tvDataVenitSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month +=1;
                String date = dayOfMonth + "/" + month + "/" + year;
                tvDataVenit.setText(date);
            }
        };
    }

    private Spinner spinnerVenituri;
    private String[] tipuriVenituri;
    private void configureSpinnerVenturi(){
        tipuriVenituri = new String[]{"Cash", "Card", "CEC", "Altele"}; //va fi populat dinamic mai tarziu
        ArrayAdapter<String> adaptor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tipuriVenituri);
        spinnerVenituri.setAdapter(adaptor);
    }

    private EditText etProvenienta;
    private EditText etSuma;
    private Button btnCancel;
    private Button btnOk;


    private Intent intent;
    public static final String ADD_VENIT = "adaugaVenit";

    private void salvareFirebase(final Venit venit){
        final DatabaseReference myRef = firebaseDatabase.getReference("gestionarefinanciaraproj-default-rtdb");
        myRef.keepSynced(true);

        myRef.child("gestionarefinanciaraproj-default-rtdb").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                venit.setUid(myRef.child("gestionarefinanciaraproj-default-rtdb").push().getKey());
                myRef.child("gestionarefinanciaraproj-default-rtdb").child(venit.getUid()).setValue(venit);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void transferVenit(){
        if(etProvenienta.getText() == null || etProvenienta.getText().toString().trim().isEmpty()){
            etProvenienta.setError("Introduceti provenienta!");
        }
        else if(etSuma.getText() == null || etSuma.getText().toString().trim().isEmpty()){
            etSuma.setError("Introduceti suma!");
        }
        else {
            final Venit venit = new Venit(tvDataVenit.getText().toString(), etProvenienta.getText().toString(), Integer.parseInt(String.valueOf(etSuma.getText())), spinnerVenituri.getSelectedItem().toString().toUpperCase());
            Log.d(TAG, venit.toString());
            Toast.makeText(getApplicationContext(),venit.toString(),Toast.LENGTH_LONG).show();

            //Database insert
            final AplicatieDB aplicatieDB = AplicatieDB.getInstanta(getApplicationContext());
            venit.setUserId(Global.loggedUserId);
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    aplicatieDB.getVenitDao().insert(venit);
                }
            });

            //FirebaseDatabase insert
            salvareFirebase(venit);

            //Transfer in activitatea principala de valori
            intent.putExtra(ADD_VENIT, venit);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_venit);

        tvDataVenit = findViewById(R.id.tvDataVenit);
        configurareDatePicker();

        spinnerVenituri = findViewById(R.id.spinnerCashAdaugaVenit);
        configureSpinnerVenturi();
        etProvenienta = findViewById(R.id.editTextProvenientaVenit);
        etSuma = findViewById(R.id.editTextSumaAdaugaVenit);
        btnCancel = findViewById(R.id.btnCancelAdaugaVenit);
        btnOk = findViewById(R.id.btnOkAdaugaVenit);

        firebaseDatabase = FirebaseDatabase.getInstance("https://gestionarefinanciaraproj-default-rtdb.firebaseio.com/");

        intent = getIntent();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transferVenit();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
    }
}
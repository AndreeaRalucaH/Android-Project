package com.example.proiecthorhociagurita;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
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

import java.util.Calendar;
import java.util.List;

public class AdaugareCheltuiala extends AppCompatActivity {

    public static final String TAG = "AdaugaCheltuiala";

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

                DatePickerDialog dialog = new DatePickerDialog(AdaugareCheltuiala.this,
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

    private Spinner spinnerCheltuieli;
    private String[] tipuriCheltuieli;
    private void configureSpinnerCheltuieli(){
        tipuriCheltuieli = new String[]{"Mancare", "Utilitati", "Distractie", "Altele"}; //va fi populat dinamic mai tarziu
        ArrayAdapter<String> adaptor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tipuriCheltuieli);
        spinnerCheltuieli.setAdapter(adaptor);
    }

    private EditText etDenumire;
    private EditText etSuma;
    private EditText etNotite;
    private Button btnSubmitSpending;
    private Button btnCancelSpending;

    private Intent intent;
    public static final String ADD_CHELTUIALA = "adaugaCheltuiala";

    public void transferCheltuiala(){
        if(etDenumire.getText() == null || etDenumire.getText().toString().trim().isEmpty()){
            etDenumire.setError("Introduceti denumirea!");
        }
        else if(etSuma.getText() == null || etSuma.getText().toString().trim().isEmpty()){
            etDenumire.setError("Introduceti suma!");
        }
        else{
            //Log.d(TAG, String.valueOf(etDenumire.getText()) + " " + etSuma.getText() + " " + tvData.getText() + " " + etNotite.getText() + " " + spinnerCheltuieli.getSelectedItem().toString().toUpperCase());
            final Cheltuiala cheltuiala = new Cheltuiala(etDenumire.getText().toString(), Integer.parseInt(String.valueOf(etSuma.getText())), tvData.getText().toString(), etNotite.getText().toString(), spinnerCheltuieli.getSelectedItem().toString().toUpperCase());
            Log.d(TAG, cheltuiala.toString());

            //Database insert
            final AplicatieDB aplicatieDB = AplicatieDB.getInstanta(getApplicationContext());
            cheltuiala.setUserId(Global.loggedUserId);

            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    aplicatieDB.getCheltuialaDao().insert(cheltuiala);
                }
            });


            //Test database
            List<Cheltuiala> listaCheltuieli = aplicatieDB.getCheltuialaDao().getAll();
            if(listaCheltuieli!=null)
                for(Cheltuiala ch : listaCheltuieli)
                    Toast.makeText(getApplicationContext(), ch.toString(), Toast.LENGTH_LONG).show();

            intent.putExtra(ADD_CHELTUIALA, cheltuiala); // am pus rezultatul in intent
            setResult(RESULT_OK, intent);
            finish(); //am distrus activitatea
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaugare_cheltuiala);

        tvData = findViewById(R.id.tv_SpendingDate);
        configureDatePicker();

        spinnerCheltuieli = findViewById(R.id.spinner_SpendingType);
        configureSpinnerCheltuieli();

        etDenumire = findViewById(R.id.et_SpendingName);
        etSuma = findViewById(R.id.et_SpendingSum);
        etNotite = findViewById(R.id.et_SpendingNotes);
        btnSubmitSpending = findViewById(R.id.btn_SpendingsOK);
        btnCancelSpending = findViewById(R.id.btn_SpendingsCancel);

        //prindem intent-ul
        intent = getIntent();

        btnSubmitSpending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transferCheltuiala();
            }
        });

        btnCancelSpending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });


    }
}
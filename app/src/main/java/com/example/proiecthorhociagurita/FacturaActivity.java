package com.example.proiecthorhociagurita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class FacturaActivity extends AppCompatActivity {

    private EditText etDenumireafac;
    private EditText etDataEmitere;
    private EditText etSuma;
    private EditText etDataScadenta;
    private EditText etMentiuni;

    private Button btnPlateste;
    private Button btnBack;

    public static final String ADD_FACTURA = "adaugaFactura";

    public static final String VIEW_PRET = "viewPRet";

    private int poz;

    Intent intent;

    List<Factura> lista = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);

        etDenumireafac = findViewById(R.id.editTextDenumireFurnizor);
        etDataEmitere = findViewById(R.id.editTextDataEmitere);
        etSuma = findViewById(R.id.editTextSumaFactura);
        etDataScadenta = findViewById(R.id.editTextDataScadenta);
        etMentiuni = findViewById(R.id.editTextMentiuniFactura);
        btnBack = findViewById(R.id.btnBackFactura);
        btnPlateste = findViewById(R.id.btnPlatesteFactura);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), AfiseazaFactura.class);
                startActivity(intent2);
            }
        });

        btnPlateste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), PayUActivity.class);
                startActivity(intent1);
            }
        });

        intent = getIntent();

        if(intent.hasExtra(AfiseazaFactura.VIEW_FACTURA)){

            Factura factura = (Factura)intent.getSerializableExtra(AfiseazaFactura.VIEW_FACTURA); //aici deserializam
            etDenumireafac.setText(factura.getDenumireFurn());
            etDataEmitere.setText(factura.getDataEmitere());
            etSuma.setText("" + factura.getSuma());
            etDataScadenta.setText(factura.getDataScadenta());
            etMentiuni.setText(factura.getMentiuni());
        }

//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),AfiseazaFactura.class);
//                startActivity(intent);
//            }
//        });


        //transfer de suma
        btnPlateste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String pret = etSuma.getText().toString();
               Intent myintent = new Intent(FacturaActivity.this, PayUActivity.class);
               myintent.putExtra("pret", pret);
               startActivity(myintent);
            }
        });





    }
}
package com.example.proiecthorhociagurita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PayUActivity extends AppCompatActivity {

    private EditText etSumaDePlata;
    Intent intent;

    private Button btnPlateste;

    private EditText etNrCard;
    private EditText etDataCard;
    private EditText etCVV;
    private EditText etNumeDet;

    public void Verificare(){
        if(etNrCard.getText().toString().isEmpty()){
            etNrCard.setError("Introduceti numarul cardului");
        }else if(etDataCard.getText() == null || etDataCard.getText().toString().trim().isEmpty()){
            etDataCard.setError("Introduceti data cardului");
        }else if(etCVV.getText().toString().isEmpty()){
            etCVV.setError("Introduceti codul de securitate al cardului");
        }else if(etNumeDet.getText() == null || etNumeDet.getText().toString().trim().isEmpty()){
            etNumeDet.setError("Introduceti detinatorul cardului");
        }else
        {
            if(etNrCard.getText().length() < 14){
                etNrCard.setError("Cifre prea putine");
            }else if(etCVV.getText().length() > 3){
                etCVV.setError("Format gresit");
            }else
            {
               // PayU py = new PayU(Long.parseLong(String.valueOf(etNrCard.getText())), etDataCard.getText().toString(), Integer.parseInt(etCVV.getText().toString()), etNumeDet.getText().toString(), Integer.parseInt(etSumaDePlata.getText().toString()));
                Toast.makeText(getApplicationContext(), "A fost realizata plata facturii cu suma  " + etSumaDePlata.getText().toString() , Toast.LENGTH_LONG).show();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_u);

        btnPlateste = findViewById(R.id.btnPlatestePayU);
        etNrCard = findViewById(R.id.editTextNumarulCardului);
        etDataCard = findViewById(R.id.editTextDataCard);
        etCVV = findViewById(R.id.editTextCodSecuritate);
        etNumeDet = findViewById(R.id.editTextDetinatorulCard);

        //transfer de suma
        etSumaDePlata = findViewById(R.id.editTextTotalDePlata);
        intent = getIntent();
        String pret = intent.getExtras().getString("pret");
        etSumaDePlata.setText(pret);

        btnPlateste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Verificare();
            }
        });


    }
}
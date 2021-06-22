package com.example.proiecthorhociagurita;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AfiseazaFactura extends AppCompatActivity {

    public Intent intentFac;

    ListView listView;
    List<Factura> listaFacturi = new ArrayList<>();

    private Button btnAfiseaza;

    private int poz;

    public static final int REQUEST_CODE_VIEW = 300;
    public static final String VIEW_FACTURA = "viewFactura";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afiseaza_factura);

        listView = findViewById(R.id.lvListaFacturi);
        btnAfiseaza = findViewById(R.id.btnAfiseazaFacturi);

        //extragere facturi din XML
        btnAfiseaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExtrageFacturi extrageFacturi = new ExtrageFacturi() {
                    @Override
                    protected void onPostExecute(InputStream inputStream) {
                        listaFacturi.addAll(ExtrageFacturi.facturas);

                        FacturaAdapter adapter = new FacturaAdapter(getApplicationContext(), R.layout.elemente_factura_adapter, listaFacturi, getLayoutInflater());
                        listView.setAdapter(adapter);
                    }
                };
                try {
                    extrageFacturi.execute(new URL("https://pastebin.com/raw/cVQ2hzhC"));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            }
        });

        //stergere elemete
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                final Factura factura = listaFacturi.get(position);
                final FacturaAdapter adapter = (FacturaAdapter)listView.getAdapter();

                AlertDialog dialog = new AlertDialog.Builder(AfiseazaFactura.this).
                        setTitle("Confirmare stergere").setMessage("Siguri doriti sa stergeti?").setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Nu s-a sters nimic", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listaFacturi.remove(factura);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(),"A fost stearsa factura : " +factura.toString(), Toast.LENGTH_LONG).show();
                    }
                }).create();

                dialog.show();
                return true;
            }
        });

        //permitere vizualizare sau editare
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                poz = position;
                intentFac = new Intent(getApplicationContext(), FacturaActivity.class);
                intentFac.putExtra(VIEW_FACTURA, listaFacturi.get(position));
                startActivityForResult(intentFac, REQUEST_CODE_VIEW);
            }
        });
    }


    //aici verificam daca totul este ok pentru a vizualiza datele in Factura activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_VIEW && resultCode == RESULT_OK && data != null){
            Factura factura = (Factura)data.getSerializableExtra(FacturaActivity.ADD_FACTURA);
            if(factura != null){
                listaFacturi.get(poz).setDenumireFurn(factura.getDenumireFurn());
                listaFacturi.get(poz).setDataEmitere(factura.getDataEmitere());
                listaFacturi.get(poz).setSuma(factura.getSuma());
                listaFacturi.get(poz).setDataScadenta(factura.getDataScadenta());
                listaFacturi.get(poz).setMentiuni(factura.getDataScadenta());
                FacturaAdapter adapter = new FacturaAdapter(getApplicationContext(), R.layout.elemente_factura_adapter, listaFacturi, getLayoutInflater());
                listView.setAdapter(adapter);
            }
        }
    }
}

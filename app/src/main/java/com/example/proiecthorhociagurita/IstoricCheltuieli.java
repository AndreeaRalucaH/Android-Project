package com.example.proiecthorhociagurita;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class IstoricCheltuieli extends AppCompatActivity {

    private ListView listViewCheltuieli;
    private List<Cheltuiala> listaCheltuieliDB;
    private Button btnMancare;
    private Button btnDistractie;
    private Button btnUtilitati;
    private Button btnAltele;
    private Button btnToate;
    private int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_istoric_cheltuieli);

        listViewCheltuieli = findViewById(R.id.listViewCheltuieliDB);
        final AplicatieDB aplicatieDB = AplicatieDB.getInstanta(getApplicationContext());
        listaCheltuieliDB = aplicatieDB.getCheltuialaDao().getWhereUserId(Global.loggedUserId);

        btnMancare = findViewById(R.id.btnMancare);
        btnDistractie = findViewById(R.id.btnDistractie);
        btnUtilitati = findViewById(R.id.btnUtilitati);
        btnToate = findViewById(R.id.btnToateCh);
        btnAltele = findViewById(R.id.btnAlteleCh);

        List<Button> listaButoane = new ArrayList<>();
        listaButoane.add(btnMancare);
        listaButoane.add(btnDistractie);
        listaButoane.add(btnUtilitati);
        listaButoane.add(btnAltele);
        listaButoane.add(btnToate);
        final List<String> listaCriterii = new ArrayList<>();
        listaCriterii.add("MANCARE");
        listaCriterii.add("DISTRACTIE");
        listaCriterii.add("UTILITATI");
        listaCriterii.add("ALTELE");
        listaCriterii.add("TOATE");


        for(Button b : listaButoane){
            final String criteriu = listaCriterii.get(i);
            if(!criteriu.equals("TOATE")){
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listaCheltuieliDB = aplicatieDB.getCheltuialaDao().getWhereTip(Global.loggedUserId, criteriu);
                        drawList();
                    }
                });
            }
            else{
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listaCheltuieliDB = aplicatieDB.getCheltuialaDao().getWhereUserId(Global.loggedUserId);
                        drawList();
                    }
                });
            }

            i++;
        }

        //stergere element
        listViewCheltuieli.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                final Cheltuiala cheltuiala = listaCheltuieliDB.get(position);
                final CheltuialaAdapter adapter = (CheltuialaAdapter)listViewCheltuieli.getAdapter();

                AlertDialog dialog = new AlertDialog.Builder(IstoricCheltuieli.this).
                        setTitle("Confirmare stergere").setMessage("Siguri doriti sa stergeti?").setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Nu s-a sters nimic", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listaCheltuieliDB.remove(cheltuiala);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(),"A fost stearsa cheltuiala : " + cheltuiala.toString(), Toast.LENGTH_LONG).show();
                        aplicatieDB.getCheltuialaDao().deleteWhere(cheltuiala.getId());
                    }
                }).create();

                dialog.show();
                return true;
            }
        });

    }

    public void drawList(){
        CheltuialaAdapter adapter = new CheltuialaAdapter(getApplicationContext(), R.layout.elemente_istoric, listaCheltuieliDB, getLayoutInflater());
        listViewCheltuieli.setAdapter(adapter);
    }
}
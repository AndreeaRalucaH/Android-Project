package com.example.proiecthorhociagurita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class IstoricVenituri extends AppCompatActivity {

    private ListView listViewVenituri;
    private List<Venit> listaVenituriFB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_istoric_venituri);

    listViewVenituri = findViewById(R.id.listViewVenituriFB);
    listaVenituriFB = new ArrayList<>();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://gestionarefinanciaraproj-default-rtdb.firebaseio.com/");
    final DatabaseReference myRef = firebaseDatabase.getReference("gestionarefinanciaraproj-default-rtdb");
    myRef.keepSynced(true);

        ValueEventListener messageListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    listaVenituriFB.clear();
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        Venit venit = ds.getValue(Venit.class);
                        if(venit.getUserId()==Global.loggedUserId) listaVenituriFB.add(venit);
                    }
                    drawList();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        myRef.child("gestionarefinanciaraproj-default-rtdb").addValueEventListener(messageListener);

    }

    public void drawList(){
        VenitAdapter adapter = new VenitAdapter(getApplicationContext(), R.layout.elemente_istoric_venit, listaVenituriFB, getLayoutInflater());
        listViewVenituri.setAdapter(adapter);
    }
}
package com.example.proiecthorhociagurita;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class VenitAdapter extends ArrayAdapter<Venit> {
    private Context context;
    private int resource;
    private List<Venit> listaVenituri;
    private LayoutInflater layoutInflater;

    public VenitAdapter(@NonNull Context context, int resource, List<Venit> listaVenituri, LayoutInflater layoutInflater) {
        super(context, resource, listaVenituri);
        this.layoutInflater = layoutInflater;
        this.context = context;
        this.resource = resource;
        this.listaVenituri = listaVenituri;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(resource, parent, false);

        Venit venit = listaVenituri.get(position);
        if(venit != null){
            TextView tv1 = view.findViewById(R.id.denumireVen);
            tv1.setText(venit.getProvenienta());

            TextView tv2 = view.findViewById(R.id.sumaVen);
            tv2.setText(new StringBuilder().append(String.valueOf(venit.getSuma())).append(" RON").toString());

            TextView tv3 = view.findViewById(R.id.dataVenit);
            tv3.setText(venit.getDataVenit());

            TextView tv4 = view.findViewById(R.id.tipVenit);
            tv4.setText(venit.getTipVenit());

        }

        return view;
    }
}

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

public class CheltuialaAdapter extends ArrayAdapter<Cheltuiala> {

    private Context context;
    private int resource;
    private List<Cheltuiala> listaCheltuieli;
    private LayoutInflater layoutInflater;

    public CheltuialaAdapter(@NonNull Context context, int resource, List<Cheltuiala> listaCheltuieli, LayoutInflater layoutInflater) {
        super(context, resource, listaCheltuieli);
        this.layoutInflater = layoutInflater;
        this.context = context;
        this.resource = resource;
        this.listaCheltuieli = listaCheltuieli;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(resource, parent, false);

        Cheltuiala cheltuiala = listaCheltuieli.get(position);
        if(cheltuiala != null){
            TextView tv1 = view.findViewById(R.id.denumire);
            tv1.setText(cheltuiala.getDenumire());

            TextView tv2 = view.findViewById(R.id.suma);
            tv2.setText(new StringBuilder().append(String.valueOf(cheltuiala.getSuma())).append(" RON").toString());

            TextView tv3 = view.findViewById(R.id.dataCheltuiala);
            tv3.setText(cheltuiala.getDataCheltuiala());

            TextView tv4 = view.findViewById(R.id.tipCheltuiala);
            tv4.setText(cheltuiala.getTipCheltuiala());

            TextView tv5 = view.findViewById(R.id.notite);
            tv5.setText(cheltuiala.getNotite());
        }

        return view;
    }
}

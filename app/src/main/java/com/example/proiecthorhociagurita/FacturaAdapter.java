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

public class FacturaAdapter extends ArrayAdapter<Factura> {

    private Context contex;
    private int resource;
    private List<Factura> listFacutra;
    private LayoutInflater layoutInflater;


    public FacturaAdapter(@NonNull Context contex, int resource, List<Factura> listFacutra, LayoutInflater layoutInflater){
        super(contex,resource,listFacutra);
        this.contex=contex;
        this.resource=resource;
        this.listFacutra = listFacutra;
        this.layoutInflater = layoutInflater;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        View view = layoutInflater.inflate(resource, parent, false);

        Factura factura = listFacutra.get(position);

        if(factura != null)
        {
            TextView tv1 = view.findViewById(R.id.tvAdapterDenumireFurnizor);
            tv1.setText(factura.getDenumireFurn());

            TextView tv2 = view.findViewById(R.id.tvAdapterDataEmitere);
            tv2.setText(factura.getDataEmitere());

            TextView tv3 = view.findViewById(R.id.tvAdapterSuma);
            tv3.setText(String.valueOf(factura.getSuma()));

            TextView tv4 = view.findViewById(R.id.tvAdapterDaraScadenta);
            tv4.setText(factura.getDataScadenta());

            TextView tv5 = view.findViewById(R.id.tvAdapterMentiuni);
            tv5.setText(factura.getMentiuni());
        }

        return view;
    }

}

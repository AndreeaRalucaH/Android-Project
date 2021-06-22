package com.example.proiecthorhociagurita;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class CheltuieliFragment extends Fragment {

    private ImageView iv_pieChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_cheltuieli, container, false);
        iv_pieChart = view.findViewById(R.id.iv_pie_ch);

        iv_pieChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), PieChartActivity.class);
                intent.putExtra("EXTRA_GRAPH_TYPE", "CHELTUIELI");
                startActivity(intent);
            }
        });

        return view;
    }
}
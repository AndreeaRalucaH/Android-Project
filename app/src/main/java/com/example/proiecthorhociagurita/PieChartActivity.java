package com.example.proiecthorhociagurita;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

public class PieChartActivity extends AppCompatActivity {

    private TextView tvTitlu;

    private class DownloadDataSpendings extends AsyncTask<String, Integer, Long>{

        Pie pie = AnyChart.pie();
        List<DataEntry> data = new ArrayList<>();

        @Override
        protected Long doInBackground(String... strings) {
            int count = strings.length;
            List<Long> results = new ArrayList<>();
            long sum=0;
            AplicatieDB aplicatieDB = AplicatieDB.getInstanta(getApplicationContext());
            for(int i=0; i<count; i++){
                sum = aplicatieDB.getCheltuialaDao().getSumaCheltuieliTip(Global.loggedUserId, strings[i]);
                results.add(sum);
                data.add(new ValueDataEntry(strings[i], sum));
            }
            return sum;
        }

        protected void onPostExecute(Long result) {

            pie.data(data);

            AnyChartView anyChartView = (AnyChartView) findViewById(R.id.any_chart_view);
            anyChartView.setChart(pie);
        }
    }

    private class DownloadDataEarnings extends AsyncTask<String, Integer, Long>{

        Pie pie = AnyChart.pie();
        List<DataEntry> data = new ArrayList<>();

        @Override
        protected Long doInBackground(String... strings) {
            int count = strings.length;
            List<Long> results = new ArrayList<>();
            long sum=0;
            AplicatieDB aplicatieDB = AplicatieDB.getInstanta(getApplicationContext());
            for(int i=0; i<count; i++){
                sum = aplicatieDB.getVenitDao().getSumaVenituriTip(Global.loggedUserId, strings[i]);
                results.add(sum);
                data.add(new ValueDataEntry(strings[i], sum));
            }
            return sum;
        }

        protected void onPostExecute(Long result) {

            pie.data(data);

            AnyChartView anyChartView = (AnyChartView) findViewById(R.id.any_chart_view);
            anyChartView.setChart(pie);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        tvTitlu = findViewById(R.id.tv_Grafic);


        if(getIntent().getStringExtra("EXTRA_GRAPH_TYPE").equals("CHELTUIELI"))
        {
            tvTitlu.setText(R.string.titluGraficCheltuieli);
            new DownloadDataSpendings().execute("MANCARE", "DISTRACTIE", "UTILITATI", "ALTELE");
        }
        else if (getIntent().getStringExtra("EXTRA_GRAPH_TYPE").equals("VENITURI"))
        {
            tvTitlu.setText(R.string.titluGraficVenituri);
            new DownloadDataEarnings().execute("CASH", "CARD", "CEC", "ALTELE");
        }

    }
}
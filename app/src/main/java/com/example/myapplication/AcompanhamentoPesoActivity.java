package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AcompanhamentoPesoActivity extends AppCompatActivity {

    GraphView GraficoPeso;
    LineGraphSeries<DataPoint> series;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acompanhamento_peso);

        GraficoPeso = (GraphView) findViewById(R.id.GraficoPeso);
        series = new LineGraphSeries<>(getDataPoint());
        GraficoPeso.addSeries(series);

        GraficoPeso.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX){
                if(isValueX){
                    return sdf.format(new Date((long) value));
                }
                else{
                    return super.formatLabel(value, isValueX);
                }
            }
        });

        ImageView pesoBack;
        pesoBack = findViewById(R.id.pesoBack);
        pesoBack.setOnClickListener(this :: teste);
    }

    private void teste(View view) {
        Intent intent = new Intent(this, indexActivity.class);
        startActivity(intent);
    }

    private DataPoint[] getDataPoint(){
        DataPoint[] dp=new DataPoint[]{
                new DataPoint(new Date().getTime(), 65),
        };
        return dp;
    }
}
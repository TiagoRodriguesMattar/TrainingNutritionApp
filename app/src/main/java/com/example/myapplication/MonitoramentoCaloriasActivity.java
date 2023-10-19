package com.example.myapplication;

import androidx.annotation.NonNull;
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

public class MonitoramentoCaloriasActivity extends AppCompatActivity {

    GraphView GraficoCalorias;
    LineGraphSeries<DataPoint> series;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoramento_calorias);

        GraficoCalorias = (GraphView) findViewById(R.id.GraficoCalorias);
        series = new LineGraphSeries<>(getDataPoint());
        GraficoCalorias.addSeries(series);

        GraficoCalorias.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
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

        ImageView caloriasBack;
        caloriasBack = findViewById(R.id.caloriasBack);
        caloriasBack.setOnClickListener(this :: teste);
    }

    private void teste(View view) {
        Intent intent = new Intent(this, indexActivity.class);
        startActivity(intent);
    }

    private DataPoint[] getDataPoint(){
        DataPoint[] dp=new DataPoint[]{
                new DataPoint(new Date().getTime(), 80),
        };
        return dp;
    }
}
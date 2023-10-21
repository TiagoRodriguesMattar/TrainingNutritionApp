package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AcompanhamentoPesoActivity extends AppCompatActivity {


    Button insertPeso;
    EditText Peso;
    GraphView GraficoPeso;
    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[0]);

    MyHelperAcoPeso myHelperAcoPeso;
    SQLiteDatabase sqLiteDatabase;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acompanhamento_peso);

        insertPeso = (Button) findViewById(R.id.InserirPeso);
        Peso = (EditText) findViewById(R.id.inputPeso);
        GraficoPeso = (GraphView) findViewById(R.id.GraficoPeso);
        myHelperAcoPeso = new MyHelperAcoPeso(this);
        sqLiteDatabase = myHelperAcoPeso.getWritableDatabase();

        GraficoPeso.addSeries(series);

        /*GraficoPeso.getViewport().setScrollable(true);
        GraficoPeso.getViewport().setScrollableY(true);

        GraficoPeso.getViewport().setScalable(true);
        GraficoPeso.getViewport().setScalableY(true);*/


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

        series.resetData(getDataPoint());

        exqInsert();

        ImageView pesoBack;
        pesoBack = findViewById(R.id.pesoBack);
        pesoBack.setOnClickListener(this :: teste);
    }

    private void exqInsert(){
        insertPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long xValue = new Date().getTime();
                int yValue = Integer.parseInt(Peso.getText().toString());

                myHelperAcoPeso.insertData(xValue, yValue);

                series.resetData(getDataPoint());
            }
        });
    }

    private DataPoint[] getDataPoint() {
        String[] columns={"xValues", "yValues"};
        Cursor cursor = sqLiteDatabase.query("Peso", columns, null, null, null, null, null);
        DataPoint[] dp = new DataPoint[cursor.getCount()];

        for(int i = 0;i < cursor.getCount();i++){
            cursor.moveToNext();
            dp[i] = new DataPoint(cursor.getLong(0), cursor.getInt(1));
        }

        return dp;
    }

    private void teste(View view) {
        Intent intent = new Intent(this, indexActivity.class);
        startActivity(intent);
    }
}
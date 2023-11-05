package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AcompanhamentoPesoActivity extends AppCompatActivity {


    Button insertPeso;
    EditText inputPeso;

    GraphView GraficoPeso;
    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[0]);

    MyHelperAcoPeso myHelperAcoPeso;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acompanhamento_peso);

        insertPeso = (Button) findViewById(R.id.InserirPeso);
        inputPeso = (EditText) findViewById(R.id.inputPeso);
        GraficoPeso = (GraphView) findViewById(R.id.GraficoPeso);
        myHelperAcoPeso = new MyHelperAcoPeso(this);
        sqLiteDatabase = myHelperAcoPeso.getWritableDatabase();

        GraficoPeso.getViewport().setXAxisBoundsManual(true);
        GraficoPeso.getViewport().setMinX(0);
        GraficoPeso.getViewport().setMaxX(10);
        GraficoPeso.getViewport().setYAxisBoundsManual(true);
        GraficoPeso.getViewport().setMinY(0);
        GraficoPeso.getViewport().setMaxY(150);

        GraficoPeso.getViewport().setScalable(true);
        GraficoPeso.getViewport().setScalableY(true);

        series = new LineGraphSeries<DataPoint>(getDataPoint());
        GraficoPeso.addSeries(series);

        series.setDrawDataPoints(true);

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                String msg = "Peso: " + dataPoint.getY() + "Kg";
                Toast.makeText(AcompanhamentoPesoActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        series.setDrawBackground(true);
        series.setBackgroundColor(Color.argb(60,95,226,156));

        exqInsert();

        ImageView pesoBack;
        pesoBack = findViewById(R.id.pesoBack);
        pesoBack.setOnClickListener(this :: teste);
    }

    private void exqInsert(){
        insertPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String query = "SELECT xValues FROM Peso ORDER BY xValues DESC LIMIT 1";
                Cursor cursor = sqLiteDatabase.rawQuery(query, null);
                int lastXValue = 0;

                if (cursor.moveToFirst()) {
                    lastXValue = cursor.getInt(0); // Obtém o último valor de xValues
                    // Faça o que você precisa com lastXValue
                } else {
                    // Trate o caso em que a tabela está vazia
                }
                cursor.close();

                int yValue = Integer.parseInt(inputPeso.getText().toString());
                lastXValue = lastXValue+1;
                int xValue = lastXValue;


                myHelperAcoPeso.insertData(xValue, yValue);

                series = new LineGraphSeries<DataPoint>(getDataPoint());
                GraficoPeso.addSeries(series);

                series.setDrawDataPoints(true);

                series.setOnDataPointTapListener(new OnDataPointTapListener() {
                    @Override
                    public void onTap(Series series, DataPointInterface dataPoint) {
                        String msg = "Peso: " + dataPoint.getY() + "Kg";
                        Toast.makeText(AcompanhamentoPesoActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

                series.setDrawBackground(true);
                series.setBackgroundColor(Color.argb(60,95,226,156));

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
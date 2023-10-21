package com.example.myapplication;

import androidx.annotation.NonNull;
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

public class MonitoramentoCaloriasActivity extends AppCompatActivity {


    Button insertCalorias;
    EditText Ganho;
    EditText Perda;

    GraphView GraficoCalorias;
    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[0]);

    MyHelperMonCal myHelperMonCal;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoramento_calorias);

        insertCalorias = (Button) findViewById(R.id.InserirCal);
        Ganho = (EditText) findViewById(R.id.inputCalGanhas);
        Perda = (EditText) findViewById(R.id.inputCalPerdidas);
        GraficoCalorias = (GraphView) findViewById(R.id.GraficoCalorias);
        myHelperMonCal = new MyHelperMonCal(this);
        sqLiteDatabase = myHelperMonCal.getWritableDatabase();

        GraficoCalorias.getViewport().setXAxisBoundsManual(true);
        GraficoCalorias.getViewport().setMinX(0);
        GraficoCalorias.getViewport().setMaxX(10);
        GraficoCalorias.getViewport().setYAxisBoundsManual(true);
        GraficoCalorias.getViewport().setMinY(-2000);
        GraficoCalorias.getViewport().setMaxY(2000);

        GraficoCalorias.getViewport().setScalable(true);
        GraficoCalorias.getViewport().setScalableY(true);

        series = new LineGraphSeries<DataPoint>(getDataPoint());
        GraficoCalorias.addSeries(series);

        series.setDrawDataPoints(true);

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                String msg = "Saldo Calorias: " + dataPoint.getY() + "Kcal";
                Toast.makeText(MonitoramentoCaloriasActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        series.setDrawBackground(true);
        series.setBackgroundColor(Color.argb(60,95,226,156));

        exqInsert();

        ImageView caloriasBack;
        caloriasBack = findViewById(R.id.caloriasBack);
        caloriasBack.setOnClickListener(this :: teste);
    }

    private void exqInsert(){
        insertCalorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String query = "SELECT xValues FROM Calorias ORDER BY xValues DESC LIMIT 1";
                Cursor cursor = sqLiteDatabase.rawQuery(query, null);
                int lastXValue = 0;

                if (cursor.moveToFirst()) {
                    lastXValue = cursor.getInt(0); // Obtém o último valor de xValues
                    // Faça o que você precisa com lastXValue
                } else {
                    // Trate o caso em que a tabela está vazia
                }
                cursor.close();

                int n1 = Integer.parseInt(Ganho.getText().toString());
                int n2 = Integer.parseInt(Perda.getText().toString());
                int yValue = n1 - n2;
                lastXValue = lastXValue+1;
                int xValue = lastXValue;


                myHelperMonCal.insertData(xValue, yValue);

                series = new LineGraphSeries<DataPoint>(getDataPoint());
                GraficoCalorias.addSeries(series);
                series.setDrawDataPoints(true);

                series.setOnDataPointTapListener(new OnDataPointTapListener() {
                    @Override
                    public void onTap(Series series, DataPointInterface dataPoint) {
                        String msg = "Saldo Calorias: " + dataPoint.getY() + "Kcal";
                        Toast.makeText(MonitoramentoCaloriasActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

                series.setDrawBackground(true);
                series.setBackgroundColor(Color.argb(60,95,226,156));

            }
        });
    }

    private DataPoint[] getDataPoint() {
        String[] columns={"xValues", "yValues"};
        Cursor cursor = sqLiteDatabase.query("Calorias", columns, null, null, null, null, null);
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
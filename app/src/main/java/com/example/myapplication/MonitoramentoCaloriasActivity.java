package com.example.myapplication;

import androidx.annotation.NonNull;
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

public class MonitoramentoCaloriasActivity extends AppCompatActivity {


    Button insertCalorias;
    EditText Ganho;
    EditText Perda;

    GraphView GraficoCalorias;
    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[0]);

    MyHelperMonCal myHelperMonCal;
    SQLiteDatabase sqLiteDatabase;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");

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

        GraficoCalorias.addSeries(series);

        /*GraficoCalorias.getViewport().setXAxisBoundsManual(true);
        GraficoCalorias.getViewport().setYAxisBoundsManual(true);
        GraficoCalorias.getViewport().setMinY(-30);
        GraficoCalorias.getViewport().setMaxY(30);

        GraficoCalorias.getViewport().setScrollable(true);
        GraficoCalorias.getViewport().setScrollableY(true);*/


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

        series.resetData(getDataPoint());

        exqInsert();

        ImageView caloriasBack;
        caloriasBack = findViewById(R.id.caloriasBack);
        caloriasBack.setOnClickListener(this :: teste);
    }

    private void exqInsert(){
        insertCalorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long xValue = new Date().getTime();
                int n1 = Integer.parseInt(Ganho.getText().toString());
                int n2 = Integer.parseInt(Perda.getText().toString());
                int yValue = n1 - n2;

                myHelperMonCal.insertData(xValue, yValue);

                series.resetData(getDataPoint());
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
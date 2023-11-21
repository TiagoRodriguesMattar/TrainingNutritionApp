package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class TreinoPersonalizadoActivity extends AppCompatActivity{

    private RecyclerView TreinoRV;
    private TreinoPersoRVAdapter treinoPersoRVAdapter;
    private NumberPicker numberPickerNumber_repet;
    private NumberPicker numberPickerNumber_series;
    private String itemSelecionado;
    private ArrayList<TreinosPersonalizadosArray> arrayTreinos;
    private int valorNumberPickerRepet,valorNumberPickerSeries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treino_personalizado);
        Spinner spinner = findViewById(R.id.spinner);
        numberPickerNumber_repet = findViewById(R.id.numberpicker_repet);
        numberPickerNumber_repet.setMaxValue(15);
        numberPickerNumber_repet.setMinValue(1);
        numberPickerNumber_series = findViewById(R.id.numberpicker_series);
        numberPickerNumber_series.setMaxValue(15);
        numberPickerNumber_series.setMinValue(1);

        arrayTreinos = new ArrayList<>();

        TreinoRV = findViewById(R.id.treinoRV);
        treinoPersoRVAdapter = new TreinoPersoRVAdapter(arrayTreinos,this);
        TreinoRV.setLayoutManager(new LinearLayoutManager(this));
        TreinoRV.setAdapter(treinoPersoRVAdapter);

        RecyclerViewItemDecor itemDecor = new RecyclerViewItemDecor(1);
        TreinoRV.addItemDecoration(itemDecor);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opcoes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        Button addTreino = findViewById(R.id.buttonAdd);
        addTreino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valorNumberPickerRepet = numberPickerNumber_repet.getValue();
                valorNumberPickerSeries = numberPickerNumber_series.getValue();
                Log.d("CREATION","Exercicio: " + valorNumberPickerRepet);
                Log.d("CREATION","Exercicio: " + valorNumberPickerSeries);
                Log.d("CREATION","Exercicio: " + itemSelecionado);
                try{
                    arrayTreinos.add(new TreinosPersonalizadosArray(itemSelecionado,valorNumberPickerSeries,valorNumberPickerRepet));

                }
                catch(Exception e){
                    Log.d("CREATION","Erro: " + e);
                }

                Log.d("CREATION","Exercicio: " + arrayTreinos.get(0).getExercicio());
            }
        });

        Button showTreino = findViewById(R.id.showTreino);

        showTreino.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                treinoPersoRVAdapter.notifyDataSetChanged();
            }
        }));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemSelecionado = parent.getItemAtPosition(position).toString();
                // Faça algo com o item selecionado, se necessário
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Ação quando nada é selecionado
            }
        });
    }


}
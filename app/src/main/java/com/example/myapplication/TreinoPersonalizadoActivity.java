package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TreinoPersonalizadoActivity extends AppCompatActivity{

    private RecyclerView TreinoRV;
    private TreinoPersoRVAdapter treinoPersoRVAdapter;
    private NumberPicker numberPickerNumber_repet;
    private NumberPicker numberPickerNumber_series;
    private String itemSelecionado;
    private ArrayList<ExAdapter> exerciciosAdapter;
    private ArrayList<Exercicio> exercicios = new ArrayList<>();
    private ArrayList<TreinosPersonalizadosArray> arrayTreinos;
    private int valorNumberPickerRepet,valorNumberPickerSeries;
    ImageView videoBack2;
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
        exerciciosAdapter = new ArrayList<>();

        TreinoRV = findViewById(R.id.treinoRV);
        treinoPersoRVAdapter = new TreinoPersoRVAdapter(exerciciosAdapter,this);
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
                SharedPreferences preferences = getSharedPreferences("User_Key", MODE_PRIVATE);
                String user = preferences.getString("User", "");

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:3000/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RetrofitService retrofitService = retrofit.create(RetrofitService.class);
                Call<ArrayList<Exercicio>> call;
                call = retrofitService.getTreino(user);

                call.enqueue(new Callback<ArrayList<Exercicio>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Exercicio>> call, Response<ArrayList<Exercicio>> response) {
                        Log.d("CREATION","Deu certo api");

                            exerciciosAdapter.clear();
                            ArrayList<Exercicio> exe = response.body();

                            for (Exercicio exercicio : exe) {

                                String nomeExercicio = exercicio.getNome();
                                int repeticoes = exercicio.getRepeticoes();
                                int series = exercicio.getSeries();
                                exerciciosAdapter.add(new ExAdapter(exercicio));
                                //Log.d("CREATION",nomeExercicio + " " + series + " " + repeticoes);
                                // Faça o que deseja com cada objeto Exercicio aqui
                            }

                        try{
                            treinoPersoRVAdapter.notifyDataSetChanged();
                        } catch (Exception e){

                            Log.e("CREATION", "Erro: " + e);
                        }

                    }

                    @Override
                    public void onFailure(Call<ArrayList<Exercicio>> call, Throwable t) {
                        Log.d("CREATION","Deu merda");
                        Log.e("API Failure", "Erro na chamada da API: " + t.getMessage());
                    }
                });

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
        Button saveTreino = findViewById(R.id.saveTreino);
        saveTreino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!arrayTreinos.isEmpty()) {
                    try {

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://10.0.2.2:3000/") // Substitua pela URL base correta
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

                        SharedPreferences preferences = getSharedPreferences("User_Key", MODE_PRIVATE);
                        String user = preferences.getString("User", "");

                        for (int i = 0; i < arrayTreinos.size(); i++) {
                            Exercicio exercicio = new Exercicio(arrayTreinos.get(i).getExercicio(),Integer.parseInt(arrayTreinos.get(i).getRepeticoes()), Integer.parseInt(arrayTreinos.get(i).getSeries()));
                            exercicios.add(exercicio);
                        }

                        DadosEnvio usuario = new DadosEnvio(user, exercicios);


                        Call<Void> call = retrofitService.setTreino(usuario);

                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()) {
                                    //
                                } else {
                                    // Trate falhas na solicitação
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Log.d("CREATION", "Erro: " + t);
                            }
                        });
                    }catch (Exception e){
                        Log.e("TreinoAPI","Erro: " + e);
                    }
                }
                else{
                    Log.e("TreinoAPI","Array vazio");
                }
            }
        });


        videoBack2 = findViewById(R.id.video_volta);
        videoBack2.setOnClickListener(this :: teste);
    }
    private void teste(View view) {
        Intent intent = new Intent(this, indexActivity.class);
        startActivity(intent);
    }
}
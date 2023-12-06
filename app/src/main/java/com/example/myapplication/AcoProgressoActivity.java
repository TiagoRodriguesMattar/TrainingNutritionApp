package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AcoProgressoActivity extends AppCompatActivity {

    private RecyclerView TreinoRV;
    private TreinoPersoRVAdapter treinoPersoRVAdapter;

    private ArrayList<ExAdapter> exerciciosAdapter;
    private ArrayList<Exercicio> exercicios = new ArrayList<>();
    private ArrayList<TreinosPersonalizadosArray> arrayTreinos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aco_progresso);

        arrayTreinos = new ArrayList<>();
        exerciciosAdapter = new ArrayList<>();

        TreinoRV = findViewById(R.id.denisZe);
        treinoPersoRVAdapter = new TreinoPersoRVAdapter(exerciciosAdapter,this);
        TreinoRV.setLayoutManager(new LinearLayoutManager(this));
        TreinoRV.setAdapter(treinoPersoRVAdapter);

        RecyclerViewItemDecor itemDecor = new RecyclerViewItemDecor(1);
        TreinoRV.addItemDecoration(itemDecor);

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

        ImageView ImcBack;
        ImcBack = findViewById(R.id.imc_back);
        ImcBack.setOnClickListener(this :: teste);
    }

    private void teste(View view) {
        Intent intent = new Intent(this, indexActivity.class);
        startActivity(intent);
    }
}
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ViewDesafiosTreinoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_desafios_treino);

        RecyclerView recyclerView = findViewById(R.id.DesafioTreinoReciclerView);

        List<DataDesafioTreino> items = new ArrayList<DataDesafioTreino>();
        items.add(new DataDesafioTreino("Desafio de 100 flexões por 30 dias", "1° semana:\n\n- 5 séries de 20 flexões inclinadas\n\n2° semana:\n\n- 5 séries de 10 flexões normais\n- 2 séries de 25 flexões inclinadas\n\n3° semana:\n\n- 5 séries de 20 flexões normais\n\n4° semana:\n\n- 1 série de 25 flexões explosivas\n- 1 séries de 25 flexões diamantes\n- 1 série de 25 flexões normais\n- 1 séries de 25 flexões abertas", "4 semanas", "iniciante", "Peitoral, Tríceps e Ombro", "60 segundos", R.drawable.pushup));
        items.add(new DataDesafioTreino("Desafio de 10 barras fixas em 30 dias", "1° semana:\n\n- 10 séries de 10 barras australianas\n\n2° semana:\n\n- 5 séries de 5 barras fixas negativas\n- 5 séries de 10 barras australianas\n\n3° semana:\n\n- 5 séries de número máximo de repetições de barras fixas\n- 5 séries de 5 barras fixas negativas\n- 5 séries de 10 barras australianas\n\n4° semana:\n\n- 1 série de 10 barras fixas", "4 semanas", "iniciante", "Costas e Bíceps", "90 segundos", R.drawable.pullup));
        items.add(new DataDesafioTreino("Desafio queima de gordura corporal definitiva", "1° semana:\n\n- 30 minutos de corrida moderada\n- 20 minutos de HIIT\n- 10 minutos de alongamento\n\n2° semana:\n\n- Agachamentos: 3 séries de 12 repetições\n- Supino: 3 séries de 12 repetições\n- 15 minutos de cardio leve\n\n3° semana:\n\n- 30 minutos de corrida moderada\n- 20 minutos de HIIT\n- 10 minutos de alongamento\n\n4° semana:\n\n- Agachamentos: 3 séries de 12 repetições\n- Supino: 3 séries de 12 repetições\n- 15 minutos de cardio leve", "4 semanas", "iniciante", "Full Body", "60 segundos", R.drawable.burningfat));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapterDesafioTreino(getApplicationContext(), items));

        ImageView BtnBack;
        BtnBack = findViewById(R.id.button_back);
        BtnBack.setOnClickListener(this :: teste);
    }

    private void teste(View view) {
        Intent intent = new Intent(this, indexActivity.class);
        startActivity(intent);
    }
}
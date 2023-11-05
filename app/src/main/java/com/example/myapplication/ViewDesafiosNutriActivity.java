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

public class ViewDesafiosNutriActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_desafios_nutri);

        RecyclerView recyclerView = findViewById(R.id.DesafioNutriReciclerView);

        List<DataDesafioNutri> items = new ArrayList<DataDesafioNutri>();
        items.add(new DataDesafioNutri("Redução de açúcar", R.drawable.less_sugar, "Coisas que mais tem açucar:\n-Refrigerante\n-sobremesa\n\nDesafio:\n- 1 semana:\nReduza pela metade o consumo de refrigerante\nsobremesa apenas de final de semana\n-2 semana:\nRefrigerante apenas de fim de semana\nNada de sobremesa"));
        items.add(new DataDesafioNutri("Cortando produtos industrializados", R.drawable.less_industrial, "Teste"));
        items.add(new DataDesafioNutri("Alimentação menos gordurosa", R.drawable.less_fat, "Leme negro"));



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapterDesafioNutri(getApplicationContext(), items));



        ImageView BtnBack;
        BtnBack = findViewById(R.id.button_back);
        BtnBack.setOnClickListener(this :: teste);
    }

    private void teste(View view) {
        Intent intent = new Intent(this, indexActivity.class);
        startActivity(intent);
    }
}
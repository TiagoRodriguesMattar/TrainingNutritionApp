package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import org.apache.commons.lang3.text.translate.UnicodeUnescaper;

public class NutritionActivity extends AppCompatActivity {
    private RecyclerView nutriRV;
    private NutritionRVAdapter NutriRVAdapter;
    private ArrayList<TabelaNutricional> tabela = new ArrayList<>();
    private EditText editText;
    private Button button;
    private String input = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageView videoBack2;
        TranslatorText translateRequest = new TranslatorText();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);
        editText = findViewById(R.id.editText);
        videoBack2 = findViewById(R.id.video_back_2);
        videoBack2.setOnClickListener(this :: voltar);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //UnicodeUnescaper unescaper = new UnicodeUnescaper();
                input = editText.getText().toString();
                ApiRequest(input); // tem q digitar em ingles
            }

        });


        nutriRV = findViewById(R.id.RVnutri);
        NutriRVAdapter = new NutritionRVAdapter(tabela, this);
        nutriRV.setLayoutManager(new LinearLayoutManager(this));
        nutriRV.setAdapter(NutriRVAdapter);

        NutriRVAdapter.notifyDataSetChanged();

    }

    private void voltar(View view) {
        Intent intent = new Intent(this, indexActivity.class);
        startActivity(intent);
    }

    public void ApiRequest(String query) {

        Log.d("CREATION","Entrou apinutri");
        tabela.clear();
        String ApiKey = "uiE8KXsrL6Dtcl/nfpS06g==io1yDWrr4S4qukAr";
        String BASE_URL = "https://api.api-ninjas.com/v1/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call <ArrayList<TabelaNutricional>>call;
        call = retrofitService.getDataNutri(ApiKey, query);

        call.enqueue(new Callback<ArrayList<TabelaNutricional>>() {
            @Override
            public void onResponse(Call<ArrayList<TabelaNutricional>> call, Response<ArrayList<TabelaNutricional>> response) {

                ArrayList<TabelaNutricional> aux = response.body();
                for(int i=0; i<aux.size(); i++){
                    tabela.add(new TabelaNutricional(aux.get(i).getName(),aux.get(i).getCalories(),
                            aux.get(i).getServing_size_g(),aux.get(i).getFat_total_g(),aux.get(i).getFat_saturated_g(),
                            aux.get(i).getProtein_g(),aux.get(i).getSodium_mg(),aux.get(i).getPotassium_mg(),
                            aux.get(i).getCholesterol_mg(), aux.get(i).getCarbohydrates_total_g(), aux.get(i).getFiber_g(), aux.get(i).getSugar_g()));
                    Log.d("CREATION","Retorno funcao get news: " + tabela.get(i).getName());
                }
                try{
                    NutriRVAdapter.notifyDataSetChanged();
                }
                catch (Exception e){
                    Log.e("CREATION","Deu merda notify: " + e);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<TabelaNutricional>> call, Throwable t) {
                Log.d("CREATION","Deu merda");
                Log.e("API Failure", "Erro na chamada da API: " + t.getMessage());
            }
        });
    }
}
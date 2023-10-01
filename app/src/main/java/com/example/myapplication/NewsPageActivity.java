package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsPageActivity extends AppCompatActivity {
    private RecyclerView newsRV;
    private ArrayList<Articles> articles;
    private NewsRVAdapter news_rv_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news_page);

        articles = new ArrayList<>();
        getNews();
        newsRV = findViewById(R.id.idRVNews);
        news_rv_adapter = new NewsRVAdapter(articles,this);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(news_rv_adapter);


        news_rv_adapter.notifyDataSetChanged();
    }
    public void getNews(){
        Log.d("CREATION","Entrou");
        articles.clear();
        String url = "https://newsapi.org/v2/everything?q=nutrição esportiva&sortBy=popularity&apiKey=92b3f225f18c48bcbf9df3c1ec7bef0d";
        String BASE_URL = "https://newsapi.org/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<ApiResponse> call;
        call = retrofitService.getData(url);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.d("CREATION","Deu certo api");
                ApiResponse apiResponse = response.body();
                ArrayList<Articles> articlesArrayList = apiResponse.getArticles();
                //Log.d("CREATION","Retorno funcao get news: " + articlesArrayList.get(2).getTitle());
                for(int i=0 ;i<articlesArrayList.size();i++){
                    articles.add(new Articles(articlesArrayList.get(i).getTitle(),articlesArrayList.get(i).getDescription(),articlesArrayList.get(i).getUrlToImage(),articlesArrayList.get(i).getUrl(),articlesArrayList.get(i).getContent()));
                    Log.d("CREATION","Retorno funcao get news: " + articles.get(i).getTitle());
                    }
                try{
                    news_rv_adapter.notifyDataSetChanged();
                }
                catch(Exception e){
                    Log.d("CREATION", "Eroo: " + e);
                }

            }
            // getContent retorna certo, getTitle retorna description, getDescription retorna o url, get Url retorna o title;
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d("CREATION","Deu merda");
                Log.e("API Failure", "Erro na chamada da API: " + t.getMessage());
            }
        });
    }

}
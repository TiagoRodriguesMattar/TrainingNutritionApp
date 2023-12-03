package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RetrofitService {
    @GET
    Call<ApiResponse> getData(@Url String url);
    @POST("/addTreino")
    Call<Void> setTreino(@Body DadosEnvio dados);
    @GET("/getTreino")
    Call<ArrayList<Exercicio>> getTreino(@Query("User") String user);
}

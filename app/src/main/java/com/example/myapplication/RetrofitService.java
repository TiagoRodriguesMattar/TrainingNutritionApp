package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RetrofitService {
    @GET
    Call<ApiResponse> getData(@Url String url );

    @GET("nutrition")
    Call<ArrayList<TabelaNutricional>> getDataNutri(@Header("X-Api-Key") String ApiKeY, @Query("query") String query);
}


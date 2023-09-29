package com.example.myapplication;

import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Url;

public interface RetrofitService {
    @GET
    Call<ApiResponse> getData(@Url String url);
}

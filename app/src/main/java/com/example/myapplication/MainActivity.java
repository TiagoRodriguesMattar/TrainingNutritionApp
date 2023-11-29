package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public void TrainingActivity() {
        Intent i = new Intent(this, indexActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);



        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        MaterialButton cadbtn = (MaterialButton) findViewById(R.id.cadbtn);

        //admin and admin
        loginbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = username.getText().toString();
                String senha = password.getText().toString();

                    if (email.equals("admin") && senha.equals("admin")) {
                        TrainingActivity();
                    }
                    else{
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://10.0.2.2:3000/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        RetrofitService retrofitService = retrofit.create(RetrofitService.class);


                        User usuario = new User(email, senha);
                        Call<Void> call = retrofitService.getLoginRes(usuario);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                                    TrainingActivity();
                                } else {
                                    // Trate falhas na solicitação
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Log.d("CREATION", "Erro: " + t);
                            }
                        });
                        }
                                    }
        });

        cadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString();
                String senha = password.getText().toString();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:3000/") // Substitua pela URL base correta
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RetrofitService retrofitService = retrofit.create(RetrofitService.class);


                User usuario = new User(email, senha);
                Call<Void> call = retrofitService.getCadRes(usuario);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {

                        } else {
                            // Trate falhas na solicitação
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("CREATION","Erro: " + t);
                    }
                });
            }
        });


    }

}
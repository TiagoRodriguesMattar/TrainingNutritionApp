package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

public class DietaEspActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dieta_esp);


        Button prox = findViewById(R.id.Ciente);
        CheckBox checkBox = findViewById(R.id.checkBox);

        prox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(checkBox.isChecked()){
                    proxBtn(v);
                }
                else{
                    Toast.makeText(DietaEspActivity.this,"CONFIMRE QUE ESTÁ CIENTE!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageView BtnBack;
        BtnBack = findViewById(R.id.button_back);
        BtnBack.setOnClickListener(this :: teste);
    }


    public void proxBtn(View view) {
        Intent i = new Intent(this, ViewDietaEspActivity.class);
        startActivity(i);
    }

    private void teste(View view) {
        Intent intent = new Intent(this, indexActivity.class);
        startActivity(intent);
    }
}
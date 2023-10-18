package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ImageView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class ImcDataActivity extends AppCompatActivity {
    private EditText Peso;
    private EditText Altura;
    private TextView ResultadoImc;
    private TextView SituacaoImc;
    private Button BotaoCalculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc_data);

        Peso = findViewById(R.id.inputPeso);
        Altura = findViewById(R.id.inputAltura);
        ResultadoImc = findViewById(R.id.resultadoImc);
        BotaoCalculo = findViewById(R.id.calculateImcBtn);
        SituacaoImc = findViewById(R.id.situacaoImc);

        BotaoCalculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Peso.getText().toString().isEmpty()) {
                    Peso.setText("0");
                }

                if (Altura.getText().toString().isEmpty()) {
                    Altura.setText("0");
                }

                double peso = Double.parseDouble(Peso.getText().toString());
                double altura = Double.parseDouble(Altura.getText().toString());
                altura = altura/100;
                double imc = peso / (altura * altura);
                if(imc >= 17 && imc < 18.5){
                    SituacaoImc.setText(String.valueOf("Abaixo do peso"));
                }
                if(imc >= 18.5 && imc < 25){
                    SituacaoImc.setText(String.valueOf("Peso normal"));
                }
                if(imc >= 25 && imc < 30){
                    SituacaoImc.setText(String.valueOf("Acima do peso"));
                }
                if(imc >= 30 && imc < 35){
                    SituacaoImc.setText(String.valueOf("Obesidade I"));
                }
                if(imc >= 35 && imc < 40){
                    SituacaoImc.setText(String.valueOf("Obesidade II"));
                }
                if(imc >= 40){
                    SituacaoImc.setText(String.valueOf("Obesidade III"));
                }
                if(imc < 17){
                    SituacaoImc.setText(String.valueOf("Muito abaixo do peso"));
                }
                DecimalFormat formatador = new DecimalFormat("0.0");
                ResultadoImc.setText(String.valueOf(formatador.format(imc)));
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

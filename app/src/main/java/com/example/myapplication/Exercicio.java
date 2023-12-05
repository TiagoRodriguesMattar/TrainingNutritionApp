package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Exercicio {
    @SerializedName("Repeticoes")
    private int repeticoes;

    @SerializedName("Series")
    private int series;

    @SerializedName("Exercicio")
    private String nome;

    // Getters e Setters

    public Exercicio(String nome, int repeticoes, int series) {
        this.repeticoes = repeticoes;
        this.series = series;
        this.nome = nome;
    }

    public int getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(int repeticoes) {
        this.repeticoes = repeticoes;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

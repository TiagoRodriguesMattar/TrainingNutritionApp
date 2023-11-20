package com.example.myapplication;

public class TreinosPersonalizadosArray {
    private String exercicio;
    private int series, repeticoes;

    public TreinosPersonalizadosArray(String exercicio, int series, int repeticoes) {
        this.exercicio = exercicio;
        this.series = series;
        this.repeticoes = repeticoes;
    }

    public String getExercicio() {
        return exercicio;
    }

    public int getSeries() {
        return series;
    }

    public int getRepeticoes() {
        return repeticoes;
    }

    public void setExercicio(String exercicio) {
        this.exercicio = exercicio;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public void setRepeticoes(int repeticoes) {
        this.repeticoes = repeticoes;
    }
}

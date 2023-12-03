package com.example.myapplication;

import com.example.myapplication.Exercicio;

public class ExAdapter {
    private Exercicio exercicio;

    public ExAdapter(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    public String getNome() {
        return exercicio.getNome();
    }

    public String getSeries() {
        return String.valueOf(exercicio.getSeries());
    }

    public String getRepeticoes() {
        return String.valueOf(exercicio.getRepeticoes());
    }
}

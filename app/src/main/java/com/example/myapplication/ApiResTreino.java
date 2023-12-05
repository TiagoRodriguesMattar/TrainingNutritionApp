package com.example.myapplication;

import java.util.ArrayList;

public class ApiResTreino {
    private ArrayList<Exercicio> exercicios;

    public ApiResTreino(ArrayList<Exercicio> exercicios) {
        this.exercicios = exercicios;
    }

    public ArrayList<Exercicio> getExercicios() {
        return exercicios;
    }

    public void setExercicios(ArrayList<Exercicio> exercicios) {
        this.exercicios = exercicios;
    }
}

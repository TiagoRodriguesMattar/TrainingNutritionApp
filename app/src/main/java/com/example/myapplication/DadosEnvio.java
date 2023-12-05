package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DadosEnvio {
    @SerializedName("User")
    private String user;

    @SerializedName("Treino")
    private List<Exercicio> treino;

    // Getters e SettersW

    public DadosEnvio(String user, List<Exercicio> treino) {
        this.user = user;
        this.treino = treino;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<Exercicio> getTreino() {
        return treino;
    }

        public void setTreino(List<Exercicio> treino) {
        this.treino = treino;
    }
}

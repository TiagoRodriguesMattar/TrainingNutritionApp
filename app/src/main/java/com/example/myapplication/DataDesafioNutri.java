package com.example.myapplication;

import android.widget.Button;

public class DataDesafioNutri {
    String title;
    String sub;
    int image;


    public DataDesafioNutri(String title, int image, String sub) {
        this.title = title;
        this.image = image;
        this.sub = sub;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }
}

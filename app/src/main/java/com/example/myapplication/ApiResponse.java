package com.example.myapplication;

import java.util.ArrayList;


public class ApiResponse {
    private String status;
    private ArrayList<Articles> articles;
    private int totalResults;

    public ApiResponse( int totalResults, String status, ArrayList<Articles> articles) {
        this.status = status;
        this.articles = articles;
        this.totalResults = totalResults;
    }

    public ArrayList<Articles> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Articles> articles) {
        this.articles = articles;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

}




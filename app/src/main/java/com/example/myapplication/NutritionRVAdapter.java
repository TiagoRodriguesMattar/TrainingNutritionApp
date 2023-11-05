package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NutritionRVAdapter extends RecyclerView.Adapter<NutritionRVAdapter.ViewHolder>{



    private ArrayList<TabelaNutricional> TabelaNutri;
    private Context context;

    public NutritionRVAdapter(ArrayList<TabelaNutricional> tabelaNutri, Context context) {
        this.TabelaNutri = tabelaNutri;
        this.context = context;
    }
    @NonNull
    @Override
    public NutritionRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nutri_rv_item,parent, false);
        return new NutritionRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NutritionRVAdapter.ViewHolder holder, int position) {
        TabelaNutricional tabelinha = TabelaNutri.get(position);
        holder.TitleNutri.setText(tabelinha.getName());
        holder.TextNutri.setText(tabelinha.getTable());
    }

    @Override
    public int getItemCount() {
        return TabelaNutri.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView TitleNutri, TextNutri;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TitleNutri = itemView.findViewById(R.id.Title_Nutri);
            TextNutri = itemView.findViewById(R.id.Nutri_text);

        }
    }
}

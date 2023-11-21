package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TreinoPersoRVAdapter extends RecyclerView.Adapter<TreinoPersoRVAdapter.ViewHolder>{

    private ArrayList<TreinosPersonalizadosArray> Treino;
    private Context context;

    public TreinoPersoRVAdapter(ArrayList<TreinosPersonalizadosArray> treino, Context context) {
        this.Treino = treino;
        this.context = context;
    }

    @NonNull
    @Override
    public TreinoPersoRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.treino_personalizado_item,parent,false);
        return new TreinoPersoRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TreinoPersoRVAdapter.ViewHolder holder, int position) {
        TreinosPersonalizadosArray treinos = Treino.get(position);
        holder.Nome.setText(treinos.getExercicio());
        holder.numSerie.setText(treinos.getSeries());
        holder.numRepet.setText(treinos.getRepeticoes());
    }

    @Override
    public int getItemCount() {
        return Treino.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView Nome,numSerie,numRepet;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Nome = itemView.findViewById(R.id.NomeEx);
            numSerie = itemView.findViewById(R.id.NumSeries);
            numRepet = itemView.findViewById(R.id.NumRepet);
        }
    }

}

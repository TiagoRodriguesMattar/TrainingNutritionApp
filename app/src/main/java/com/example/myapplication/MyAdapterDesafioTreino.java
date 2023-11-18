package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapterDesafioTreino extends RecyclerView.Adapter<MyViewHolderDesafioTreino> {

    Context context;
    List<DataDesafioTreino> items;

    public MyAdapterDesafioTreino(Context context, List<DataDesafioTreino> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolderDesafioTreino onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderDesafioTreino(LayoutInflater.from(context).inflate(R.layout.item_view_desafiotreino, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderDesafioTreino holder, int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.sub.setText(items.get(position).getSub());
        holder.duration.setText(items.get(position).getDuration());
        holder.level.setText(items.get(position).getLevel());
        holder.muscleGroups.setText(items.get(position).getMuscleGroups());
        holder.restTime.setText(items.get(position).getRestTime());
        holder.image.setImageResource(items.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
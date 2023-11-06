package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapterDesafioNutri extends RecyclerView.Adapter<MyViewHolderDesafioNutri> {

    Context context;
    List<DataDesafioNutri> items;

    public MyAdapterDesafioNutri(Context context, List<DataDesafioNutri> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolderDesafioNutri onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderDesafioNutri(LayoutInflater.from(context).inflate(R.layout.item_view_desafionutri, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderDesafioNutri holder, int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.image.setImageResource(items.get(position).getImage());
        holder.sub.setText(items.get(position).getSub());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

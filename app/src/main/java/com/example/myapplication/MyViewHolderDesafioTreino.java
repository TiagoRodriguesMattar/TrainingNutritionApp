package com.example.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderDesafioTreino extends RecyclerView.ViewHolder {
    TextView title, sub, duration, muscleGroups, level, restTime;
    ImageView image;

    public MyViewHolderDesafioTreino(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.TitleDNV);
        sub = itemView.findViewById(R.id.SubDNV);
        duration = itemView.findViewById(R.id.DurationDNV);
        muscleGroups = itemView.findViewById(R.id.MuscleGroupsDNV);
        level = itemView.findViewById(R.id.LevelDNV);
        restTime = itemView.findViewById(R.id.RestTimeDNV);
        image = itemView.findViewById(R.id.ImageDNV);
    }
}
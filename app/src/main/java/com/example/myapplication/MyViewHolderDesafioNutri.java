package com.example.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderDesafioNutri extends RecyclerView.ViewHolder {

    ImageView image;
    TextView title, sub;

    public MyViewHolderDesafioNutri(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.ImageDNV);
        title = itemView.findViewById(R.id.TitleDNV);
        sub = itemView.findViewById(R.id.SubDNV);
    }
}

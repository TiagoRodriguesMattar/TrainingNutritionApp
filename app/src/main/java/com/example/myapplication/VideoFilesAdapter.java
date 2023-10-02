package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

public class VideoFilesAdapter extends RecyclerView.Adapter<VideoFilesAdapter.ViewHolder> {
    private ArrayList<MediaFiles> videoList;
    private Context context;

    public VideoFilesAdapter(ArrayList<MediaFiles> videoList, Context context) {
        this.videoList = videoList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.video_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.videoName.setText(videoList.get(position).getDisplayName());
        switch (videoList.get(position).getDisplayName()) {
            case "flexao":
                holder.videoName.setText("Flexão");
                break;
            case "flexao_diamante":
                holder.videoName.setText("Flexão Diamante");
                break;
            case "flexao_aberta":
                holder.videoName.setText("Flexão Aberta");
                break;
            case "flexao_pseudo":
                holder.videoName.setText("Flexão Pseudo");
                break;
            case "flexao_pike":
                holder.videoName.setText("Flexão Ombro");
                break;
            case "flexao_explosiva":
                holder.videoName.setText("Flexão Explosiva");
                break;
            case "extensao_de_triceps":
                holder.videoName.setText("Extensão De Tríceps");
                break;
            case "skull_crunches":
                holder.videoName.setText("Skull Crunches");
                break;
            case "agachamento":
                holder.videoName.setText("Agaquamento");
                break;
            case "rosca_direta":
                holder.videoName.setText("Rosca Direta");
                break;
            case "rosca_martelo":
                holder.videoName.setText("Rosca Martelo");
                break;
            case "panturrilha_em_pe":
                holder.videoName.setText("Panturrilha Em Pé");
                break;
            case "abdominal_normal":
                holder.videoName.setText("Abdominal Normal");
                break;
            case "abdominal_infra":
                holder.videoName.setText("Abdominal Infra");
                break;
            case "barra_fixa":
                holder.videoName.setText("Barra Fixa");
                break;
            case "barra_fixa_fechada":
                holder.videoName.setText("Barra Fixa Fechada");
                break;
            case "barra_fixa_aberta":
                holder.videoName.setText("Barra Fixa Aberta");
                break;
            case "barra_fixa_supinada":
                holder.videoName.setText("Barra Fixa Supinada");
                break;
            default:
                break;
        }
        holder.menu_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "menu more", Toast.LENGTH_SHORT).show();
            }
        });

        switch (videoList.get(position).getDisplayName()) {
            case "flexao":
                holder.musclesGroups.setText("Peitoral");
                break;
            case "flexao_diamante":
                holder.musclesGroups.setText("Peitoral, Tríceps");
                break;
            case "flexao_aberta":
                holder.musclesGroups.setText("Peitoral");
                break;
            case "flexao_pseudo":
                holder.musclesGroups.setText("Peitoral, Ombro");
                break;
            case "flexao_pike":
                holder.musclesGroups.setText("Ombro");
                break;
            case "flexao_explosiva":
                holder.musclesGroups.setText("Peitoral");
                break;
            case "extensao_de_triceps":
                holder.musclesGroups.setText("Tríceps");
                break;
            case "skull_crunches":
                holder.musclesGroups.setText("Tríceps");
                break;
            case "agachamento":
                holder.musclesGroups.setText("Quadríceps, Glúteos");
                break;
            case "rosca_direta":
                holder.musclesGroups.setText("Bíceps");
                break;
            case "rosca_martelo":
                holder.musclesGroups.setText("Bíceps");
                break;
            case "panturrilha_em_pe":
                holder.musclesGroups.setText("Panturrilha");
                break;
            case "abdominal_normal":
                holder.musclesGroups.setText("Abdomên");
                break;
            case "abdominal_infra":
                holder.musclesGroups.setText("Abdomên");
                break;
            case "barra_fixa":
                holder.musclesGroups.setText("Costas");
                break;
            case "barra_fixa_fechada":
                holder.musclesGroups.setText("Costas");
                break;
            case "barra_fixa_aberta":
                holder.musclesGroups.setText("Costas");
                break;
            case "barra_fixa_supinada":
                holder.musclesGroups.setText("Costas");
                break;
            default:
                break;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition(); // Obtenha a posição clicada usando getAdapterPosition()
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(context, VideoPlayerActivity.class);
                    intent.putExtra("position", clickedPosition);
                    intent.putExtra("video_title", videoList.get(clickedPosition).getDisplayName());
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("videoArrayList", videoList);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            }
        });

        // Use Glide para carregar a thumbnail diretamente do MediaStore
        Glide.with(context)
                .load(Uri.parse(videoList.get(position).getPath())) // Converta o caminho para Uri
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail, menu_more;
        TextView videoName, musclesGroups;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            menu_more = itemView.findViewById(R.id.video_menu_more);
            videoName = itemView.findViewById(R.id.video_name);
            musclesGroups = itemView.findViewById(R.id.video_muscles);
        }
    }
}

package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class VideoFilesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private ArrayList<MediaFiles> videoFilesArrayList = new ArrayList<>();
    VideoFilesAdapter videoFilesAdapter;

    ImageView videoBack2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_files);
        recyclerView = findViewById(R.id.videos_rv);
        videoBack2 = findViewById(R.id.video_back_2);
        videoBack2.setOnClickListener(this :: teste);
        showVideoFiles();
    }

    private void teste(View view) {
        Intent intent = new Intent(this, indexActivity.class);
        startActivity(intent);
    }

    private void showVideoFiles() {
        videoFilesArrayList = (ArrayList<MediaFiles>) RawVideoFilesProvider.getRawVideoFiles(this);
        videoFilesAdapter = new VideoFilesAdapter(videoFilesArrayList, this);
        recyclerView.setAdapter(videoFilesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        videoFilesAdapter.notifyDataSetChanged();
    }
}
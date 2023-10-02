package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import dalvik.system.InMemoryDexClassLoader;

public class NewsDetailActivity extends AppCompatActivity {
    String title,desc,content,url,ImageUrl;

    private TextView titleTV, descTV,contentTV;
    private ImageView newsIV;
    private Button readNewsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        title = getIntent().getStringExtra("title");
        desc = getIntent().getStringExtra("desc");
        content = getIntent().getStringExtra("content");
        url = getIntent().getStringExtra("url");
        ImageUrl = getIntent().getStringExtra("image");
        titleTV = findViewById(R.id.Title);
        descTV = findViewById(R.id.SubDescription);
        contentTV = findViewById(R.id.Content);
        newsIV = findViewById(R.id.Image);
        readNewsBtn = findViewById(R.id.BtnReadNews);
        titleTV.setText(title);
        descTV.setText(desc);
        contentTV.setText(content);
        Picasso.get().load(ImageUrl).into(newsIV);
        readNewsBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        }

        );
    }
}
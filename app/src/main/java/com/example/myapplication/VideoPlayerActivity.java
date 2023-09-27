package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class VideoPlayerActivity extends AppCompatActivity implements View.OnClickListener {

    PlayerView playerView;
    SimpleExoPlayer player;
    int position;
    String videoTitle;
    TextView title;
    ArrayList<MediaFiles> mVideoFiles = new ArrayList<>();
    ConcatenatingMediaSource concatenatingMediaSource;
    ImageView nextButton, previousButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_video_player);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        playerView = findViewById(R.id.exoplayer_view);
        position = getIntent().getIntExtra("position", 0);
        videoTitle = getIntent().getStringExtra("video_title");
        mVideoFiles = getIntent().getParcelableArrayListExtra("videoArrayList");
        nextButton = findViewById(R.id.exo_next);
        previousButton = findViewById(R.id.exo_prev);

        title = findViewById(R.id.video_title);
        title.setText(videoTitle);

        nextButton.setOnClickListener(this);
        previousButton.setOnClickListener(this);

        initializePlayer();
    }

    private void initializePlayer() {
        player = new SimpleExoPlayer.Builder(this).build();
        playerView.setPlayer(player);

        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(
                this, Util.getUserAgent(this, "app"));

        concatenatingMediaSource = new ConcatenatingMediaSource();
        for (MediaFiles mediaFile : mVideoFiles) {
            Uri videoUri = Uri.parse(mediaFile.getPath());
            MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(videoUri);
            concatenatingMediaSource.addMediaSource(mediaSource);
        }

        player.setMediaSource(concatenatingMediaSource);
        player.setPlayWhenReady(true);
        player.seekTo(position, C.TIME_UNSET);

        player.addListener(new Player.EventListener() {
            @Override
            public void onPlaybackStateChanged(int state) {
                // Pode lidar com os estados de reprodução, se necessário
            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {
                Toast.makeText(VideoPlayerActivity.this, "Video Playing Error", Toast.LENGTH_SHORT).show();
                finish(); // Fecha a atividade em caso de erro de reprodução
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (player != null) {
            player.release();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (player.isPlaying()) {
            player.stop();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.setPlayWhenReady(false);
        player.getPlaybackState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        player.setPlayWhenReady(true);
        player.getPlaybackState();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        player.setPlayWhenReady(true);
        player.getPlaybackState();
    }

    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.exo_next) {
            try {
                player.stop();
                position++;
                initializePlayer();
            } catch (Exception e) {
                Toast.makeText(this, "No Next Video", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else if (viewId == R.id.exo_prev) {
            try {
                player.stop();
                position--;
                initializePlayer();
            } catch (Exception e) {
                Toast.makeText(this, "No Previous Video", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
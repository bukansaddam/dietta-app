package com.sugadev.dietta.User.Video;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;
import com.sugadev.dietta.R;

public class VideoDetail extends AppCompatActivity {

    VideoView videoOlahraga;
    TextView tvTitle,tvDesc,tvCategory;
    String iVideo, iTitle, iDesc, iCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);

        declaration();
        getData();
        setData();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(iTitle);
    }

    private void getData(){
        Intent intent = getIntent();
        iVideo = intent.getStringExtra("video");
        iTitle = intent.getStringExtra("title");
        iDesc = intent.getStringExtra("desc");
        iCategory = intent.getStringExtra("category");
    }

    private void setData(){
        tvTitle.setText(iTitle);
        tvDesc.setText(iDesc);
        tvCategory.setText(iCategory);
        Uri uri = Uri.parse(iVideo);

        MediaController mc = new MediaController(this);
        mc.setAnchorView(videoOlahraga);
        mc.setMediaPlayer(videoOlahraga);
        videoOlahraga.setMediaController(mc);
        videoOlahraga.setVideoURI(uri);
        videoOlahraga.requestFocus();
        videoOlahraga.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoOlahraga.start();
            }
        });
        videoOlahraga.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoOlahraga.start();
            }
        });
    }

    private void declaration(){
        videoOlahraga = findViewById(R.id.videoPlayer);
        tvTitle = findViewById(R.id.tvTitleVideo);
        tvCategory = findViewById(R.id.tvCategory);
        tvDesc = findViewById(R.id.tvDescVideo);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
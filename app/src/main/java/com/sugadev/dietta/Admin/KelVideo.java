package com.sugadev.dietta.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sugadev.dietta.R;

public class KelVideo extends AppCompatActivity {

    RecyclerView rvVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kel_video);

        rvVideo = findViewById(R.id.rvVideo);
    }

    public void tambahVideo(View view) {
        Intent intent = new Intent(getApplicationContext(), FormAddVideo.class);
        startActivity(intent);
    }
}
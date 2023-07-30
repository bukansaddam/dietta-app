package com.sugadev.dietta.User.Video;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.sugadev.dietta.R;
import com.sugadev.dietta.User.Video.Adapter.VideoAdapter;

public class Cardio extends AppCompatActivity {

    RecyclerView rvVideo;

    int image[] = {
            R.drawable.kare,
            R.drawable.kare,
            R.drawable.kare,
            R.drawable.kare,
            R.drawable.kare
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardio);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Cardio");

        rvVideo = findViewById(R.id.rvVideoCardio);

        dataVideo();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void dataVideo(){
        String[] jdlMakanan = getResources().getStringArray(R.array.judul_makanan);
        String[] descMakanan = getResources().getStringArray(R.array.deskripsi_makanan);

        VideoAdapter videoAdapter = new VideoAdapter(jdlMakanan, descMakanan, image, this);
        rvVideo.setAdapter(videoAdapter);
        rvVideo.setLayoutManager(new LinearLayoutManager(this));
    }
}
package com.sugadev.dietta.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.sugadev.dietta.Admin.MakananAdapter;
import com.sugadev.dietta.R;

public class Yoga extends AppCompatActivity {

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
        setContentView(R.layout.activity_yoga);

        rvVideo = findViewById(R.id.rvVideoOlahraga);

        String[] jdlMakanan = getResources().getStringArray(R.array.judul_makanan);
        String[] descMakanan = getResources().getStringArray(R.array.deskripsi_makanan);

        YogaAdapter yogaAdapter = new YogaAdapter(jdlMakanan, descMakanan, image, this);
        rvVideo.setAdapter(yogaAdapter);
        rvVideo.setLayoutManager(new LinearLayoutManager(this));


    }
}
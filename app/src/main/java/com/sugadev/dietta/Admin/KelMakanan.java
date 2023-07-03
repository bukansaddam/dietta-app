package com.sugadev.dietta.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sugadev.dietta.R;

public class KelMakanan extends AppCompatActivity {

    RecyclerView rvMakanan;

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
        setContentView(R.layout.activity_kel_makanan);

        rvMakanan = findViewById(R.id.rvMakanan);

        String[] jdlMakanan = getResources().getStringArray(R.array.judul_makanan);
        String[] descMakanan = getResources().getStringArray(R.array.deskripsi_makanan);


        MakananAdapter makananAdapter = new MakananAdapter(jdlMakanan, descMakanan, image, this);
        rvMakanan.setAdapter(makananAdapter);
        rvMakanan.setLayoutManager(new LinearLayoutManager(this));

    }

    public void tambahMakanan(View view) {
        Intent intent = new Intent(getApplicationContext(), FormAddMakanan.class);
        startActivity(intent);
    }
}
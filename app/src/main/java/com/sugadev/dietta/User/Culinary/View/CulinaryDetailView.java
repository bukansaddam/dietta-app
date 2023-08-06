package com.sugadev.dietta.User.Culinary.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.sugadev.dietta.R;

public class CulinaryDetailView extends AppCompatActivity {

    ImageView imgCulinary;
    TextView title, description, protein, lemak, karbo, kalori;
    String imgUrl, iTitle, iDescription;
    int iProtein, iLemak, iKarbo, iKalori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culinary_detail);

        initialization();
        getData();
        setData();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(iTitle);
    }

    private void setData(){
        Picasso.get().load(imgUrl).into(imgCulinary);
        title.setText(iTitle);
        description.setText(iDescription);
        protein.setText(String.valueOf(iProtein));
        lemak.setText(String.valueOf(iLemak));
        karbo.setText(String.valueOf(iKarbo));
        kalori.setText(String.valueOf(iKalori));
    }

    private void initialization(){
        imgCulinary = findViewById(R.id.imgDetMakanan);
        title = findViewById(R.id.tvTitleCulinary);
        description = findViewById(R.id.tvDescCulinary);
        protein = findViewById(R.id.tvProtein);
        lemak = findViewById(R.id.tvLemak);
        karbo = findViewById(R.id.tvKarbo);
        kalori = findViewById(R.id.tvKalori);
    }

    private void getData(){
        Intent intent = getIntent();
        imgUrl = intent.getStringExtra("image");
        iTitle = intent.getStringExtra("title");
        iDescription = intent.getStringExtra("desc");
        iProtein = intent.getIntExtra("protein", 0);
        iLemak = intent.getIntExtra("lemak", 0);
        iKarbo = intent.getIntExtra("karbo", 0);
        iKalori = intent.getIntExtra("kalori", 0);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
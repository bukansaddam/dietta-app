package com.sugadev.dietta.Admin.Culinary.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;
import com.sugadev.dietta.API.BaseUrlConfig;
import com.sugadev.dietta.API.JsonPlaceHolderAPI;
import com.sugadev.dietta.R;
import com.sugadev.dietta.User.Culinary.Model.Culinary;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CulinaryDetailAdmin extends AppCompatActivity {

    ImageView imgCulinary;
    TextView title, description, protein, lemak, karbo, kalori;
    String imgUrl, iTitle, iDescription;
    int iProtein, iLemak, iKarbo, iKalori, iId;

    Button btnEdit, btnDelete;

    Retrofit retrofit;
    JsonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culinary_detail_admin);

        initialization();
        getData();
        getDataIntent();
        setData();
        editDataMakanan();
        deleteMakanan();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(iTitle);
    }

    private void getData() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlConfig.BASE_URL_CULINARY)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
    }

    private void editDataMakanan() {
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dirEdit = new Intent(getApplicationContext(), FormEditMakanan.class);
                dirEdit.putExtra("id", iId);
                dirEdit.putExtra("title", iTitle);
                dirEdit.putExtra("desc", iDescription);
                dirEdit.putExtra("protein", iProtein);
                dirEdit.putExtra("lemak", iLemak);
                dirEdit.putExtra("karrbo", iKarbo);
                dirEdit.putExtra("kalori", iKalori);
                startActivity(dirEdit);
            }
        });
    }

    private void deleteMakanan(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Culinary> call = jsonPlaceHolderAPI.deleteMakanan(iId);

                call.enqueue(new Callback<Culinary>() {
                    @Override
                    public void onResponse(Call<Culinary> call, Response<Culinary> response) {
                        if (!response.isSuccessful()){
                            Toast.makeText(CulinaryDetailAdmin.this, "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                        }

                        Toast.makeText(CulinaryDetailAdmin.this, "Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Culinary> call, Throwable t) {
                        Toast.makeText(CulinaryDetailAdmin.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
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
        btnEdit = findViewById(R.id.btnEditMakanan);
        btnDelete = findViewById(R.id.btnDeleteMakanan);
    }

    private void getDataIntent(){
        Intent intent = getIntent();
        iId = intent.getIntExtra("id",0);
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
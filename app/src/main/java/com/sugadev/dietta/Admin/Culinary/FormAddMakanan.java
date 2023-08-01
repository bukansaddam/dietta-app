package com.sugadev.dietta.Admin.Culinary;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.picasso.BuildConfig;
import com.sugadev.dietta.JsonPlaceHolderAPI;
import com.sugadev.dietta.R;
import com.sugadev.dietta.User.Culinary.Culinary;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormAddMakanan extends AppCompatActivity {

    EditText etUrl, etTitle, etDesc, etLemak, etKarbo, etProtein, etKalori;
    String url, title, desc;
    int lemak, karbo, protein, kalori;

    private static final String api_Culinary = "http://103.174.114.254:8787/";

    Retrofit retrofit;
    JsonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add_makanan);

        declaration();
        getData();

    }

    private void getData() {
        retrofit = new Retrofit.Builder()
                .baseUrl(api_Culinary)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
    }

    private void declaration() {
        etUrl = findViewById(R.id.etImageUrl);
        etTitle = findViewById(R.id.etTitle);
        etDesc = findViewById(R.id.etDeskripsi);
        etLemak = findViewById(R.id.etLemak);
        etKarbo = findViewById(R.id.etKarbohidrat);
        etProtein = findViewById(R.id.etProtein);
        etKalori = findViewById(R.id.etKalori);
    }

    public void tambahmakanan(View view) {
        url = etUrl.getText().toString();
        title = etTitle.getText().toString();
        desc = etDesc.getText().toString();
        lemak = Integer.parseInt(etLemak.getText().toString());
        karbo = Integer.parseInt(etKarbo.getText().toString());
        protein = Integer.parseInt(etProtein.getText().toString());
        kalori = Integer.parseInt(etKalori.getText().toString());

        Culinary culinary = new Culinary(0, title, kalori, lemak, karbo, protein, desc, url);

        Call<Culinary> call = jsonPlaceHolderAPI.addCulinaries(culinary);

        call.enqueue(new Callback<Culinary>() {
            @Override
            public void onResponse(Call<Culinary> call, Response<Culinary> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(FormAddMakanan.this, "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "message : " + response.message());
                    return;
                }

                Toast.makeText(FormAddMakanan.this, "Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Culinary> call, Throwable t) {

            }
        });
    }
}
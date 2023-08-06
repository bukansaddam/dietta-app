package com.sugadev.dietta.Admin.Culinary.View;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sugadev.dietta.API.BaseUrlConfig;
import com.sugadev.dietta.API.JsonPlaceHolderAPI;
import com.sugadev.dietta.R;
import com.sugadev.dietta.User.Culinary.Model.Culinary;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormEditMakanan extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";
    public static final String ID = "idUser";
    private String token, id;
    private int idUser;

    EditText etUrl, etTitle, etDesc, etLemak, etKarbo, etProtein, etKalori;
    String url, title, desc;
    String imgUrl, iTitle, iDescription;
    int iProtein, iLemak, iKarbo, iKalori, iId;
    int lemak, karbo, protein, kalori;

    Retrofit retrofit;
    JsonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_edit_makanan);

        initialization();
        getData();
        getDataIntent();
        setData();

    }

    private void setData() {
        etUrl.setText(imgUrl);
        etTitle.setText(iTitle);
        etDesc.setText(iDescription);
        etLemak.setText(String.valueOf(iLemak));
        etKarbo.setText(String.valueOf(iKarbo));
        etProtein.setText(String.valueOf(iProtein));
        etKalori.setText(String.valueOf(iKalori));
    }

    private void getData() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlConfig.BASE_URL_CULINARY)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
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

    private void initialization() {
        etUrl = findViewById(R.id.etImageUrl);
        etTitle = findViewById(R.id.etTitle);
        etDesc = findViewById(R.id.etDeskripsi);
        etLemak = findViewById(R.id.etLemak);
        etKarbo = findViewById(R.id.etKarbohidrat);
        etProtein = findViewById(R.id.etProtein);
        etKalori = findViewById(R.id.etKalori);
    }

    private void loadToken(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(TOKEN, "");
        id = sharedPreferences.getString(ID, "");
        idUser = Integer.parseInt(id);
        Log.i(TAG, "loadToken: " + token + id);
    }

    public void editMakanan(View view) {
        url = etUrl.getText().toString();
        title = etTitle.getText().toString();
        desc = etDesc.getText().toString();
        lemak = Integer.parseInt(etLemak.getText().toString());
        karbo = Integer.parseInt(etKarbo.getText().toString());
        protein = Integer.parseInt(etProtein.getText().toString());
        kalori = Integer.parseInt(etKalori.getText().toString());

        Culinary culinary = new Culinary(0, title, kalori, lemak, karbo, protein, desc, url);

        loadToken();

        Call<Culinary> call = jsonPlaceHolderAPI.updateMakanan("Bearer " + token, iId,culinary);

        call.enqueue(new Callback<Culinary>() {
            @Override
            public void onResponse(Call<Culinary> call, Response<Culinary> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(FormEditMakanan.this, "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(FormEditMakanan.this, "Berhasil Diupdate", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Culinary> call, Throwable t) {
                Toast.makeText(FormEditMakanan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
package com.sugadev.dietta.User.Culinary.View;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.sugadev.dietta.API.BaseUrlConfig;
import com.sugadev.dietta.API.JsonPlaceHolderAPI;
import com.sugadev.dietta.R;
import com.sugadev.dietta.User.Culinary.Model.Culinary;
import com.sugadev.dietta.User.Culinary.Adapter.CulinaryAdapterAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CulinaryAllView extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView rvCulinary;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";
    public static final String ID = "idUser";
    private String token, id;
    private int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culinary_all);

        initialization();
        getDataCulinary();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }

    private void initialization() {
        rvCulinary = findViewById(R.id.rvCulinaryAll);
        toolbar = findViewById(R.id.toolbar);
    }

    private void loadToken(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(TOKEN, "");
        id = sharedPreferences.getString(ID, "");
        idUser = Integer.parseInt(id);
        Log.i(TAG, "loadToken: " + token + id);
    }

    private void getDataCulinary() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlConfig.BASE_URL_CULINARY)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        loadToken();

        Call<List<Culinary>> call = jsonPlaceHolderAPI.getCulinary("Bearer " + token);

        call.enqueue(new Callback<List<Culinary>>() {
            @Override
            public void onResponse(Call<List<Culinary>> call, Response<List<Culinary>> response) {

                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Culinary> culinaries = response.body();

                CulinaryAdapterAll culinaryAdapterAll = new CulinaryAdapterAll(culinaries);
                rvCulinary.setAdapter(culinaryAdapterAll);
                rvCulinary.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }

            @Override
            public void onFailure(Call<List<Culinary>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
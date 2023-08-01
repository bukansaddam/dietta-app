package com.sugadev.dietta.Admin.Culinary;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sugadev.dietta.Admin.Culinary.FormAddMakanan;
import com.sugadev.dietta.JsonPlaceHolderAPI;
import com.sugadev.dietta.R;
import com.sugadev.dietta.User.Culinary.Culinary;
import com.sugadev.dietta.User.Culinary.CulinaryAdapterHome;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KelMakanan extends AppCompatActivity {

    private static final String api_Culinary = "http://103.174.114.254:8787/";

    RecyclerView rvMakanan;

    int image[] = {
            R.drawable.kare,
            R.drawable.kare,
            R.drawable.kare,
            R.drawable.kare,
            R.drawable.kare
    };

    Retrofit retrofit;
    JsonPlaceHolderAPI jsonPlaceHolderAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kel_makanan);

        rvMakanan = findViewById(R.id.rvMakanan);

        getData();

    }

    private void getData(){
        retrofit = new Retrofit.Builder()
                .baseUrl(api_Culinary)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        Call<List<Culinary>> call = jsonPlaceHolderAPI.getCulinary();

        call.enqueue(new Callback<List<Culinary>>() {
            @Override
            public void onResponse(Call<List<Culinary>> call, Response<List<Culinary>> response) {

                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Culinary> culinaries = response.body();

                CulinaryAdapterHome culinaryAdapterHome = new CulinaryAdapterHome(culinaries);
                rvMakanan.setAdapter(culinaryAdapterHome);
                rvMakanan.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

            }

            @Override
            public void onFailure(Call<List<Culinary>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    public void tambahMakanan(View view) {
        Intent intent = new Intent(getApplicationContext(), FormAddMakanan.class);
        startActivity(intent);
    }
}
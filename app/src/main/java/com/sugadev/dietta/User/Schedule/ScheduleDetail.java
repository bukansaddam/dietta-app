package com.sugadev.dietta.User.Schedule;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sugadev.dietta.JsonPlaceHolderAPI;
import com.sugadev.dietta.R;
import com.sugadev.dietta.User.Schedule.Model.Schedul;
import com.sugadev.dietta.User.Schedule.Model.Schedule;
import com.sugadev.dietta.User.Schedule.Model.ScheduleDetailParent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScheduleDetail extends AppCompatActivity {

    RecyclerView rvDetSche;
    Toolbar toolbar;
    EditText etWaktu;
    Button btnMulai;

    String iTitle;
    int idScheduleParent;

    Retrofit retrofit;
    JsonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_detail);

        declaration();
        getDataIntent();
        getData();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(iTitle);

    }
    private void declaration(){
        rvDetSche = findViewById(R.id.rvVideoSche);
        toolbar = findViewById(R.id.toolbar);
        etWaktu = findViewById(R.id.etMenit);
        btnMulai = findViewById(R.id.btnMulai);
    }

    private void getDataIntent(){
        Intent intent = getIntent();
        idScheduleParent = intent.getIntExtra("id", 0);
        iTitle = intent.getStringExtra("title");
    }

    private void getData(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://103.174.115.40:8989/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        Call<List<Schedul>> call = jsonPlaceHolderAPI.getScheduleChild(idScheduleParent);


        call.enqueue(new Callback<List<Schedul>>() {
            @Override
            public void onResponse(Call<List<Schedul>> call, Response<List<Schedul>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Schedul> scheduleDetailParents = response.body();
                ScheduleDetailAdapter scheduleAdapter = new ScheduleDetailAdapter(scheduleDetailParents);
                rvDetSche.setAdapter(scheduleAdapter);
                rvDetSche.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }

            @Override
            public void onFailure(Call<List<Schedul>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
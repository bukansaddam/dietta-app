package com.sugadev.dietta.User.Schedule.View;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sugadev.dietta.API.BaseUrlConfig;
import com.sugadev.dietta.API.JsonPlaceHolderAPI;
import com.sugadev.dietta.R;
import com.sugadev.dietta.User.Schedule.Adapter.ScheduleChildAdapter;
import com.sugadev.dietta.User.Schedule.Model.Child.ScheduleChildDetail;
import com.sugadev.dietta.User.Video.View.VideoAllView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScheduleChildView extends AppCompatActivity {

    RecyclerView rvDetSche;
    Toolbar toolbar;
    EditText etWaktu;
    Button btnMulai;
    FloatingActionButton fabAddVideo;

    String iTitle;
    int idScheduleParent;

    Retrofit retrofit;
    JsonPlaceHolderAPI jsonPlaceHolderAPI;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";
    public static final String ID = "idUser";
    private String token, id;
    private int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_detail);

        getDataIntent();
        initialization();
        getData();
        dirVideoAll();
        mulaiOlahraga();


    }

    private void mulaiOlahraga() {
        btnMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int jumlahData = rvDetSche.getAdapter().getItemCount();
                int waktuSesi = Integer.parseInt(etWaktu.getText().toString());

                int totalWaktu = (jumlahData * waktuSesi) * 1000;

                new CountDownTimer(totalWaktu,1000){

                    @Override
                    public void onTick(long millisUntilFinished) {
                        Toast.makeText(ScheduleChildView.this, "Tersisa : " + millisUntilFinished, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFinish() {
                        Toast.makeText(ScheduleChildView.this, "Selesai", Toast.LENGTH_SHORT).show();
                    }
                }.start();
            }
        });
    }

    private void initialization(){
        rvDetSche = findViewById(R.id.rvVideoSche);
        etWaktu = findViewById(R.id.etMenit);
        btnMulai = findViewById(R.id.btnMulai);
        fabAddVideo = findViewById(R.id.fabAddScheduleChild);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(iTitle);
    }

    private void dirVideoAll(){
        fabAddVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dirVideo = new Intent(getApplicationContext(), VideoAllView.class);
                dirVideo.putExtra("id", idScheduleParent);
                startActivity(dirVideo);
            }
        });
    }

    private void getDataIntent(){
        Intent intent = getIntent();
        idScheduleParent = intent.getIntExtra("id", 0);
        iTitle = intent.getStringExtra("title");
    }

    private void loadToken(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(TOKEN, "");
        id = sharedPreferences.getString(ID, "");
        idUser = Integer.parseInt(id);
        Log.i(TAG, "loadToken: " + token + id);
    }

    private void getData(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlConfig.BASE_URL_SCHEDULE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loadToken();

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
        Call<List<ScheduleChildDetail>> call = jsonPlaceHolderAPI.getScheduleChild("Bearer "+token,idScheduleParent);
        call.enqueue(new Callback<List<ScheduleChildDetail>>() {
            @Override
            public void onResponse(Call<List<ScheduleChildDetail>> call, Response<List<ScheduleChildDetail>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<ScheduleChildDetail> scheduleDetailParents = response.body();
                ScheduleChildAdapter scheduleAdapter = new ScheduleChildAdapter(scheduleDetailParents);
                rvDetSche.setAdapter(scheduleAdapter);
                rvDetSche.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }

            @Override
            public void onFailure(Call<List<ScheduleChildDetail>> call, Throwable t) {
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
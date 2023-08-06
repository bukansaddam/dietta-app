package com.sugadev.dietta.Admin.Video.View;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sugadev.dietta.Admin.Video.Adapter.VideoAdapterAdmin;
import com.sugadev.dietta.API.BaseUrlConfig;
import com.sugadev.dietta.API.JsonPlaceHolderAPI;
import com.sugadev.dietta.R;
import com.sugadev.dietta.User.Video.Model.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KelVideo extends AppCompatActivity {

    RecyclerView rvVideo;

    Retrofit retrofit;
    JsonPlaceHolderAPI jsonPlaceHolderAPI;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kel_video);

        initialization();
        getData();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Kelola Video");
    }

    private void getData(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlConfig.BASE_URL_VIDEO)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        Call<List<Video>> call = jsonPlaceHolderAPI.getVideo();

        call.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {

                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Video> videos = response.body();

                VideoAdapterAdmin videoAdapterAdmin = new VideoAdapterAdmin(videos);
                rvVideo.setAdapter(videoAdapterAdmin);
                rvVideo.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    private void initialization(){
        rvVideo = findViewById(R.id.rvVideo);
        toolbar = findViewById(R.id.toolbar);
    }

    public void tambahVideo(View view) {
        Intent intent = new Intent(getApplicationContext(), FormAddVideo.class);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
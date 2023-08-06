package com.sugadev.dietta.User.Video.View;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.sugadev.dietta.API.BaseUrlConfig;
import com.sugadev.dietta.API.JsonPlaceHolderAPI;
import com.sugadev.dietta.R;
import com.sugadev.dietta.User.Schedule.Adapter.ScheduleParentAdapter;
import com.sugadev.dietta.User.Schedule.Adapter.ScheduleVideoAdapter;
import com.sugadev.dietta.User.Schedule.Model.Parent.ScheduleParentDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoDetailView extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";
    public static final String ID = "idUser";
    private String token, id;
    private int idUser;

    Retrofit retrofit;
    JsonPlaceHolderAPI jsonPlaceHolderAPI;

    VideoView videoOlahraga;
    TextView tvTitle,tvDesc,tvCategory;
    String iVideo, iTitle, iDesc, iCategory;
    int iIdVideo;
    Button btnAddJadwal;
    BottomSheetDialog bottomSheetDialog;
    RecyclerView rvSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);

        initialization();
        getDataIntent();
        setData();
        addToSchedule();

    }

    private void getDataIntent(){
        Intent intent = getIntent();
        iIdVideo = intent.getIntExtra("id",0);
        iVideo = intent.getStringExtra("video");
        iTitle = intent.getStringExtra("title");
        iDesc = intent.getStringExtra("desc");
        iCategory = intent.getStringExtra("category");
    }

    private void loadToken(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(TOKEN, "");
        id = sharedPreferences.getString(ID, "");
        idUser = Integer.parseInt(id);
        Log.i(TAG, "loadToken: " + token + id);
    }

    private void getDataSchedule(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlConfig.BASE_URL_SCHEDULE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        loadToken();

        Call<List<ScheduleParentDetail>> call = jsonPlaceHolderAPI.getScheduleParent("Bearer "+token,idUser);


        call.enqueue(new Callback<List<ScheduleParentDetail>>() {
            @Override
            public void onResponse(Call<List<ScheduleParentDetail>> call, Response<List<ScheduleParentDetail>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<ScheduleParentDetail> scheduleParentDetails = response.body();
                ScheduleVideoAdapter scheduleVideoAdapter = new ScheduleVideoAdapter(scheduleParentDetails, iIdVideo);
                rvSchedule.setAdapter(scheduleVideoAdapter);
                rvSchedule.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }

            @Override
            public void onFailure(Call<List<ScheduleParentDetail>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    private void setData(){
        tvTitle.setText(iTitle);
        tvDesc.setText(iDesc);
        tvCategory.setText(iCategory);
        Uri uri = Uri.parse(iVideo);

        MediaController mc = new MediaController(this);
        mc.setAnchorView(videoOlahraga);
        mc.setMediaPlayer(videoOlahraga);
//        videoOlahraga.setMediaController(mc);
        videoOlahraga.setVideoURI(uri);
        videoOlahraga.requestFocus();
        videoOlahraga.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoOlahraga.start();
            }
        });
        videoOlahraga.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoOlahraga.start();
            }
        });
    }

    private void initialization(){
        videoOlahraga = findViewById(R.id.videoPlayer);
        tvTitle = findViewById(R.id.tvTitleVideo);
        tvCategory = findViewById(R.id.tvCategory);
        tvDesc = findViewById(R.id.tvDescVideo);
        btnAddJadwal = findViewById(R.id.btnAddToSchedule);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(iTitle);
    }

    private void addToSchedule(){
        btnAddJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheet();
            }
        });
    }

    private void showBottomSheet() {
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_add_schedule);
        rvSchedule = bottomSheetDialog.findViewById(R.id.rvBotSchedule);

        getDataSchedule();

        bottomSheetDialog.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
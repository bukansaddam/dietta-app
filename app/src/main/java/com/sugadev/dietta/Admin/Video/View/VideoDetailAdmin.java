package com.sugadev.dietta.Admin.Video.View;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.sugadev.dietta.API.BaseUrlConfig;
import com.sugadev.dietta.API.JsonPlaceHolderAPI;
import com.sugadev.dietta.R;
import com.sugadev.dietta.User.Video.Model.Video;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoDetailAdmin extends AppCompatActivity {

    VideoView videoOlahraga;
    TextView tvTitle,tvDesc,tvCategory;
    String iVideo, iTitle, iDesc, iCategory, iThumbnail;
    int iId;
    Button btnEdit, btnDelete;


    Retrofit retrofit;
    JsonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail_admin);

        initialization();
        getData();
        getDataIntent();
        setData();
        editData();
        deleteData();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(iTitle);

    }

    private void getData() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlConfig.BASE_URL_VIDEO)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
    }

    private void getDataIntent(){
        Intent intent = getIntent();
        iId = intent.getIntExtra("id",0);
        iVideo = intent.getStringExtra("video");
        iTitle = intent.getStringExtra("title");
        iDesc = intent.getStringExtra("desc");
        iCategory = intent.getStringExtra("category");
        iThumbnail = intent.getStringExtra("thumbnail");
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

    private void editData(){
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dirEdit = new Intent(getApplicationContext(), FormEditVideo.class);
                dirEdit.putExtra("id", iId);
                dirEdit.putExtra("video", iVideo);
                dirEdit.putExtra("title", iTitle);
                dirEdit.putExtra("desc", iDesc);
                dirEdit.putExtra("category", iCategory);
                dirEdit.putExtra("thumbnail", iThumbnail);
                startActivity(dirEdit);
            }
        });
    }

    private void deleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Video> call = jsonPlaceHolderAPI.deleteVideo(iId);

                call.enqueue(new Callback<Video>() {
                    @Override
                    public void onResponse(Call<Video> call, Response<Video> response) {
                        if (!response.isSuccessful()){
                            Toast.makeText(VideoDetailAdmin.this, "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                        }

                        Toast.makeText(VideoDetailAdmin.this, "Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Video> call, Throwable t) {
                        Toast.makeText(VideoDetailAdmin.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void initialization(){
        videoOlahraga = findViewById(R.id.videoPlayer);
        tvTitle = findViewById(R.id.tvTitleVideo);
        tvCategory = findViewById(R.id.tvCategory);
        tvDesc = findViewById(R.id.tvDescVideo);
        btnEdit = findViewById(R.id.btnEditVideo);
        btnDelete = findViewById(R.id.btnDeleteVideo);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
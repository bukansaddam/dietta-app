package com.sugadev.dietta.Admin.Video.View;

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

public class FormEditVideo extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";
    public static final String ID = "idUser";
    private String token, id;
    private int idUser;

    EditText etUrl, etThumbnail, etTitle, etDesc, etCategory;

    String title, description, url, thumbnail, category;

    String iVideo, iTitle, iDesc, iCategory, iThumbnail;
    int iId;
    Toolbar toolbar;

    Retrofit retrofit;
    JsonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_edit_video);

        initialization();
        getData();
        getDataIntent();
        setEditText();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Video");

    }

    private void setEditText(){
        etUrl.setText(iVideo);
        etThumbnail.setText(iThumbnail);
        etTitle.setText(iTitle);
        etDesc.setText(iDesc);
        etCategory.setText(iCategory);
    }

    private void getData() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlConfig.BASE_URL_VIDEO)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

    }

    private void initialization(){
        etUrl = findViewById(R.id.etVideoUrl);
        etThumbnail = findViewById(R.id.etThumbnail);
        etTitle = findViewById(R.id.etTitleVid);
        etDesc = findViewById(R.id.etDeskripsiVid);
        etCategory = findViewById(R.id.etCategory);
        toolbar = findViewById(R.id.toolbar);
    }

    private void getDataIntent(){
        Intent intent = getIntent();
        iId = intent.getIntExtra("id",0);
        iVideo = intent.getStringExtra("video");
        iTitle = intent.getStringExtra("title");
        iDesc = intent.getStringExtra("desc");
        iCategory = intent.getStringExtra("category");
        iThumbnail = intent.getStringExtra("thumbnail");
        Log.i(TAG, "iduser: " + iId);
    }

    private void loadToken(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(TOKEN, "");
        id = sharedPreferences.getString(ID, "");
        idUser = Integer.parseInt(id);
        Log.i(TAG, "loadToken: " + token + id);
    }

    public void editVideo(View view) {
        url = etUrl.getText().toString();
        thumbnail = etThumbnail.getText().toString();
        title = etTitle.getText().toString();
        description = etDesc.getText().toString();
        category = etCategory.getText().toString();

        loadToken();

        Video video = new Video(0, title, description, category, url, thumbnail);

        Call<Video> call = jsonPlaceHolderAPI.updateVideo("Bearer " + token, iId, video);

        call.enqueue(new Callback<Video>() {
            @Override
            public void onResponse(Call<Video> call, Response<Video> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(FormEditVideo.this, "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(FormEditVideo.this, "Berhasil Diubah", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Video> call, Throwable t) {
                Toast.makeText(FormEditVideo.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
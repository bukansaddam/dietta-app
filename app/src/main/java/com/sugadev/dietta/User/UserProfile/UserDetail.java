package com.sugadev.dietta.User.UserProfile;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.sugadev.dietta.JsonPlaceHolderAPI;
import com.sugadev.dietta.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserDetail extends AppCompatActivity {

    TextView Username, Password, Nama, Email, Notelp, BeratBadan, TinggiBadan;

    int idUser;
    String iUsername, iPassword, iNama, iEmail;
    int iNotelp, iBeratBadan, iTinggiBadan;

    Retrofit retrofit;
    JsonPlaceHolderAPI jsonPlaceHolderAPI;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        declaration();
        getData();
        setData();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(iNama);

    }




    private void setData() {
        Username.setText(iUsername);
        Password.setText(iPassword);
        Nama.setText(String.valueOf(iNama));
        Email.setText(String.valueOf(iEmail));
        Notelp.setText(String.valueOf(iNotelp));
        TinggiBadan.setText(String.valueOf(iTinggiBadan));
        BeratBadan.setText(String.valueOf(iBeratBadan));

    }

    private void declaration() {
        Username = findViewById(R.id.tvUsername);
        Password = findViewById(R.id.tvPassword);
        Nama = findViewById(R.id.tvNama);
        Email = findViewById(R.id.tvEmail);
        Notelp = findViewById(R.id.tvNotelp);
        TinggiBadan = findViewById(R.id.tvTinggiBadan);
        BeratBadan = findViewById(R.id.tvBeratBadan);

    }

//    private void getData() {
//        Intent intent = getIntent();
//        iUsername = intent.getStringExtra("username");
//        iPassword = intent.getStringExtra("password");
//        iNama = intent.getStringExtra("name");
//        iEmail = intent.getStringExtra("email");
//        iNotelp = intent.getIntExtra("no_telp", 0);
//        iTinggiBadan = intent.getIntExtra("tinggi_badan", 0);
//        iBeratBadan = intent.getIntExtra("berat_badan", 0);
//    }


    private void getData() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://103.31.39.4:8383/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        Call<UserProfile> call = jsonPlaceHolderAPI.getUserDetail(1);

        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                UserProfile userProfile = response.body();
                if (userProfile != null) {
                    iUsername = userProfile.getUsername();
                    iPassword = userProfile.getPassword();
                    iNama = userProfile.getName();
                    iEmail = userProfile.getEmail();
                    iNotelp = userProfile.getNo_telp();
                    iTinggiBadan = userProfile.getTinggiBadan();
                    iBeratBadan = userProfile.getBeratBadan();

                    // Update UI with the retrieved data
                    setData();
                } else {
                    Toast.makeText(getApplicationContext(), "User profile data is null", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                Toast.makeText(getApplicationContext(), "Failed to fetch user profile data", Toast.LENGTH_SHORT).show();
            }

        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
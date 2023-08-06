package com.sugadev.dietta.User.UserProfile.View;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sugadev.dietta.API.BaseUrlConfig;
import com.sugadev.dietta.API.JsonPlaceHolderAPI;
import com.sugadev.dietta.LoginView;
import com.sugadev.dietta.R;
import com.sugadev.dietta.User.UserProfile.Model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Profile extends AppCompatActivity {

    Button btnLogout, btnEdit;
    TextView name;

    TextView Username, Password, Nama, Email, Notelp, BeratBadan, TinggiBadan;


    String iUsername, iPassword, iNama, iEmail;
    int iNotelp, iBeratBadan, iTinggiBadan;

    Retrofit retrofit;
    JsonPlaceHolderAPI jsonPlaceHolderAPI;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";
    public static final String ID = "idUser";
    private String token,id;
    private int idUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initialization();
        getData();
        setData();
        editData();
        logout();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Profile");

    }

    private void loadToken(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(TOKEN, "");
        id = sharedPreferences.getString(ID, "");
        idUser = Integer.parseInt(id);
    }



    private void initialization() {
        btnLogout = findViewById(R.id.btnLogout);
        Username = findViewById(R.id.tvUsername);
        Nama = findViewById(R.id.tvNama);
        Email = findViewById(R.id.tvEmail);
        Notelp = findViewById(R.id.tvNotelp);
        TinggiBadan = findViewById(R.id.tvTinggiBadan);
        BeratBadan = findViewById(R.id.tvBeratBadan);
        btnEdit = findViewById(R.id.btnEditProfil);
    }

    private void setData() {
        Username.setText(iUsername);
        Nama.setText(String.valueOf(iNama));
        Email.setText(String.valueOf(iEmail));
        Notelp.setText(String.valueOf(iNotelp));
        TinggiBadan.setText(String.valueOf(iTinggiBadan));
        BeratBadan.setText(String.valueOf(iBeratBadan));
    }

    private void logout(){
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent dirLogin = new Intent(getApplicationContext(), LoginView.class);
                startActivity(dirLogin);
            }
        });
    }

    private void getData() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlConfig.BASE_URL_USER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loadToken();
        Log.i(TAG, "tokpro: " + token + id);

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        Call<User> call = jsonPlaceHolderAPI.getUserDetail("Bearer " + token,idUser);

        Log.i(TAG, "profil " + token);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Cod : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = response.body();
                if (user != null) {
                    iUsername = user.getUsername();
                    iPassword = user.getPassword();
                    iNama = user.getName();
                    iEmail = user.getEmail();
                    iNotelp = user.getNo_telp();
                    iTinggiBadan = user.getTinggiBadan();
                    iBeratBadan = user.getBeratBadan();
                    setData();
                } else {
                    Toast.makeText(getApplicationContext(), "User profile data is null", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                Toast.makeText(getApplicationContext(), "Failed to fetch user profile data", Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void editData(){
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FormEditProfile.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
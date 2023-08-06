package com.sugadev.dietta.User.UserProfile.View;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sugadev.dietta.API.BaseUrlConfig;
import com.sugadev.dietta.API.JsonPlaceHolderAPI;
import com.sugadev.dietta.R;
import com.sugadev.dietta.User.UserProfile.Model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormEditProfile extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";
    public static final String ID = "idUser";
    private String token, id;
    private int idUser;

    EditText etName, etEmail, etUsername, etTinggi, etBerat, etPassword, etNotelp;
    Button btnSubmit;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_edit_profile);

        initialization();
        getData();
        updateData();

    }

    private void loadToken(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(TOKEN, "");
        id = sharedPreferences.getString(ID, "");
        idUser = Integer.parseInt(id);
        Log.i(TAG, "loadToken: " + token + id);
    }

    private void getData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlConfig.BASE_URL_USER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loadToken();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        Call<User> call = jsonPlaceHolderAPI.getUserDetail("Bearer " + token,idUser);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = response.body();
                if (user != null) {
                    etName.setText(user.getName());
                    etEmail.setText(user.getEmail());
                    etUsername.setText(user.getUsername());
                    etTinggi.setText(Integer.toString(user.getTinggiBadan()));
                    etBerat.setText(Integer.toString(user.getBeratBadan()));
                    etPassword.setText(user.getPassword());
                    etNotelp.setText(Integer.toString(user.getNo_telp()));
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

    private void updateData(){
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = etName.getText().toString();
                String email = etEmail.getText().toString();
                String username = etUsername.getText().toString();
                int tinggi = Integer.parseInt(etTinggi.getText().toString());
                int berat = Integer.parseInt(etBerat.getText().toString());
                String password = etPassword.getText().toString();
                int notelp = Integer.parseInt(etNotelp.getText().toString());

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BaseUrlConfig.BASE_URL_USER)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                loadToken();

                JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

                User user = new User(0, username, password, nama, email, notelp, berat, tinggi);

                Call<User> call = jsonPlaceHolderAPI.updateUser("Bearer " + token,idUser, user);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        User user = response.body();
                        if (user != null) {
                            etName.setText(user.getName());
                            etEmail.setText(user.getEmail());
                            etUsername.setText(user.getUsername());
                            etTinggi.setText(user.getTinggiBadan());
                            etBerat.setText(user.getBeratBadan());
                            etPassword.setText(user.getPassword());
                            etNotelp.setText(user.getNo_telp());
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
        });

    }

    private void initialization(){
        etName = findViewById(R.id.etFormName);
        etEmail = findViewById(R.id.etFormEmail);
        etUsername = findViewById(R.id.etFormUsername);
        etTinggi = findViewById(R.id.etFormTinggiBadan);
        etBerat = findViewById(R.id.etFormBeratBadan);
        etPassword = findViewById(R.id.etFormPassword);
        etNotelp = findViewById(R.id.etFormNoTelp);
        btnSubmit = findViewById(R.id.btnFormSubmit);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Profil");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
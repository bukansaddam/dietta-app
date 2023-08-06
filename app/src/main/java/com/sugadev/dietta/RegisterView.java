package com.sugadev.dietta;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sugadev.dietta.API.BaseUrlConfig;
import com.sugadev.dietta.API.JsonPlaceHolderAPI;
import com.sugadev.dietta.User.UserProfile.Model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterView extends AppCompatActivity {

    TextView tvLogin;
    Button btnRegister;
    EditText etEmail, etPassword, etName, etUsername, etNotelp, etTinggi, etBerat;
    String email, password, name, username;
    int notelp, tinggibadan, beratbadan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initialization();
        submitData();

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dirLogin = new Intent(getApplicationContext(), LoginView.class);
                startActivity(dirLogin);
                finish();
            }
        });


    }
    private void submitData(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
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

                JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

                User user = new User(0, username, password, nama, email, notelp, berat, tinggi);

                Call<User> call = jsonPlaceHolderAPI.addUser(user);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(RegisterView.this, "Akun Berhasil Dibuat", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
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

    private void getEditText() {
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        name = etName.getText().toString();
        username = etUsername.getText().toString();
        tinggibadan = Integer.parseInt(etTinggi.getText().toString());
        beratbadan = Integer.parseInt(etBerat.getText().toString());
        notelp = Integer.parseInt(etNotelp.getText().toString());
    }

    private void initialization() {
        tvLogin = findViewById(R.id.tvDirLogin);
        btnRegister = findViewById(R.id.btnRegister);
        etEmail = findViewById(R.id.etRegEmail);
        etPassword = findViewById(R.id.etRegPassword);
        etName = findViewById(R.id.etRegName);
        etUsername = findViewById(R.id.etRegUsername);
        etTinggi = findViewById(R.id.etRegTinggiBadan);
        etBerat = findViewById(R.id.etRegBeratBadan);
        etNotelp = findViewById(R.id.etRegNoTelp);
    }

}
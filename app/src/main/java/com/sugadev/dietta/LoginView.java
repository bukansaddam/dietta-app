package com.sugadev.dietta;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sugadev.dietta.API.BaseUrlConfig;
import com.sugadev.dietta.API.JsonPlaceHolderAPI;
import com.sugadev.dietta.API.JwtUtils;
import com.sugadev.dietta.API.TokenApi;
import com.sugadev.dietta.Admin.Dashboard;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginView extends AppCompatActivity {

    TextView tvRegister;
    Button btnLogin;
    EditText etEmail, etPassword;
    String iemail, ipassword, iname, iusername;
    int notelp, tinggibadan, beratbadan, idUser;

    String email, password, token, roles, id;
    Context context;


    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";
    public static final String ID = "idUser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = getApplicationContext();

        tvRegister = findViewById(R.id.tvDirRegister);
        btnLogin = findViewById(R.id.btnLogin);
        etEmail = findViewById(R.id.etLogEmail);
        etPassword = findViewById(R.id.etLogPassword);
//        getDataIntent();

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dirRegister = new Intent(getApplicationContext(), RegisterView.class);
                startActivity(dirRegister);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    Login login = new Login(email, password);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BaseUrlConfig.BASE_URL_USER)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
                    Call<TokenApi> call = jsonPlaceHolderAPI.login(login);

                    call.enqueue(new Callback<TokenApi>() {
                        @Override
                        public void onResponse(Call<TokenApi> call, Response<TokenApi> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(LoginView.this, "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                                token = response.body().getAccessToken();

                                String decodedRoles = JwtUtils.jwtGetRoles(token);
                                String decodedSub = JwtUtils.jwtGetId(token);

                                int startIndex = decodedRoles.indexOf("[");
                                int endIndex = decodedRoles.indexOf("]");

                                if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
                                    roles = decodedRoles.substring(startIndex + 1, endIndex);
                                    Log.i(TAG, "roles " + roles);
                                } else {
                                    System.out.println("Tidak ada potongan yang ditemukan di antara kurung siku.");
                                }

                                if (roles.equals("ROLE_ADMIN")) {
                                    Intent dirDashboard = new Intent(getApplicationContext(), Dashboard.class);
                                    startActivity(dirDashboard);
                                } else {
                                    Intent dirHome = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(dirHome);
                                }

                                SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();

                                editor.putString(TOKEN, token);
                                editor.putString(ID, decodedSub);
                                editor.apply();

                            } else {
                                Toast.makeText(LoginView.this, "login gagal", Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onFailure(Call<TokenApi> call, Throwable t) {
                            Log.e(TAG, "onFailure: ", t);
                        }
                    });

                } else {
                    Toast.makeText(LoginView.this, "Masukkan data dengan lengkap", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getDataIntent() {
        Intent intent = getIntent();
        iname = intent.getStringExtra("nama");
        iusername = intent.getStringExtra("username");
        iemail = intent.getStringExtra("email");
        password = intent.getStringExtra("password");
        tinggibadan = intent.getIntExtra("tinggi", 0);
        beratbadan = intent.getIntExtra("berat", 0);
        notelp = intent.getIntExtra("notelp", 0);
    }

}
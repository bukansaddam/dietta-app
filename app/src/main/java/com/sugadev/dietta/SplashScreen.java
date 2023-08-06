package com.sugadev.dietta;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashScreen extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";
    public static final String ID = "idUser";
    private String token, id;
    private int idUser;


    int time = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Log.i(TAG, "loadToken: " + token + id);
        loadToken();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!token.equals("") && !id.equals("")) {
                    Intent dirHome = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(dirHome);
                }else {
                    Intent dirLogin = new Intent(getApplicationContext(), LoginView.class);
                    startActivity(dirLogin);
                }
            }
        }, time);
    }

    private void loadToken(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(TOKEN, "");
        id = sharedPreferences.getString(ID, "");
        Log.i(TAG, "loadToken: " + token + id);
    }
}
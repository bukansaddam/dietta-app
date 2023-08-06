package com.sugadev.dietta;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.sugadev.dietta.API.BaseUrlConfig;
import com.sugadev.dietta.API.JsonPlaceHolderAPI;
import com.sugadev.dietta.User.Diary.View.DiaryView;
import com.sugadev.dietta.User.Culinary.View.CulinaryAllView;
import com.sugadev.dietta.User.History.View.HistoryView;
import com.sugadev.dietta.User.Homepage;
import com.sugadev.dietta.User.UserProfile.Model.User;
import com.sugadev.dietta.User.UserProfile.View.Profile;
import com.sugadev.dietta.User.Schedule.View.ScheduleParentView;
import com.sugadev.dietta.User.Track;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {


    BottomNavigationView bottomNavigationView;
    FloatingActionButton addDiary;
    DiaryView diaryViewFragment = new DiaryView();
    Track trackFragment = new Track();
    HistoryView historyViewFragment = new HistoryView();
    Profile profileFragment = new Profile();
    Homepage homepageFragment = new Homepage();
    ScheduleParentView scheduleParentViewFragment = new ScheduleParentView();

    TextView name, title;
    ImageView btnProfil;

    String iemail, password, iname, username;
    int notelp, tinggibadan, beratbadan;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";
    public static final String ID = "idUser";
    private String token,id;
    private int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, homepageFragment).commit();
        }

        dataUser();
        initialization();
//        firebaseGetUser();
        btnAddDiary();
        btnDirProfil();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setItemIconTintList(null);
    }

    private void btnDirProfil() {
        btnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dirProfil = new Intent(getApplicationContext(), Profile.class);
                startActivity(dirProfil);
            }
        });
    }

    private void initialization(){
        name = findViewById(R.id.phNama);
        btnProfil = findViewById(R.id.btnProfil);
        title = findViewById(R.id.tvTitle);
        addDiary = findViewById(R.id.fabAddDiary);
    }

    private void btnAddDiary(){
        addDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dirCulinaryAll = new Intent(getApplicationContext(), CulinaryAllView.class);
                startActivity(dirCulinaryAll);
            }
        });
    }

    private void dataUser(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlConfig.BASE_URL_USER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(TOKEN, "");
        id = sharedPreferences.getString(ID, "");
        idUser = Integer.parseInt(id);
        Log.i(TAG, "loadToken: " + token + id);

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
                    name.setText(user.getName());
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.catatan) {
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, diaryViewFragment).commit();
            title.setText("Catatan");
            addDiary.setVisibility(View.VISIBLE);
            return true;
        } else if (item.getItemId() == R.id.schedule) {
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, scheduleParentViewFragment).commit();
            title.setText("Schedule");
            addDiary.setVisibility(View.GONE);
            return true;
        } else if (item.getItemId() == R.id.tracker) {
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, trackFragment).commit();
            title.setText("Track");
            addDiary.setVisibility(View.GONE);
            return true;
        } else if (item.getItemId() == R.id.history) {
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, historyViewFragment).commit();
            title.setText("History");
            addDiary.setVisibility(View.GONE);
            return true;
        } else if (item.getItemId() == R.id.home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe,homepageFragment).commit();
            title.setText("Dietta");
            addDiary.setVisibility(View.GONE);
            return true;
        }
        return false;
    }
}
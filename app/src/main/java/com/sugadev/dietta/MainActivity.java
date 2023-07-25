package com.sugadev.dietta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.sugadev.dietta.User.History;
import com.sugadev.dietta.User.Homepage;
import com.sugadev.dietta.User.Profile;
import com.sugadev.dietta.User.Track;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {


    BottomNavigationView bottomNavigationView;
    Profile catatanFragment = new Profile();
    Track trackFragment = new Track();
    History historyFragment = new History();
    Profile profileFragment = new Profile();
    Homepage homepageFragment = new Homepage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.catatan) {
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, catatanFragment).commit();
            return true;
        } else if (item.getItemId() == R.id.profile) {
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, profileFragment).commit();
            return true;
        } else if (item.getItemId() == R.id.tracker) {
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, trackFragment).commit();
            return true;
        } else if (item.getItemId() == R.id.history) {
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, historyFragment).commit();
            return true;
        } else if (item.getItemId() == R.id.home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe,homepageFragment).commit();
            return true;
        }
        return false;
    }
}
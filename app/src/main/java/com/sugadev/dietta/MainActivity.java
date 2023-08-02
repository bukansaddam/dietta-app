package com.sugadev.dietta;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sugadev.dietta.User.Diary.Catatan;
import com.sugadev.dietta.User.Culinary.CulinaryAll;
import com.sugadev.dietta.User.History;
import com.sugadev.dietta.User.Homepage;
import com.sugadev.dietta.User.Profile;
import com.sugadev.dietta.User.Schedule.ScheduleView;
import com.sugadev.dietta.User.Track;
import com.sugadev.dietta.User.User;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {


    BottomNavigationView bottomNavigationView;
    FloatingActionButton addDiary;
    Catatan catatanFragment = new Catatan();
    Track trackFragment = new Track();
    History historyFragment = new History();
    Profile profileFragment = new Profile();
    Homepage homepageFragment = new Homepage();
    ScheduleView scheduleViewFragment = new ScheduleView();

    TextView name, title;
    ImageView btnProfil;
    FirebaseAuth mAuth;
    DatabaseReference mReference;
    FirebaseUser mUser;
    FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, homepageFragment).commit();
        }

        declaration();
        firebaseGetUser();
        btnAddDiary();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setItemIconTintList(null);
    }

    private void declaration(){
        name = findViewById(R.id.phNama);
        btnProfil = findViewById(R.id.btnProfil);
        title = findViewById(R.id.tvTitle);
        addDiary = findViewById(R.id.fabAddDiary);
    }

    private void btnAddDiary(){
        addDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dirCulinaryAll = new Intent(getApplicationContext(), CulinaryAll.class);
                startActivity(dirCulinaryAll);
            }
        });
    }

    private void firebaseGetUser(){
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference().child("users").child(mUser.getUid());

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                name.setText(user.getName());
                Log.i(TAG, "name: " + user.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, "the read failed: " + error.getCode());
            }
        });

        btnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mAuth.signOut();
//                Intent dirLogin = new Intent(getApplicationContext(), Login.class);
//                startActivity(dirLogin);
                Intent dirProfil = new Intent(getApplicationContext(), Profile.class);
                startActivity(dirProfil);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.catatan) {
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, catatanFragment).commit();
            title.setText("Catatan");
            addDiary.setVisibility(View.VISIBLE);
            return true;
        } else if (item.getItemId() == R.id.schedule) {
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, scheduleViewFragment).commit();
            title.setText("Schedule");
            addDiary.setVisibility(View.GONE);
            return true;
        } else if (item.getItemId() == R.id.tracker) {
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, trackFragment).commit();
            title.setText("Track");
            addDiary.setVisibility(View.GONE);
            return true;
        } else if (item.getItemId() == R.id.history) {
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, historyFragment).commit();
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
package com.sugadev.dietta.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.sugadev.dietta.Login;
import com.sugadev.dietta.R;

public class Dashboard extends AppCompatActivity {

    TextView btnLogout;
    Button btnVideo, btnMakanan;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mAuth = FirebaseAuth.getInstance();
        btnLogout = findViewById(R.id.btndLogout);
        btnVideo = findViewById(R.id.btnKelVideo);
        btnMakanan = findViewById(R.id.btnKelMakanan);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent dirLogin = new Intent(getApplicationContext(), Login.class);
                startActivity(dirLogin);
                finish();
            }
        });
    }

    public void kelolaMakanan(View view) {
    }

    public void kelolaVideo(View view) {
        Intent intent = new Intent(getApplicationContext(), KelVideo.class);
        startActivity(intent);
    }
}
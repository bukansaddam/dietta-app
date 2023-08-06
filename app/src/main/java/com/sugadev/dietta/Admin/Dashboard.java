package com.sugadev.dietta.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sugadev.dietta.Admin.Culinary.View.KelMakanan;
import com.sugadev.dietta.Admin.Video.View.KelVideo;
import com.sugadev.dietta.LoginView;
import com.sugadev.dietta.R;

public class Dashboard extends AppCompatActivity {

    TextView btnLogout;
    Button btnVideo, btnMakanan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnLogout = findViewById(R.id.btndLogout);
        btnVideo = findViewById(R.id.btnKelVideo);
        btnMakanan = findViewById(R.id.btnKelMakanan);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dirLogin = new Intent(getApplicationContext(), LoginView.class);
                startActivity(dirLogin);
                finish();
            }
        });
    }

    public void kelolaMakanan(View view) {
        Intent intent = new Intent(getApplicationContext(), KelMakanan.class);
        startActivity(intent);
    }

    public void kelolaVideo(View view) {
        Intent intent = new Intent(getApplicationContext(), KelVideo.class);
        startActivity(intent);
    }
}
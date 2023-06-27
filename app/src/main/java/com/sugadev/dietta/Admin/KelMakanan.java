package com.sugadev.dietta.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sugadev.dietta.R;

public class KelMakanan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kel_makanan);
    }

    public void tambahMakanan(View view) {
        Intent intent = new Intent(getApplicationContext(), FormAddMakanan.class);
        startActivity(intent);
    }
}
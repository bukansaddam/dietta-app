package com.sugadev.dietta.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sugadev.dietta.R;

public class FormAddMakanan extends AppCompatActivity {

    EditText etUrl, etTitle, etDesc;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add_makanan);

        etUrl = findViewById(R.id.etImageUrl);
        etTitle = findViewById(R.id.etTitle);
        etDesc = findViewById(R.id.etDeskripsi);
    }

    public void tambahmakanan(View view) {
        String url = etUrl.getText().toString();
        String title = etTitle.getText().toString();
        String desc = etDesc.getText().toString();

        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(title) || TextUtils.isEmpty(desc)){
            Toast.makeText(this, "Masukkan data dengan lengkap", Toast.LENGTH_SHORT).show();
        }else {
            Makanan makanan = new Makanan(url, title, desc);

            reference = FirebaseDatabase.getInstance().getReference("makanan");
            reference.child(makanan.getTitle()).setValue(makanan).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Intent intent = new Intent(getApplicationContext(), KelVideo.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
}
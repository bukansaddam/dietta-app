package com.sugadev.dietta.User;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sugadev.dietta.Login;
import com.sugadev.dietta.R;

public class Profile extends AppCompatActivity {

    Button btnLogout;
    TextView name;
    FirebaseAuth mAuth;
    DatabaseReference mReference;
    FirebaseUser mUser;
    FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        declaration();
        firebaseGetUser();
    }

    private void declaration() {
        btnLogout = findViewById(R.id.btnLogout);
        name = findViewById(R.id.tvNamaProfil);
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

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent dirLogin = new Intent(getApplicationContext(), Login.class);
                startActivity(dirLogin);
            }
        });
    }
}
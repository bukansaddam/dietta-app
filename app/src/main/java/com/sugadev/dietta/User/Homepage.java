package com.sugadev.dietta.User;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sugadev.dietta.Admin.MakananAdapterHome;
import com.sugadev.dietta.Login;
import com.sugadev.dietta.R;
import com.sugadev.dietta.User.Video.Cardio;
import com.sugadev.dietta.User.Video.Gym;
import com.sugadev.dietta.User.Video.Pilates;
import com.sugadev.dietta.User.Video.Yoga;


public class Homepage extends Fragment {

    int image[] = {
            R.drawable.kare,
            R.drawable.kare,
            R.drawable.kare,
            R.drawable.kare,
            R.drawable.kare
    };
    TextView name;
    ImageView btnLogout;
    FirebaseAuth mAuth;
    DatabaseReference mReference;
    FirebaseUser mUser;
    FirebaseDatabase mDatabase;
    RecyclerView rvMakanan;

    LinearLayout gym, yoga, cardio, pilates;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_homepage, container, false);

        name = view.findViewById(R.id.phNama);
        btnLogout = view.findViewById(R.id.btnLogout);
        rvMakanan = view.findViewById(R.id.rvMakananHome);
        yoga = view.findViewById(R.id.btnYoga);
        gym = view.findViewById(R.id.btnGym);
        pilates = view.findViewById(R.id.btnPilates);
        cardio = view.findViewById(R.id.btnCardio);

        String[] jdlMakanan = getResources().getStringArray(R.array.judul_makanan);
        String[] descMakanan = getResources().getStringArray(R.array.deskripsi_makanan);

        dataMakanan(jdlMakanan,descMakanan, image, getContext());

        btnVideo();

        firebaseGetUser();

        return view;
    }

    private void btnVideo(){
        yoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dirYoga = new Intent(getContext(), Yoga.class);
                startActivity(dirYoga);
            }
        });

        cardio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dirCardio = new Intent(getContext(), Cardio.class);
                startActivity(dirCardio);
            }
        });

        gym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dirGym = new Intent(getContext(), Gym.class);
                startActivity(dirGym);
            }
        });

        pilates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dirPilates = new Intent(getContext(), Pilates.class);
                startActivity(dirPilates);
            }
        });
    }

    private void dataMakanan(String[] judul, String[] deskripsi, int[] image, Context context){
        MakananAdapterHome makananAdapterHome = new MakananAdapterHome(judul, deskripsi, image, context);
        rvMakanan.setAdapter(makananAdapterHome);
        rvMakanan.setLayoutManager(new GridLayoutManager(getContext(), 2));
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
                Intent dirLogin = new Intent(getContext(), Login.class);
                startActivity(dirLogin);
            }
        });
    }

}
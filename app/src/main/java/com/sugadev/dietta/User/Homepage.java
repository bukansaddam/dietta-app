package com.sugadev.dietta.User;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sugadev.dietta.User.Culinary.CulinaryAdapterHome;
import com.sugadev.dietta.JsonPlaceHolderAPI;
import com.sugadev.dietta.Login;
import com.sugadev.dietta.User.Culinary.Culinary;
import com.sugadev.dietta.R;
import com.sugadev.dietta.User.Video.Cardio;
import com.sugadev.dietta.User.Video.Gym;
import com.sugadev.dietta.User.Video.Pilates;
import com.sugadev.dietta.User.Video.Yoga;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Homepage extends Fragment {

    private static final String api_Culinary = "http://103.174.114.254:8787/";

    RecyclerView rvMakanan;

    LinearLayout gym, yoga, cardio, pilates;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_homepage, container, false);

        rvMakanan = view.findViewById(R.id.rvMakananHome);
        yoga = view.findViewById(R.id.btnYoga);
        gym = view.findViewById(R.id.btnGym);
        pilates = view.findViewById(R.id.btnPilates);
        cardio = view.findViewById(R.id.btnCardio);

        dataMakanan();

        btnVideo();

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

    private void dataMakanan(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api_Culinary)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        Call<List<Culinary>> call = jsonPlaceHolderAPI.getCulinary();

        call.enqueue(new Callback<List<Culinary>>() {
            @Override
            public void onResponse(Call<List<Culinary>> call, Response<List<Culinary>> response) {

                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Culinary> culinaries = response.body();

                CulinaryAdapterHome culinaryAdapterHome = new CulinaryAdapterHome(culinaries);
                rvMakanan.setAdapter(culinaryAdapterHome);
                rvMakanan.setLayoutManager(new GridLayoutManager(getContext(), 2));

            }

            @Override
            public void onFailure(Call<List<Culinary>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });

    }

}
package com.sugadev.dietta.User;

import static android.content.ContentValues.TAG;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sugadev.dietta.API.BaseUrlConfig;
import com.sugadev.dietta.User.Culinary.Adapter.CulinaryAdapterHome;
import com.sugadev.dietta.API.JsonPlaceHolderAPI;
import com.sugadev.dietta.User.Culinary.Model.Culinary;
import com.sugadev.dietta.R;
import com.sugadev.dietta.User.UserProfile.Model.User;
import com.sugadev.dietta.User.Video.View.Cardio;
import com.sugadev.dietta.User.Video.View.Gym;
import com.sugadev.dietta.User.Video.View.Pilates;
import com.sugadev.dietta.User.Video.View.Yoga;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Homepage extends Fragment {


    RecyclerView rvMakanan;
    TextView berat, tinggi;
    LinearLayout gym, yoga, cardio, pilates;
    String iNama;
    int iTinggiBadan, iBeratBadan;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";
    public static final String ID = "idUser";
    private String token, id;
    private int idUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_homepage, container, false);

        rvMakanan = view.findViewById(R.id.rvMakananHome);
        yoga = view.findViewById(R.id.btnYoga);
        gym = view.findViewById(R.id.btnGym);
        pilates = view.findViewById(R.id.btnPilates);
        cardio = view.findViewById(R.id.btnCardio);
        berat = view.findViewById(R.id.phBerat);
        tinggi = view.findViewById(R.id.phTinggi);

        loadToken();
        dataMakanan();
        dataUser();

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

    private void loadToken(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(TOKEN, "");
        id = sharedPreferences.getString(ID, "");
        idUser = Integer.parseInt(id);
        Log.i(TAG, "loadToken: " + token + id);
    }

    private void dataUser(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlConfig.BASE_URL_USER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loadToken();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        Call<User> call = jsonPlaceHolderAPI.getUserDetail("Bearer " + token,idUser);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = response.body();
                if (user != null) {
                    iNama = user.getName();
                    iTinggiBadan = user.getTinggiBadan();
                    iBeratBadan = user.getBeratBadan();
                    setData();
                } else {
                    Toast.makeText(getContext(), "User profile data is null", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                Toast.makeText(getContext(), "Failed to fetch user profile data", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void setData() {

        berat.setText(String.valueOf(iBeratBadan));
        tinggi.setText(String.valueOf(iTinggiBadan));
    }

    private void dataMakanan(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlConfig.BASE_URL_CULINARY)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loadToken();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);


        Call<List<Culinary>> call = jsonPlaceHolderAPI.getCulinary("Bearer " + token);

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
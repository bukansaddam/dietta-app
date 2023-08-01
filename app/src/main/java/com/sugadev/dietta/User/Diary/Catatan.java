package com.sugadev.dietta.User.Diary;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sugadev.dietta.JsonPlaceHolderAPI;
import com.sugadev.dietta.R;
import com.sugadev.dietta.User.Culinary.Culinary;
import com.sugadev.dietta.User.Culinary.CulinaryAdapterAll;
import com.sugadev.dietta.User.Culinary.CulinaryAdapterHome;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Catatan extends Fragment {

    RecyclerView rvDiary;
    TextView empty, totalkalori;

    int getIdCulinary, getIdCulinary2;
    List<Culinary> culinaries;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_catatan, container, false);

        rvDiary = view.findViewById(R.id.rvDiary);
        empty = view.findViewById(R.id.tvEmpty);
        totalkalori = view.findViewById(R.id.tvTotalKalori);

//        getData();
        getDataDetail();

        return view;
    }

    private void getDataDetail() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://103.174.114.254:8585/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        Call<List<Diary2>> call = jsonPlaceHolderAPI.getDetailDiary();

        call.enqueue(new Callback<List<Diary2>>() {
            @Override
            public void onResponse(Call<List<Diary2>> call, Response<List<Diary2>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Diary2> diaries = response.body();
                for (Diary2 diary2 : diaries){
                    Log.i(TAG, "idcul: " + diary2.getIdCulinary());
                    Log.i(TAG, "name: " + diary2.getFoodname());
                }
            }

            @Override
            public void onFailure(Call<List<Diary2>> call, Throwable t) {

            }
        });
    }


    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://103.174.114.254:8585/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("http://103.174.114.254:8787/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
        JsonPlaceHolderAPI jsonPlaceHolderAPI2 = retrofit2.create(JsonPlaceHolderAPI.class);

        Call<List<Diary>> call = jsonPlaceHolderAPI.getDiary();
        Call<List<Culinary>> callc = jsonPlaceHolderAPI2.getCulinary();

        callc.enqueue(new Callback<List<Culinary>>() {
            @Override
            public void onResponse(Call<List<Culinary>> call, Response<List<Culinary>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                culinaries = response.body();
                for (Culinary culinary : culinaries){
                    getIdCulinary2 = culinary.getIdCulinary();
                    Log.i(TAG, "ID : " + getIdCulinary2);
                }
            }

            @Override
            public void onFailure(Call<List<Culinary>> call, Throwable t) {

            }
        });

        call.enqueue(new Callback<List<Diary>>() {
            @Override
            public void onResponse(Call<List<Diary>> call, Response<List<Diary>> response) {

                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Diary> diaries = response.body();


            }

            @Override
            public void onFailure(Call<List<Diary>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

}
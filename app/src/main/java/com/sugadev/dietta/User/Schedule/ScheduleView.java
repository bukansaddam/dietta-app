package com.sugadev.dietta.User.Schedule;

import static android.content.ContentValues.TAG;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sugadev.dietta.JsonPlaceHolderAPI;
import com.sugadev.dietta.R;
import com.sugadev.dietta.User.Schedule.Model.ScheduleDetailParent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScheduleView extends Fragment {

    ImageView btnAdd;
    RecyclerView rvSchedule;
    TextView empty;
    View view;

    Retrofit retrofit;
    JsonPlaceHolderAPI jsonPlaceHolderAPI;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.activity_schedule, container, false);

        declaration();
        getData();

        return view;
    }

    private void declaration(){
        btnAdd = view.findViewById(R.id.btnAddSchedule);
        rvSchedule = view.findViewById(R.id.rvSchedule);
        empty = view.findViewById(R.id.tvEmptySche);
    }

    private void getData(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://103.174.115.40:8989/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        Call<List<ScheduleDetailParent>> call = jsonPlaceHolderAPI.getScheduleParent(1);


        call.enqueue(new Callback<List<ScheduleDetailParent>>() {
            @Override
            public void onResponse(Call<List<ScheduleDetailParent>> call, Response<List<ScheduleDetailParent>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<ScheduleDetailParent> scheduleDetailParents = response.body();
                ScheduleAdapter scheduleAdapter = new ScheduleAdapter(scheduleDetailParents);
                empty.setVisibility(View.GONE);
                rvSchedule.setAdapter(scheduleAdapter);
                rvSchedule.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<List<ScheduleDetailParent>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                empty.setVisibility(View.VISIBLE);
            }
        });
    }
}
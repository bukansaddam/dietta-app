package com.sugadev.dietta.User.History.View;

import static android.content.ContentValues.TAG;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sugadev.dietta.API.BaseUrlConfig;
import com.sugadev.dietta.API.JsonPlaceHolderAPI;
import com.sugadev.dietta.R;
import com.sugadev.dietta.User.History.Adapter.HistoryAdapter;
import com.sugadev.dietta.User.History.Model.HistoryDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryView extends Fragment {

    View view;
    RecyclerView rvHistory;
    TextView empty;
    Retrofit retrofit;
    JsonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.activity_history, container, false);

        initialization();
        getData();


        return view;
    }

    private void initialization(){
        rvHistory = view.findViewById(R.id.rvHistory);
        empty = view.findViewById(R.id.tvEmpty);
    }

    private void getData(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlConfig.BASE_URL_HISTORY)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        Call<List<HistoryDetail>> call = jsonPlaceHolderAPI.getHistory(2);


        call.enqueue(new Callback<List<HistoryDetail>>() {
            @Override
            public void onResponse(Call<List<HistoryDetail>> call, Response<List<HistoryDetail>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<HistoryDetail> historyDetails = response.body();
                Log.i(TAG, historyDetails.toString());
                HistoryAdapter historyAdapter = new HistoryAdapter(historyDetails);
                rvHistory.setAdapter(historyAdapter);
                rvHistory.setLayoutManager(new LinearLayoutManager(getContext()));
                empty.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<HistoryDetail>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}
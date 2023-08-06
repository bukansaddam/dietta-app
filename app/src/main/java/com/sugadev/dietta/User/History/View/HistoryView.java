package com.sugadev.dietta.User.History.View;

import static android.content.ContentValues.TAG;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.sugadev.dietta.User.History.Adapter.HistoryParentAdapter;
import com.sugadev.dietta.User.History.Model.History;
import com.sugadev.dietta.User.History.Model.HistoryParent;
import com.sugadev.dietta.User.History.Model.HistoryParentDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryView extends Fragment {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";
    public static final String ID = "idUser";
    private String token, id;
    private int idUser;

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
        getDataHistory();


        return view;
    }

    private void initialization(){
        rvHistory = view.findViewById(R.id.rvHistory);
        empty = view.findViewById(R.id.tvEmpty);
    }

    private void loadToken(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(TOKEN, "");
        id = sharedPreferences.getString(ID, "");
        idUser = Integer.parseInt(id);
        Log.i(TAG, "loadToken: " + token + id);
    }

    private void getDataHistory(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlConfig.BASE_URL_HISTORY)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        loadToken();

        Call<List<History>> call = jsonPlaceHolderAPI.getHistory("Bearer "+token,idUser);


        call.enqueue(new Callback<List<History>>() {
            @Override
            public void onResponse(Call<List<History>> call, Response<List<History>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<History> histories = response.body();
                HistoryParentAdapter historyParentAdapter = new HistoryParentAdapter(histories);
                empty.setVisibility(View.GONE);
                rvHistory.setAdapter(historyParentAdapter);
                rvHistory.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<List<History>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                empty.setVisibility(View.VISIBLE);
            }
        });
    }
}
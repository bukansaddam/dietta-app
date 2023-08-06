package com.sugadev.dietta.User.Diary.View;

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
import com.sugadev.dietta.User.Diary.Adapter.DiaryAdapter;
import com.sugadev.dietta.User.Diary.Model.DiaryDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DiaryView extends Fragment {

    RecyclerView rvDiary;
    TextView empty, totalkalori;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";
    public static final String ID = "idUser";
    private String token, id;
    private int idUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_catatan, container, false);

        rvDiary = view.findViewById(R.id.rvDiary);
        empty = view.findViewById(R.id.tvEmpty);
        totalkalori = view.findViewById(R.id.tvTotalKalori);

        getData();

        return view;
    }

    private void loadToken(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(TOKEN, "");
        id = sharedPreferences.getString(ID, "");
        idUser = Integer.parseInt(id);
        Log.i(TAG, "loadToken: " + token + id);
    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlConfig.BASE_URL_DIARY)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        loadToken();

        Call<List<DiaryDetail>> call = jsonPlaceHolderAPI.getDiary("Bearer " + token,idUser);

        call.enqueue(new Callback<List<DiaryDetail>>() {
            @Override
            public void onResponse(Call<List<DiaryDetail>> call, Response<List<DiaryDetail>> response) {

                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                int sumKalori = 0;

                List<DiaryDetail> diaries = response.body();
                DiaryAdapter diaryAdapter = new DiaryAdapter(diaries);
                rvDiary.setAdapter(diaryAdapter);
                rvDiary.setLayoutManager(new LinearLayoutManager(getContext()));

                for (DiaryDetail diary : diaries){
                    sumKalori += diary.getCulinary().getKalori();

                }

                totalkalori.setText(String.valueOf(sumKalori));
                empty.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<DiaryDetail>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

}
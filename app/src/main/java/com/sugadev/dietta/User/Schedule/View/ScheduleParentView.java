package com.sugadev.dietta.User.Schedule.View;

import static android.content.ContentValues.TAG;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sugadev.dietta.API.BaseUrlConfig;
import com.sugadev.dietta.API.JsonPlaceHolderAPI;
import com.sugadev.dietta.R;
import com.sugadev.dietta.User.Schedule.Adapter.ScheduleParentAdapter;
import com.sugadev.dietta.User.Schedule.Model.Parent.ScheduleParent;
import com.sugadev.dietta.User.Schedule.Model.Parent.ScheduleParentDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScheduleParentView extends Fragment {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";
    public static final String ID = "idUser";
    private String token, id;
    private int idUser;

    ImageView btnAdd;
    RecyclerView rvSchedule;
    TextView empty;
    View view;

    private EditText judul, desc;
    String sJudul, sDesc;

    Retrofit retrofit;
    JsonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.activity_schedule, container, false);

        initialization();
        getData();
        addJadwal();

        return view;
    }

    private void initialization(){
        btnAdd = view.findViewById(R.id.btnAddSchedule);
        rvSchedule = view.findViewById(R.id.rvSchedule);
        empty = view.findViewById(R.id.tvEmptySche);
    }

    private void addJadwal(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });
    }

    private void showCustomDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog);

        judul = dialog.findViewById(R.id.etcdJudul);
        desc = dialog.findViewById(R.id.etdcDesc);
        Button btnSubmit = dialog.findViewById(R.id.btnSubmit);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambahJadwal();
                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void loadToken(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(TOKEN, "");
        id = sharedPreferences.getString(ID, "");
        idUser = Integer.parseInt(id);
        Log.i(TAG, "loadToken: " + token + id);
    }

    private void getData(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlConfig.BASE_URL_SCHEDULE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        loadToken();

        Call<List<ScheduleParentDetail>> call = jsonPlaceHolderAPI.getScheduleParent("Bearer "+token,idUser);


        call.enqueue(new Callback<List<ScheduleParentDetail>>() {
            @Override
            public void onResponse(Call<List<ScheduleParentDetail>> call, Response<List<ScheduleParentDetail>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<ScheduleParentDetail> scheduleParentDetails = response.body();
                ScheduleParentAdapter scheduleParentAdapter = new ScheduleParentAdapter(scheduleParentDetails);
                empty.setVisibility(View.GONE);
                rvSchedule.setAdapter(scheduleParentAdapter);
                rvSchedule.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<List<ScheduleParentDetail>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                empty.setVisibility(View.VISIBLE);
            }
        });
    }

    public void tambahJadwal() {
        sJudul = judul.getText().toString();
        sDesc = desc.getText().toString();

        loadToken();

        ScheduleParent scheduleParent = new ScheduleParent(0,sJudul,sDesc,idUser);

        Call<ScheduleParent> call = jsonPlaceHolderAPI.addScheduleParent("Bearer "+token,scheduleParent);

        call.enqueue(new Callback<ScheduleParent>() {
            @Override
            public void onResponse(Call<ScheduleParent> call, Response<ScheduleParent> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "message : " + response.message());
                    return;
                }

                Toast.makeText(getContext(), "Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ScheduleParent> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}
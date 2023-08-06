package com.sugadev.dietta.User.Schedule.Adapter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sugadev.dietta.API.BaseUrlConfig;
import com.sugadev.dietta.API.JsonPlaceHolderAPI;
import com.sugadev.dietta.R;
import com.sugadev.dietta.User.Schedule.Model.Child.ScheduleChild;
import com.sugadev.dietta.User.Schedule.Model.Parent.ScheduleParent;
import com.sugadev.dietta.User.Schedule.Model.Parent.ScheduleParentDetail;
import com.sugadev.dietta.User.Schedule.View.ScheduleChildView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScheduleVideoAdapter extends RecyclerView.Adapter<ScheduleVideoAdapter.ScheduleHolder> {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";
    public static final String ID = "idUser";
    private String token, id;
    private int idUser;

    Retrofit retrofit;
    JsonPlaceHolderAPI jsonPlaceHolderAPI;
    List<ScheduleParentDetail> data;
    int idVideo;

    public ScheduleVideoAdapter(List<ScheduleParentDetail> data, int idVideo) {
        this.data = data;
        this.idVideo = idVideo;
    }

    @NonNull
    @Override
    public ScheduleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.model_schedule,parent,false);
        return new ScheduleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleHolder holder, int position) {
        int p = position;
        holder.title.setText(data.get(p).getScheduleParent().getTitle());
        holder.desc.setText(data.get(p).getScheduleParent().getDescription());
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BaseUrlConfig.BASE_URL_SCHEDULE)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

                SharedPreferences sharedPreferences = v.getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                token = sharedPreferences.getString(TOKEN, "");
                id = sharedPreferences.getString(ID, "");
                idUser = Integer.parseInt(id);
                Log.i(TAG, "loadToken: " + token + id);

                ScheduleChild scheduleChild = new ScheduleChild(0,idVideo, data.get(p).getScheduleParent().getIdScheParent(), 0);

                Call<ScheduleChild> call = jsonPlaceHolderAPI.addScheduleChild("Bearer " + token, scheduleChild);

                call.enqueue(new Callback<ScheduleChild>() {
                    @Override
                    public void onResponse(Call<ScheduleChild> call, Response<ScheduleChild> response) {
                        if (!response.isSuccessful()){
                            Toast.makeText(v.getContext(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                        }

                        Toast.makeText(v.getContext(), "Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ScheduleChild> call, Throwable t) {
                        Log.e(TAG, "onFailure: ", t);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ScheduleHolder extends RecyclerView.ViewHolder {

        TextView title, desc;
        CardView detail;

        public ScheduleHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tvJudulSchedule);
            desc = itemView.findViewById(R.id.tvDeskripsiSchedule);
            detail = itemView.findViewById(R.id.cardSchedule);
        }
    }
}

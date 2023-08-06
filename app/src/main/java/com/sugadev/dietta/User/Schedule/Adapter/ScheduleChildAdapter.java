package com.sugadev.dietta.User.Schedule.Adapter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.sugadev.dietta.API.BaseUrlConfig;
import com.sugadev.dietta.API.JsonPlaceHolderAPI;
import com.sugadev.dietta.R;
import com.sugadev.dietta.User.Diary.Model.Diary;
import com.sugadev.dietta.User.Schedule.Model.Child.ScheduleChild;
import com.sugadev.dietta.User.Schedule.Model.Child.ScheduleChildDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScheduleChildAdapter extends RecyclerView.Adapter<ScheduleChildAdapter.ScheduleHolder> {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";
    public static final String ID = "idUser";
    private String token, id;
    private int idUser;

    List<ScheduleChildDetail> data;

    public ScheduleChildAdapter(List<ScheduleChildDetail> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ScheduleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.model_video, parent, false);
        return new ScheduleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleHolder holder, int position) {
        int p = position;
        holder.judul.setText(data.get(p).getVideo().getTitle());
        Picasso.get().load(data.get(p).getVideo().getThumbnail()).into(holder.thumbnail);
        holder.desc.setText(data.get(p).getVideo().getDescription());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                dialog.setTitle("Alert")
                        .setMessage("Ingin menghapus video ini ?")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                dialoginterface.cancel();
                            }
                        })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(BaseUrlConfig.BASE_URL_SCHEDULE)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

                                SharedPreferences sharedPreferences = v.getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                                token = sharedPreferences.getString(TOKEN, "");
                                id = sharedPreferences.getString(ID, "");
                                idUser = Integer.parseInt(id);
                                Log.i(TAG, "loadToken: " + token + id);

                                Call<ScheduleChild> call = jsonPlaceHolderAPI.deleteScheduleChild("Bearer " + token, data.get(p).getSchedule().getIdScheduleChild());

                                call.enqueue(new Callback<ScheduleChild>() {
                                    @Override
                                    public void onResponse(Call<ScheduleChild> call, Response<ScheduleChild> response) {
                                        if (!response.isSuccessful()) {
                                            Toast.makeText(v.getContext(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        Toast.makeText(v.getContext(), "Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(Call<ScheduleChild> call, Throwable t) {
                                        Toast.makeText(v.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ScheduleHolder extends RecyclerView.ViewHolder {

        TextView judul, desc;
        ImageView thumbnail, btnDelete;
        CardView cardVideo;

        public ScheduleHolder(@NonNull View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.thmblVideo);
            judul = itemView.findViewById(R.id.tvJudulVideo);
            desc = itemView.findViewById(R.id.tvDeskripsiVideo);
            cardVideo = itemView.findViewById(R.id.cardVideo);
            btnDelete = itemView.findViewById(R.id.btnDelVideo);
        }
    }
}

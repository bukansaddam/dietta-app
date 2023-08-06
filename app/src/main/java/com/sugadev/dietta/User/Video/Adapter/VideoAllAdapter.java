package com.sugadev.dietta.User.Video.Adapter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.sugadev.dietta.API.BaseUrlConfig;
import com.sugadev.dietta.API.JsonPlaceHolderAPI;
import com.sugadev.dietta.R;
import com.sugadev.dietta.User.Schedule.Model.Child.ScheduleChild;
import com.sugadev.dietta.User.Schedule.View.ScheduleChildView;
import com.sugadev.dietta.User.Video.Model.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoAllAdapter extends RecyclerView.Adapter<VideoAllAdapter.VideoAllHolder> {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";
    public static final String ID = "idUser";
    private String token, id;
    private int idUser;

    List<Video> data;

    Retrofit retrofit;
    JsonPlaceHolderAPI jsonPlaceHolderAPI;
    int idParent;

    public VideoAllAdapter(List<Video> data, int idParent) {
        this.data = data;
        this.idParent = idParent;
    }

    @NonNull
    @Override
    public VideoAllHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.model_video,parent,false);
        return new VideoAllHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAllHolder holder, int position) {
        int p = position;
        holder.judul.setText(data.get(position).getTitle());
        holder.desc.setText(data.get(position).getDescription());
        Picasso.get().load(data.get(position).getThumbnail()).into(holder.thumbnail);
        holder.cardVideo.setOnClickListener(new View.OnClickListener() {
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

                ScheduleChild scheduleChild = new ScheduleChild(0,data.get(p).getId(), idParent, 0);

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

    public class VideoAllHolder extends RecyclerView.ViewHolder {

        TextView judul,desc;
        ImageView thumbnail, btnDelete;
        CardView cardVideo;
        public VideoAllHolder(@NonNull View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.thmblVideo);
            judul = itemView.findViewById(R.id.tvJudulVideo);
            desc = itemView.findViewById(R.id.tvDeskripsiVideo);
            cardVideo = itemView.findViewById(R.id.cardVideo);
            btnDelete = itemView.findViewById(R.id.btnDelVideo);
            btnDelete.setVisibility(View.GONE);
        }
    }
}

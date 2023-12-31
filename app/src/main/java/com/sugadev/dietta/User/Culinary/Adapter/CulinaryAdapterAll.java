package com.sugadev.dietta.User.Culinary.Adapter;

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
import com.sugadev.dietta.User.Culinary.Model.Culinary;
import com.sugadev.dietta.User.Diary.Model.Diary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CulinaryAdapterAll extends RecyclerView.Adapter<CulinaryAdapterAll.CulinaryHolder>{

    JsonPlaceHolderAPI jsonPlaceHolderAPI;
    Retrofit retrofit;
    List<Culinary> data;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";
    public static final String ID = "idUser";
    private String token, id;
    private int idUser;

    public CulinaryAdapterAll(List<Culinary> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public CulinaryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.model_makanan_horizontal,parent,false);
        return new CulinaryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CulinaryHolder holder, int position) {
        int p = position;
        holder.tvTitle.setText(data.get(p).getTitle());
        holder.tvDesc.setText(data.get(p).getDeskripsi());
        holder.tvKalori.setText(String.valueOf(data.get(p).getKalori()));
        Picasso.get().load(data.get(p).getThumbnail()).into(holder.imgMakanan);
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                retrofit = new Retrofit.Builder()
                        .baseUrl(BaseUrlConfig.BASE_URL_DIARY)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                SharedPreferences sharedPreferences = v.getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                token = sharedPreferences.getString(TOKEN, "");
                id = sharedPreferences.getString(ID, "");
                idUser = Integer.parseInt(id);
                Log.i(TAG, "loadToken: " + token + id);

                jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

                String currTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                Log.i(TAG, "time: " + currTime);

                Diary diary = new Diary(0, "tes", "tes", currTime, idUser, data.get(p).getIdCulinary(), data.get(p).getTitle(), data.get(p).getKalori(), data.get(p).getThumbnail());

                Call<Diary> call = jsonPlaceHolderAPI.addDiary("Bearer " + token,diary);

                call.enqueue(new Callback<Diary>() {
                    @Override
                    public void onResponse(Call<Diary> call, Response<Diary> response) {
                        if (!response.isSuccessful()){
                            Toast.makeText(v.getContext(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                        }

                        Toast.makeText(v.getContext(), "Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Diary> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class CulinaryHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDesc, tvKalori;
        ImageView imgMakanan;
        CardView detail;
        ImageView del;

        public CulinaryHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvNamaMakanan);
            tvDesc = itemView.findViewById(R.id.tvDeskripsi);
            imgMakanan = itemView.findViewById(R.id.imgMakanan);
            detail = itemView.findViewById(R.id.btnDetail);
            tvKalori = itemView.findViewById(R.id.tvKalori);
            del = itemView.findViewById(R.id.btnDelItem);
            del.setVisibility(View.GONE);
        }
    }
}

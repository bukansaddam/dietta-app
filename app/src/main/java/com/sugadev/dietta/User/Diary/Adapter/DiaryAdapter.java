package com.sugadev.dietta.User.Diary.Adapter;

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
import com.sugadev.dietta.User.Diary.Model.DiaryDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.DiaryHolder> {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";
    public static final String ID = "idUser";
    private String token, id;
    private int idUser;

    List<DiaryDetail> data;

    public DiaryAdapter(List<DiaryDetail> diaries) {
        this.data = diaries;
    }

    @NonNull
    @Override
    public DiaryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.model_makanan_horizontal, parent, false);
        return new DiaryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryHolder holder, int position) {
        int p = position;
        holder.tvTitle.setText(data.get(p).getCulinary().getTitle());
        holder.tvDesc.setText(data.get(p).getCulinary().getDeskripsi());
        Picasso.get().load(String.valueOf(data.get(p).getCulinary().getThumbnail())).into(holder.imgMakanan);
        holder.tvKalori.setText(String.valueOf(data.get(p).getCulinary().getKalori()));
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                dialog.setTitle("Alert")
                        .setMessage("Ingin menghapus menu ini ?")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                dialoginterface.cancel();
                            }
                        })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(BaseUrlConfig.BASE_URL_DIARY)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

                                SharedPreferences sharedPreferences = v.getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                                token = sharedPreferences.getString(TOKEN, "");
                                id = sharedPreferences.getString(ID, "");
                                idUser = Integer.parseInt(id);
                                Log.i(TAG, "loadToken: " + token + id);

                                Call<Diary> call = jsonPlaceHolderAPI.deleteDiary("Bearer " + token,data.get(p).getDiary().getId());

                                call.enqueue(new Callback<Diary>() {
                                    @Override
                                    public void onResponse(Call<Diary> call, Response<Diary> response) {
                                        if (!response.isSuccessful()){
                                            Toast.makeText(v.getContext(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        Toast.makeText(v.getContext(), "Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(Call<Diary> call, Throwable t) {
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

    public class DiaryHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDesc, tvKalori;
        ImageView imgMakanan;
        CardView detail;
        ImageView btnDelete;

        public DiaryHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvNamaMakanan);
            tvDesc = itemView.findViewById(R.id.tvDeskripsi);
            imgMakanan = itemView.findViewById(R.id.imgMakanan);
            detail = itemView.findViewById(R.id.btnDetail);
            tvKalori = itemView.findViewById(R.id.tvKalori);
            btnDelete = itemView.findViewById(R.id.btnDelItem);
        }
    }
}

package com.sugadev.dietta.Admin.Culinary;

import static android.content.ContentValues.TAG;

import android.content.Intent;
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
import com.sugadev.dietta.JsonPlaceHolderAPI;
import com.sugadev.dietta.R;
import com.sugadev.dietta.User.Culinary.Culinary;
import com.sugadev.dietta.User.Culinary.CulinaryDetail;
import com.sugadev.dietta.User.Diary.Diary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CulinaryAdapterAdmin extends RecyclerView.Adapter<CulinaryAdapterAdmin.CulinaryHolder>{

    JsonPlaceHolderAPI jsonPlaceHolderAPI;
    Retrofit retrofit;
    List<Culinary> data;

    public CulinaryAdapterAdmin(List<Culinary> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public CulinaryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.model_makanan_horizontal_admin,parent,false);
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
                Intent dirDetail = new Intent(v.getContext(), CulinaryDetailAdmin.class);
                dirDetail.putExtra("id", data.get(p).getIdCulinary());
                dirDetail.putExtra("image", data.get(p).getThumbnail());
                dirDetail.putExtra("title", data.get(p).getTitle());
                dirDetail.putExtra("desc", data.get(p).getDeskripsi());
                dirDetail.putExtra("protein", data.get(p).getProtein());
                dirDetail.putExtra("lemak", data.get(p).getLemak());
                dirDetail.putExtra("karbo", data.get(p).getKarbohidrat());
                dirDetail.putExtra("kalori", data.get(p).getKalori());
                v.getContext().startActivity(dirDetail);
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

        public CulinaryHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvNamaMakanan);
            tvDesc = itemView.findViewById(R.id.tvDeskripsi);
            imgMakanan = itemView.findViewById(R.id.imgMakanan);
            detail = itemView.findViewById(R.id.btnDetail);
            tvKalori = itemView.findViewById(R.id.tvKalori);
        }
    }
}

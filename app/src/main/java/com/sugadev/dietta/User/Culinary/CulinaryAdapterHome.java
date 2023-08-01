package com.sugadev.dietta.User.Culinary;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.sugadev.dietta.R;

import java.util.List;

public class CulinaryAdapterHome extends RecyclerView.Adapter<CulinaryAdapterHome.MakananHolder> {

    List<Culinary> data;

    public CulinaryAdapterHome(List<Culinary> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MakananHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.model_makanan_homepage,parent,false);
        return new MakananHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MakananHolder holder, int position) {
        int p = position;
        holder.tvTitle.setText(data.get(p).getTitle());
        holder.tvDesc.setText(data.get(p).getDeskripsi());
        Picasso.get().load(data.get(p).getThumbnail()).into(holder.imgMakanan);
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dirDetail = new Intent(v.getContext(), CulinaryDetail.class);
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


    public class MakananHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDesc;
        ImageView imgMakanan;
        CardView detail;

        public MakananHolder(View view) {
            super(view);

            tvTitle = view.findViewById(R.id.tvNamaMakananHome);
            tvDesc = view.findViewById(R.id.tvDeskripsiHome);
            imgMakanan = view.findViewById(R.id.imgMakananHome);
            detail = view.findViewById(R.id.cardHome);
        }
    }

}

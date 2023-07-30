package com.sugadev.dietta.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.sugadev.dietta.Model.Culinary;
import com.sugadev.dietta.R;

import java.util.List;

public class MakananAdapterHome extends RecyclerView.Adapter<MakananAdapterHome.MakananHolder> {

    List<Culinary> data;

    public MakananAdapterHome(List<Culinary> data) {
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
        holder.tvTitle.setText(data.get(position).getTitle());
        holder.tvDesc.setText(data.get(position).getDeskripsi());
        Picasso.get().load(data.get(position).getThumbnail()).into(holder.imgMakanan);
//        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent iDetail = new Intent(view.getContext(),DetailTempat.class);
//                iDetail.putExtra("image", images[p]);
//                iDetail.putExtra("nama_tempat", data1[p]);
//                iDetail.putExtra("kategori", data2[p]);
//                iDetail.putExtra("jam_buka", data3[p]);
//                iDetail.putExtra("desc", data4[p]);
//                iDetail.putExtra("loc", data5[p]);
//                view.getContext().startActivity(iDetail);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MakananHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDesc;
        ImageView imgMakanan;

        public MakananHolder(View view) {
            super(view);

            tvTitle = view.findViewById(R.id.tvNamaMakananHome);
            tvDesc = view.findViewById(R.id.tvDeskripsiHome);
            imgMakanan = view.findViewById(R.id.imgMakananHome);
        }
    }
}

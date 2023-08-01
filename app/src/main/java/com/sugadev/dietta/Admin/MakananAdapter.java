package com.sugadev.dietta.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sugadev.dietta.R;

public class MakananAdapter extends RecyclerView.Adapter<MakananAdapter.MakananHolder> {

    String[] data1, data2;
    int[] images;
    Context context;

    public MakananAdapter(String[] judul, String[] deskripsi, int[] images, Context context) {
        this.data1 = judul;
        this.data2 = deskripsi;
        this.images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public MakananHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.model_makanan_horizontal,parent,false);
        return new MakananHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MakananHolder holder, int position) {
        int p = position;
        holder.tvTitle.setText(data1[position]);
        holder.tvDesc.setText(data2[position]);
        holder.imgMakanan.setImageResource(images[position]);
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
        return images.length;
    }


    public class MakananHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDesc;
        ImageView imgMakanan;

        public MakananHolder(View view) {
            super(view);

            tvTitle = view.findViewById(R.id.tvNamaMakanan);
            tvDesc = view.findViewById(R.id.tvDeskripsi);
            imgMakanan = view.findViewById(R.id.imgMakanan);
        }
    }
}

package com.sugadev.dietta.User.Diary;

import static android.content.ContentValues.TAG;

import android.util.Log;
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

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.DiaryHolder> {

    List<DiaryDetail> data;

    public DiaryAdapter(List<DiaryDetail> diaries) {
        this.data = diaries;
    }

    @NonNull
    @Override
    public DiaryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.model_makanan_horizontal,parent,false);
        return new DiaryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryHolder holder, int position) {
        int p = position;
        holder.tvTitle.setText(data.get(p).getCulinary().getTitle());
        holder.tvDesc.setText(data.get(p).getCulinary().getDeskripsi());
        Picasso.get().load(String.valueOf(data.get(p).getCulinary().getThumbnail())).into(holder.imgMakanan);
        holder.tvKalori.setText(String.valueOf(data.get(p).getCulinary().getKalori()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class DiaryHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDesc, tvKalori;
        ImageView imgMakanan;
        CardView detail;
        public DiaryHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvNamaMakanan);
            tvDesc = itemView.findViewById(R.id.tvDeskripsi);
            imgMakanan = itemView.findViewById(R.id.imgMakanan);
            detail = itemView.findViewById(R.id.btnDetail);
            tvKalori = itemView.findViewById(R.id.tvKalori);
        }
    }
}

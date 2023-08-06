package com.sugadev.dietta.User.Video.Adapter;

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
import com.sugadev.dietta.User.Video.Model.Video;
import com.sugadev.dietta.User.Video.View.VideoDetailView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.YogaHolder> {

    List<Video> data;

    public VideoAdapter(List<Video> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public YogaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.model_video,parent,false);
        return new YogaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YogaHolder holder, int position) {
        int p = position;
        holder.judul.setText(data.get(position).getTitle());
        holder.desc.setText(data.get(position).getDescription());
        Picasso.get().load(data.get(position).getThumbnail()).into(holder.thumbnail);
        holder.cardVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dirDetail = new Intent(v.getContext(), VideoDetailView.class);
                dirDetail.putExtra("video", data.get(p).getUrl());
                dirDetail.putExtra("title", data.get(p).getTitle());
                dirDetail.putExtra("desc", data.get(p).getDescription());
                dirDetail.putExtra("category", data.get(p).getCategory());
                v.getContext().startActivity(dirDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class YogaHolder extends RecyclerView.ViewHolder {

        TextView judul,desc;
        ImageView thumbnail, btnDelete;
        CardView cardVideo;

        public YogaHolder(@NonNull View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.thmblVideo);
            judul = itemView.findViewById(R.id.tvJudulVideo);
            desc = itemView.findViewById(R.id.tvDeskripsiVideo);
            cardVideo = itemView.findViewById(R.id.cardVideo);
            btnDelete = itemView.findViewById(R.id.btnDelVideo);
        }
    }
}

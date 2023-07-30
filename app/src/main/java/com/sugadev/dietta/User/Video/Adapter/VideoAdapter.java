package com.sugadev.dietta.User.Video.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.sugadev.dietta.R;
import com.sugadev.dietta.User.Video.Model.Video;

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
        holder.judul.setText(data.get(position).getTitle());
        holder.desc.setText(data.get(position).getDescription());
        Picasso.get().load(data.get(position).getThumbnail()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class YogaHolder extends RecyclerView.ViewHolder {

        TextView judul,desc;
        ImageView thumbnail;

        public YogaHolder(@NonNull View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.thmblVideo);
            judul = itemView.findViewById(R.id.tvJudulVideo);
            desc = itemView.findViewById(R.id.tvDeskripsiVideo);
        }
    }
}

package com.sugadev.dietta.User.Schedule;

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
import com.sugadev.dietta.User.Schedule.Model.Schedul;
import com.sugadev.dietta.User.Schedule.Model.Schedule;

import java.util.List;

public class ScheduleDetailAdapter extends RecyclerView.Adapter<ScheduleDetailAdapter.ScheduleHolder> {

    List<Schedul> data;

    public ScheduleDetailAdapter(List<Schedul> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ScheduleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.model_video,parent,false);
        return new ScheduleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleHolder holder, int position) {
        int p = position;
        holder.judul.setText(data.get(p).getVideo().getTitle());
        Picasso.get().load(data.get(p).getVideo().getThumbnail()).into(holder.thumbnail);
        holder.desc.setText(data.get(p).getVideo().getDescription());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ScheduleHolder extends RecyclerView.ViewHolder {

        TextView judul,desc;
        ImageView thumbnail;
        CardView cardVideo;

        public ScheduleHolder(@NonNull View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.thmblVideo);
            judul = itemView.findViewById(R.id.tvJudulVideo);
            desc = itemView.findViewById(R.id.tvDeskripsiVideo);
            cardVideo = itemView.findViewById(R.id.cardVideo);
        }
    }
}

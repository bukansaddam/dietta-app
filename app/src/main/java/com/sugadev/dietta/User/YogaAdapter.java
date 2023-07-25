package com.sugadev.dietta.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sugadev.dietta.Admin.MakananAdapter;
import com.sugadev.dietta.R;

public class YogaAdapter extends RecyclerView.Adapter<YogaAdapter.YogaHolder> {

    String[] data1, data2;
    int[] images;
    Context context;

    public YogaAdapter(String[] data1, String[] data2, int[] images, Context context) {
        this.data1 = data1;
        this.data2 = data2;
        this.images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public YogaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.model_video,parent,false);
        return new YogaAdapter.YogaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YogaHolder holder, int position) {
        int p = position;
        holder.judul.setText(data1[position]);
        holder.desc.setText(data2[position]);
        holder.thumbnail.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return 0;
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

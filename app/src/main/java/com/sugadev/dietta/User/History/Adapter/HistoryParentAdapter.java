package com.sugadev.dietta.User.History.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sugadev.dietta.R;
import com.sugadev.dietta.User.History.Model.History;
import com.sugadev.dietta.User.History.Model.HistoryParent;
import com.sugadev.dietta.User.History.Model.HistoryParentDetail;
import com.sugadev.dietta.User.History.View.HistoryChildView;

import java.util.List;

public class HistoryParentAdapter extends RecyclerView.Adapter<HistoryParentAdapter.HistoryHolder> {

    List<History> data;

    public HistoryParentAdapter(List<History> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.model_history,parent,false);
        return new HistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        int p = position;
        holder.title.setText(data.get(p).getTitle());
        holder.desc.setText(data.get(p).getDescription());
        holder.tanggal.setText(data.get(p).getDate());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class HistoryHolder extends RecyclerView.ViewHolder {

        TextView title, desc, tanggal;
        CardView detail;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tvJudulSchedule);
            desc = itemView.findViewById(R.id.tvDeskripsiSchedule);
            detail = itemView.findViewById(R.id.cardSchedule);
            tanggal = itemView.findViewById(R.id.tvTanggal);
        }
    }
}

package com.sugadev.dietta.User.History.Adapter;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sugadev.dietta.R;
import com.sugadev.dietta.User.History.Model.HistoryDetail;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {

    List<HistoryDetail> data;

    public HistoryAdapter(List<HistoryDetail> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.model_schedule,parent,false);
        return new HistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        int p = position;
        holder.title.setText(data.get(p).getHistoryModel().getTitle());
        Log.i(TAG, data.get(p).getHistoryModel().toString());
        holder.desc.setText(data.get(p).getScheduleHistory().getDate());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class HistoryHolder extends RecyclerView.ViewHolder {

        TextView title, desc;
        CardView detail;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tvJudulSchedule);
            desc = itemView.findViewById(R.id.tvDeskripsiSchedule);
            detail = itemView.findViewById(R.id.cardSchedule);
        }
    }
}

package com.sugadev.dietta.User.Schedule;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sugadev.dietta.R;
import com.sugadev.dietta.User.Schedule.Model.ScheduleDetailParent;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleHolder> {

    List<ScheduleDetailParent> data;

    private static final String api_ScheduleDetail = "http://103.174.115.40:8989/";

    public ScheduleAdapter(List<ScheduleDetailParent> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ScheduleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.model_schedule,parent,false);
        return new ScheduleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleHolder holder, int position) {
        int p = position;
        holder.title.setText(data.get(p).getScheduleParent().getTitle());
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dirDetail = new Intent(v.getContext(), ScheduleDetail.class);
                dirDetail.putExtra("id", data.get(p).scheduleParent.getIdScheParent());
                dirDetail.putExtra("title", data.get(p).scheduleParent.getTitle());
                v.getContext().startActivity(dirDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ScheduleHolder extends RecyclerView.ViewHolder {

        TextView title, desc;
        CardView detail;

        public ScheduleHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tvJudulSchedule);
            desc = itemView.findViewById(R.id.tvDeskripsiSchedule);
            detail = itemView.findViewById(R.id.cardSchedule);
        }
    }
}

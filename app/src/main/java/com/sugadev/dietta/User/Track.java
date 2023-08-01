package com.sugadev.dietta.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sugadev.dietta.R;

import java.text.DecimalFormat;
import java.util.Locale;

public class Track extends Fragment {

    View view;

    private int seconds = 0;

    private boolean running;

    private boolean wasRunning;

    ImageView start;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.activity_track, null, false);

        if (savedInstanceState != null) {
            seconds
                    = savedInstanceState
                    .getInt("seconds");
            running
                    = savedInstanceState
                    .getBoolean("running");
            wasRunning
                    = savedInstanceState
                    .getBoolean("wasRunning");
        }
        runTimer();

        start = view.findViewById(R.id.btnStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (running == false) {
                    running = true;
                }else {
                    running = false;
                    seconds = 0;
                }
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(
            Bundle savedInstanceState)
    {
        savedInstanceState
                .putInt("seconds", seconds);
        savedInstanceState
                .putBoolean("running", running);
        savedInstanceState
                .putBoolean("wasRunning", wasRunning);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    public void onClickStart(View view)
    {
        running = true;
    }

    public void onClickStop(View view)
    {
        running = false;
    }

    public void onClickReset(View view)
    {
        running = false;
        seconds = 0;
    }

    private void runTimer()
    {

        final TextView timeView = view.findViewById(R.id.tvWaktu);

        final TextView kalori = view.findViewById(R.id.tvKaloriTrack);

        final Handler handler
                = new Handler();

        handler.post(new Runnable() {
            @Override

            public void run()
            {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);

                double kaloriBurn = 0.10 - 0.20 / secs;

                DecimalFormat df = new DecimalFormat("#.##");
                timeView.setText(time);


                if (seconds < 3){
                    kalori.setText(String.valueOf(0));
                }else {
                    kalori.setText(df.format(kaloriBurn));
                }

                if (running) {
                    seconds++;
                }

                handler.postDelayed(this, 1000);
            }
        });
    }
}
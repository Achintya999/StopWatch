package com.example.achintya.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.achintya.stopwatch.R.id.txtLap;

public class activity_stopwatch extends Activity {

    private int seconds = 0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }



        Button btnStart = (Button)findViewById(R.id.btnStart);
        Button btnStop = (Button)findViewById(R.id.btnStop);
        Button btnReset = (Button)findViewById(R.id.btnReset);


        Button btnLap = (Button)findViewById(R.id.btnLap);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                running = true;

            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                running = false;

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                running = false;
                seconds = 0;

            }
        });

        runTimer();

        btnLap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = true;
                onLap();
            }
        });




    }

    private void runTimer(){


        final TextView txtTime = (TextView)findViewById(txtLap);

        final android.os.Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                //int millisecs = seconds * 1000;

                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                txtTime.setText(time);

                if(running){

                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    public void onLap(){

        final TextView txtLap = (TextView)findViewById(R.id.txtLap);

        final android.os.Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                //int millisecs = seconds * 1000;

                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                txtLap.setText(time);

                if(running){

                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running);
    }
}

package com.athaman.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final int MAX_TIME = 600000; //set max time for 10 minutes in milliseconds
    final int MIN_TIME = 30000; // set min time for 30 seconds
    final int MINUTES = 60000; // for time structuring
    boolean isRunning = false;
    CountDownTimer timer;
    int time = MIN_TIME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar setTime = (SeekBar) findViewById(R.id.setTime);
        setTime.setMax(MAX_TIME);
        setTime.setProgress(MIN_TIME);

        setTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                time = progress;
                updateTimer();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if(isRunning) {
                    timer.cancel();
                    isRunning = false;
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void startTimer(View view){
        if(!isRunning){
            isRunning = true;
            timer = new CountDownTimer(time, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    time =(int) millisUntilFinished;
                    updateTimer();
                }

                @Override
                public void onFinish() {
                    updateTimer();
                    Toast.makeText(getApplicationContext(), "Your Egg is Done", Toast.LENGTH_LONG).show();
                    MediaPlayer alarm = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                    alarm.start();

                }
            };
            timer.start();
            Toast.makeText(this, "Start", Toast.LENGTH_SHORT).show();
        }else{
            timer.cancel();
            isRunning = false;
            Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateTimer(){
        TextView timeView = (TextView) findViewById(R.id.timeView);
        int minutes = (int)(time/(double)MINUTES);
        int seconds = time%MINUTES/1000;
        String secondString = String.format("%02d", seconds);
        timeView.setText(String.valueOf(minutes)+ ":" + secondString);
    }
}

package com.example.jonathanchirwa.sounddemo;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity{
    SeekBar mSeekBar;
    MediaPlayer mplayer;
    AudioManager audManager;
    TextView text_shown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volume();
        mediaPl();
    }

    public void mediaPl(){
        mSeekBar = (SeekBar) findViewById(R.id.mediaBar);
        mplayer= MediaPlayer.create(this,R.raw.funny);
        mSeekBar.setMax(mplayer.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask(){
            //@override
            public void run(){
                mSeekBar.setProgress(mplayer.getCurrentPosition());
            }
        }, 0, 100);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mplayer.seekTo(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void volume(){
        audManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVolume = audManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar volumeBar = (SeekBar) findViewById(R.id.seekBar);
        volumeBar.setMax(maxVolume);
        volumeBar.setProgress(curVolume);

        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("SeekBar", String.valueOf(i));
                audManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void play (View v){
        mplayer.start();
        text_shown.setText("Playing...");
    }

    public void pause(View v){
        mplayer.pause();
        text_shown.setText("Paused...");
    }



}

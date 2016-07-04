package com.yztc.myviewplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import com.yztc.wdigth.MyVideoView;

import java.io.File;
import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {
    private static final int MAX=0x11,PROGRESS=0x12;
    private String path= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+ File.separator+"bigbang.mp4";
    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
           switch (msg.what){
               case MAX:
                 myVideo.setMax(mediaPlayer.getDuration()/1000);
                   break;
               case PROGRESS:
                   sendEmptyMessageDelayed(PROGRESS,1000);
                   myVideo.setProgress(mediaPlayer.getCurrentPosition()/1000);
                   break;
           }
        }
    };
    @InjectView(R.id.myVideo)
    MyVideoView myVideo;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        play();
        myVideo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress * 1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void play(){
        mediaPlayer=new MediaPlayer();
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
                mediaPlayer.setDisplay(myVideo.getHolder());
                handler.sendEmptyMessage(MAX);
                handler.sendEmptyMessage(PROGRESS);

            }
        });
        mediaPlayer.prepareAsync();

    }
}

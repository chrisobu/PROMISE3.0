package com.example.faars.promise30;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class PreviewActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener{

    private VideoView myVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        String fileRes = null;
        Bundle e = getIntent().getExtras();
        if (e!=null) {
            fileRes = e.getString("fileRes");
        }

        myVideoView = (VideoView)findViewById(R.id.myvideoview);
        myVideoView.setOnCompletionListener(this);
        myVideoView.setOnPreparedListener(this);

        if (!playFileRes(fileRes)) return;

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(myVideoView);
        myVideoView.setMediaController(mediaController);
        myVideoView.start();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        String fileRes = null;
        Bundle e = getIntent().getExtras();
        if (e != null) {
            fileRes = e.getString("fileRes");
        }
        playFileRes(fileRes);
    }

    private boolean playFileRes(String fileRes) {
        if (fileRes==null) {
            stopPlaying();
            return false;
        } else {
            myVideoView.setVideoURI(Uri.parse(fileRes));
            return true;
        }
    }

    public void stopPlaying() {
        myVideoView.stopPlayback();
        this.finish();
    }

   @Override
    public void onCompletion(MediaPlayer mp) {
        finish();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.setLooping(false);
    }

}

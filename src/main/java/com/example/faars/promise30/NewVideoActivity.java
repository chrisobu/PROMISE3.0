package com.example.faars.promise30;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.faars.promise30.Fragments.RecapInfoFragment;
import com.example.faars.promise30.Fragments.StartPageFragment;

public class NewVideoActivity extends AppCompatActivity {

    android.support.v4.app.FragmentTransaction fragmentTransactionNewVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_video);

        fragmentTransactionNewVideo = getSupportFragmentManager().beginTransaction();
        fragmentTransactionNewVideo.replace(R.id.new_video_container, new RecapInfoFragment());
        fragmentTransactionNewVideo.commit();
        getSupportActionBar().setTitle("PROMISE");
    }
}

package com.example.faars.promise30.Fragments;


import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.faars.promise30.ChildActivity;
import com.example.faars.promise30.NewVideoActivity;
import com.example.faars.promise30.PreviewActivity;
import com.example.faars.promise30.R;
import com.example.faars.promise30.SQL.MyDBHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewVideoFragment extends Fragment implements View.OnClickListener{

    public NewVideoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_new_video, container, false);

        ImageView playButton = (ImageView) viewGroup.findViewById(R.id.play_instruct_video);
        Button continueButton = (Button) viewGroup.findViewById(R.id.continue_button);
        ImageView picture = (ImageView) viewGroup.findViewById(R.id.demo_screenshot);
        TextView title = (TextView) viewGroup.findViewById(R.id.title_new_video);
        TextView noNewVideo = (TextView) viewGroup.findViewById(R.id.no_new_video);

        // Get number of days till new video:
        int differenceTermDate = getDifferenceTermDate();
        if(differenceTermDate>0){
            noNewVideo.setVisibility(View.VISIBLE);
            continueButton.setVisibility(View.GONE);
            picture.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
            playButton.setVisibility(View.GONE);
        }else if(differenceTermDate <= 0 && differenceTermDate>-22){
            picture.setVisibility(View.VISIBLE);
            title.setVisibility(View.VISIBLE);
            playButton.setVisibility(View.VISIBLE);
            continueButton.setVisibility(View.VISIBLE);
            noNewVideo.setVisibility(View.GONE);

            continueButton.setOnClickListener(this);
        }else{
            noNewVideo.setVisibility(View.VISIBLE);
            continueButton.setVisibility(View.GONE);
            picture.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
            playButton.setVisibility(View.GONE);
        }
        playButton.setOnClickListener(this);

        return viewGroup;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play_instruct_video:
                String filename = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.demo;
                Intent videoPreviewActivity = new Intent(getActivity(), PreviewActivity.class);
                videoPreviewActivity.putExtra("fileRes", filename);
                startActivity(videoPreviewActivity);
                break;
            case R.id.continue_button:
                startActivity(new Intent(getActivity(), NewVideoActivity.class));
                break;
        }
    }

    private int getDifferenceTermDate(){
        MyDBHandler dbHandler = MyDBHandler.getInstance(getActivity());
        // Term date:
        String termDateString = dbHandler.getCurrentTermDate();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance(); // Get Calendar Instance
        try {
            c.setTime(sdf.parse(termDateString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Time for next video:
        c.add(Calendar.DATE, 71);  // add 10 weeks to term date
        Date endDate = new Date(c.getTimeInMillis());
        // Today's date:
        Date currentDate = new Date(System.currentTimeMillis());
        // Countdown:
        int difference;
        difference = (int) (endDate.getTime()/ (24*60*60*1000)
                -(int) (currentDate.getTime()/ (24*60*60*1000)));

        return difference;
    }
}

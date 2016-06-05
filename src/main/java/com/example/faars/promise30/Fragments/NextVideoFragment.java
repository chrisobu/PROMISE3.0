package com.example.faars.promise30.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.faars.promise30.NewVideoActivity;
import com.example.faars.promise30.R;
import com.example.faars.promise30.SQL.MyDBHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NextVideoFragment extends Fragment implements View.OnClickListener{

    public NextVideoFragment() {
        // Required empty public constructor
    }

    TextView countdown, termdate, thirteenWeeks, tenWeeks;
    Button newVideoNow;
    int weeks =0, days=0, weeksLeft=0, daysLeft=0, daysLeftOfTimezone=0;
    MyDBHandler dbHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_next_video, container, false);

        countdown = (TextView) viewGroup.findViewById(R.id.countdown);
        termdate = (TextView) viewGroup.findViewById(R.id.termDate);
        thirteenWeeks = (TextView) viewGroup.findViewById(R.id.thirteenWeeks);
        tenWeeks = (TextView) viewGroup.findViewById(R.id.tenWeeks);
        newVideoNow = (Button) viewGroup.findViewById(R.id.new_video_now);
        dbHandler = MyDBHandler.getInstance(getActivity());

        int difference = getDifferenceTermDate();

        termdate.setText(getTermDate());
        tenWeeks.setText(getTenWeeksDate());
        thirteenWeeks.setText(getThirteenWeeksDate());

        // If not there yet:
        if(difference >0){
            weeksLeft = difference / 7;
            daysLeft = difference % 7;
            if(weeksLeft>1){
                if(daysLeft>1){
                    countdown.setText("Record a video in " + weeksLeft + " weeks and " + daysLeft + " days");
                }else if(daysLeft==1){
                    countdown.setText("Record a video in " + weeksLeft + " weeks and " + daysLeft + " day");
                }else if(daysLeft==0){
                    countdown.setText("Record a video in " + weeksLeft + " weeks");
                }
            }else if(weeksLeft==1){
                if(daysLeft>1){
                    countdown.setText("Record a video in " + weeksLeft + " week and " + daysLeft + " days");
                }else if(daysLeft==1){
                    countdown.setText("Record a video in " + weeksLeft + " week and " + daysLeft + " day");
                }else if(daysLeft==0){
                    countdown.setText("Record a video in " + weeksLeft + " week");
                }
            }else if(weeksLeft==0){
                if(daysLeft>1){
                    countdown.setText("Record a video in " + daysLeft + " days");
                }else if(daysLeft==1){
                    countdown.setText("Record a video in " + daysLeft + " day");
                }
            }
        }else {//When you are in the right timezone of 3 weeks for a video:
            daysLeftOfTimezone = 21 + difference;
            weeks = daysLeftOfTimezone / 7;
            days = daysLeftOfTimezone % 7;
            newVideoNow.setVisibility(View.VISIBLE);
            if (weeks > 1) {
                if (days > 1) {
                    countdown.setText("Record a video within the next " + weeks + " weeks and " + days + " days");
                } else {
                    countdown.setText("Record a video within the next " + weeks + " weeks");
                }
            } else if (weeks == 1) {
                if (days > 1) {
                    countdown.setText("Record a video within the next " + weeks + " week and " + days + " days");
                } else if (days == 1) {
                    countdown.setText("Record a video within the next " + weeks + " week and " + days + " day");
                } else {
                    countdown.setText("Record a video within the next " + weeks + " week");
                }
            } else if (weeks == 0) {
                if (days > 1) {
                    countdown.setText("Record a video within the next " + days + " days");
                } else if (days == 1) {
                    countdown.setText("Record a video within the next " + days + " day");
                } else {
                    countdown.setText("You are outside the right timezone for a new video. Wait for result of the analysis, or contact St. Olavs Hospital if you forgot to take a video.");
                    newVideoNow.setVisibility(View.GONE);
                }
            } else {
                countdown.setText("You are outside the right timezone for a new video. Wait for result of the analysis, or contact St. Olavs Hospital if you forgot to take a video.");
                newVideoNow.setVisibility(View.GONE);
            }
        }
        newVideoNow.setOnClickListener(this);

        return viewGroup;
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getActivity(), NewVideoActivity.class));
    }

    private int getDifferenceTermDate(){
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

    private String getTenWeeksDate(){
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
        c.add(Calendar.DATE, 70);  // add 10 weeks to term date
        sdf = new SimpleDateFormat("d. MMM");
        return sdf.format(new Date(c.getTimeInMillis()));
    }

    private String getThirteenWeeksDate(){
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
        c.add(Calendar.DATE, 91);  // add 13 weeks to term date
        sdf = new SimpleDateFormat("d. MMM");
        return sdf.format(new Date(c.getTimeInMillis()));
    }

    private String getTermDate(){
        // Term date:
        String termDateString = dbHandler.getCurrentTermDate();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance(); // Get Calendar Instance
        try {
            c.setTime(sdf.parse(termDateString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf = new SimpleDateFormat("d. MMM");
        return sdf.format(new Date(c.getTimeInMillis()));
    }
}
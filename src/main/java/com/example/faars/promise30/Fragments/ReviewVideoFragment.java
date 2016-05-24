package com.example.faars.promise30.Fragments;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.faars.promise30.PreviewActivity;
import com.example.faars.promise30.R;
import com.example.faars.promise30.SQL.MyDBHandler;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewVideoFragment extends Fragment implements View.OnClickListener{

    public ReviewVideoFragment() {
        // Required empty public constructor
    }

    ImageView play, videoView;
    Button approveButton;
    CheckBox check1, check2, check3;
    android.support.v4.app.FragmentTransaction fragmentTransactionReviewVideo;
    MyDBHandler dbHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_review_video, container, false);

        dbHandler = MyDBHandler.getInstance(getActivity());

        approveButton = (Button) viewGroup.findViewById(R.id.approve_button);
        Button newVideoButton = (Button) viewGroup.findViewById(R.id.new_video_button);
        videoView = (ImageView) viewGroup.findViewById(R.id.thumbnail_video3);
        play = (ImageView) viewGroup.findViewById(R.id.play3);
        check1 = (CheckBox) viewGroup.findViewById(R.id.check1);
        check2 = (CheckBox) viewGroup.findViewById(R.id.check2);
        check3 = (CheckBox) viewGroup.findViewById(R.id.check3);

        File directory = new File("/storage/sdcard0/Pictures/PROMISE/" + dbHandler.getCurrentVideo());
        if(directory.exists()){
            Glide.with(this)
                    .load(Uri.fromFile((directory)))
                    .centerCrop()
                    .into(videoView);
        }else{
            Toast.makeText(getActivity(), "File don't exist", Toast.LENGTH_LONG).show();
        }

        newVideoButton.setOnClickListener(this);
        play.setOnClickListener(this);
        approveButton.setOnClickListener(this);
        approveButton.setEnabled(false);

        // Enables and changes background color of approve button when all checkboxes are checked:
        check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(check1.isChecked() && check2.isChecked() && check3.isChecked()){
                    approveButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    approveButton.setEnabled(true);
                }else{
                    approveButton.setBackgroundColor(getResources().getColor(R.color.colorInactive));
                    approveButton.setEnabled(false);
                }
            }
        });
        check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (check1.isChecked() && check2.isChecked() && check3.isChecked()) {
                    approveButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    approveButton.setEnabled(true);
                } else {
                    approveButton.setBackgroundColor(getResources().getColor(R.color.colorInactive));
                    approveButton.setEnabled(false);
                }
            }
        });
        check3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (check1.isChecked() && check2.isChecked() && check3.isChecked()) {
                    approveButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    approveButton.setEnabled(true);
                } else {
                    approveButton.setBackgroundColor(getResources().getColor(R.color.colorInactive));
                    approveButton.setEnabled(false);
                }
            }
        });

        return viewGroup;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.new_video_button:
                approveButton.setBackgroundColor(getResources().getColor(R.color.colorInactive));
                fragmentTransactionReviewVideo = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransactionReviewVideo.replace(R.id.new_video_container, new CameraFragment());
                fragmentTransactionReviewVideo.commit();
                break;
            case R.id.approve_button:
                approveButton.setBackgroundColor(getResources().getColor(R.color.colorInactive));
                fragmentTransactionReviewVideo = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransactionReviewVideo.replace(R.id.new_video_container, new VideoSavedFragment());
                fragmentTransactionReviewVideo.addToBackStack(null);
                fragmentTransactionReviewVideo.commit();
                break;
            case R.id.play3:
                playVideo(dbHandler.getCurrentVideo());
                break;
        }
    }

    private void playVideo(String filename) {
        Intent videoPreviewActivity = new Intent(getActivity(), PreviewActivity.class);
        String res = "/storage/sdcard0/Pictures/PROMISE/" + filename;

        // TODO: check if file exists, else Toast
        videoPreviewActivity.putExtra("fileRes", res);
        startActivity(videoPreviewActivity);
    }
}
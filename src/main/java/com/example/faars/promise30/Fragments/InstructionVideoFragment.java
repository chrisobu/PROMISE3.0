package com.example.faars.promise30.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.faars.promise30.PreviewActivity;
import com.example.faars.promise30.R;
import com.example.faars.promise30.SQL.MyDBHandler;

/**
 * A simple {@link Fragment} subclass.
 */
public class InstructionVideoFragment extends Fragment implements View.OnClickListener {


    Button newVideoButton;

    public InstructionVideoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_instruction_video, container, false);

        ImageView playButton = (ImageView) viewGroup.findViewById(R.id.play_instruct_video2);
        newVideoButton = (Button) viewGroup.findViewById(R.id.newVideoButton);

        playButton.setOnClickListener(this);

        // TODO: If in right timezone: show newVideoButton:
        // newVideoButton.setVisibility(View.VISIBLE);
        // newVideoButton.setOnClickListener(this);

        return viewGroup;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play_instruct_video2:
                playVideo("android.resource://" + getActivity().getPackageName() + "/" + R.raw.demo);
                break;
            case R.id.newVideoButton:
                android.support.v4.app.FragmentTransaction fragmentTransactionVideoSent;
                fragmentTransactionVideoSent = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransactionVideoSent.replace(R.id.main_container, new NewVideoFragment());
                fragmentTransactionVideoSent.commit();
                break;
        }
    }

    private void playVideo(String filename) {
        Intent videoPreviewActivity = new Intent(getActivity(), PreviewActivity.class);
        videoPreviewActivity.putExtra("fileRes", filename);
        startActivity(videoPreviewActivity);
    }
}

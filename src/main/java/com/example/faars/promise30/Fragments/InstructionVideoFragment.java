package com.example.faars.promise30.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.faars.promise30.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InstructionVideoFragment extends Fragment {


    public InstructionVideoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_instruction_video, container, false);
        VideoView videoView = (VideoView) viewGroup.findViewById(R.id.VideoView);
        MediaController mediaController = new MediaController(getActivity());
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoPath("android.resource://" + getActivity().getPackageName() + "/" + R.raw.demo);
        videoView.start();
        return viewGroup;
    }

}

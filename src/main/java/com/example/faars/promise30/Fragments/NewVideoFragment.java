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
import android.widget.VideoView;

import com.example.faars.promise30.ChildActivity;
import com.example.faars.promise30.NewVideoActivity;
import com.example.faars.promise30.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewVideoFragment extends Fragment implements View.OnClickListener{


    public NewVideoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_new_video, container, false);

        ImageView playButton = (ImageView) viewGroup.findViewById(R.id.play_instruct_video);
        Button continueButton = (Button) viewGroup.findViewById(R.id.continue_button);
        playButton.setOnClickListener(this);
        continueButton.setOnClickListener(this);

        return viewGroup;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play_instruct_video:
                // TODO: add preview of instruction video here
                break;
            case R.id.continue_button:
                getActivity().finish();
                startActivity(new Intent(getActivity(), NewVideoActivity.class));
                break;
        }
    }
}

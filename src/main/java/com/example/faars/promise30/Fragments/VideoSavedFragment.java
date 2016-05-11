package com.example.faars.promise30.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.faars.promise30.ChildActivity;
import com.example.faars.promise30.MainActivity;
import com.example.faars.promise30.R;

public class VideoSavedFragment extends Fragment implements View.OnClickListener{

    public VideoSavedFragment() {
        // Required empty public constructor
    }

    public final static String EXTRA_LAYOUT = "com.example.faars.promise20";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_video_saved, container, false);

        Button sendNowButton = (Button) viewGroup.findViewById(R.id.send_now_button);
        Button sendLaterButton = (Button) viewGroup.findViewById(R.id.send_later_button);

        sendNowButton.setOnClickListener(this);
        sendLaterButton.setOnClickListener(this);

        return viewGroup;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_now_button:
                Intent sendNow = new Intent(getActivity(), MainActivity.class);
                sendNow.putExtra(EXTRA_LAYOUT, "SendVideoFragment");
                getActivity().finish(); // TODO: can I remove this?
                startActivity(sendNow);
                break;
            case R.id.send_later_button:
                Intent sendLater = new Intent(getActivity(), MainActivity.class);
                sendLater.putExtra(EXTRA_LAYOUT, "MyVideosFragment");
                getActivity().finish(); // TODO: can I remove this?
                startActivity(sendLater);
                break;
        }
    }
}

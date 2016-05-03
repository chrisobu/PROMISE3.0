package com.example.faars.promise30.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.faars.promise30.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewVideoFragment extends Fragment implements View.OnClickListener{


    public ReviewVideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_review_video, container, false);

        Button reviewContinueButton = (Button) viewGroup.findViewById(R.id.review_continue_button);
        reviewContinueButton.setOnClickListener(this);

        return viewGroup;
    }

    @Override
    public void onClick(View v) {
        android.support.v4.app.FragmentTransaction fragmentTransactionReviewVideo;
        fragmentTransactionReviewVideo = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransactionReviewVideo.replace(R.id.new_video_container, new VideoSavedFragment());
        fragmentTransactionReviewVideo.commit();
    }
}

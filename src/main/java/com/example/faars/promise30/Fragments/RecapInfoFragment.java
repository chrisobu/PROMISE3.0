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
public class RecapInfoFragment extends Fragment implements View.OnClickListener{

    public RecapInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_recap_info, container, false);

        Button recapContinueButton = (Button) viewGroup.findViewById(R.id.recap_continue_button);
        recapContinueButton.setOnClickListener(this);

        return viewGroup;
    }

    @Override
    public void onClick(View v) {
        android.support.v4.app.FragmentTransaction fragmentTransactionRecap;
        fragmentTransactionRecap = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransactionRecap.replace(R.id.new_video_container, new CameraFragment());
        fragmentTransactionRecap.addToBackStack(null);
        fragmentTransactionRecap.commit();
    }
}

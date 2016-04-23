package com.example.faars.promise30.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.faars.promise30.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SendVideoFragment extends Fragment implements View.OnClickListener {


    public SendVideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_send_video, container, false);

        Button sendButton = (Button) viewGroup.findViewById(R.id.send_button);

        sendButton.setOnClickListener(this);

        return viewGroup;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(),
                "Video sent!", Toast.LENGTH_LONG)
                .show();
        android.support.v4.app.FragmentTransaction fragmentTransactionVideoSent;
        fragmentTransactionVideoSent = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransactionVideoSent.replace(R.id.main_container, new StartPageFragment());
        fragmentTransactionVideoSent.commit();

    }
}

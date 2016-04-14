package com.example.faars.promise30.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.faars.promise30.ChildActivity;
import com.example.faars.promise30.MainActivity;
import com.example.faars.promise30.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterChildFragment extends Fragment implements View.OnClickListener{


    public RegisterChildFragment() {
        // Required empty public constructor
    }

    TextView tvChildID, tvHospitalID, tvCountry, tvApiKey;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_register_child, container, false);

        tvChildID = (TextView) viewGroup.findViewById(R.id.et_child_id);
        tvHospitalID = (TextView) viewGroup.findViewById(R.id.et_hospital_id);
        tvCountry = (TextView) viewGroup.findViewById(R.id.et_country);
        tvApiKey = (TextView) viewGroup.findViewById(R.id.et_api_key);
        Button registerChildButton = (Button) viewGroup.findViewById(R.id.register_child_button);

        registerChildButton.setOnClickListener(this);

        tvChildID.setText("");
        tvHospitalID.setText("hei");
        tvCountry.setText("på");
        tvApiKey.setText("deg");

        //TODO: Save ChildID, HospitalID, Country, APIkey, and use when sending the video

        return viewGroup;
    }

    @Override
    public void onClick(View v) {
        getActivity().finish();
        startActivity(new Intent(getActivity(), MainActivity.class));
    }
}

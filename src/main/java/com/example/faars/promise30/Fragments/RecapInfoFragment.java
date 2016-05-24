package com.example.faars.promise30.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.faars.promise30.R;

public class RecapInfoFragment extends Fragment implements View.OnClickListener{

    public RecapInfoFragment() {
        // Required empty public constructor
    }

    CheckBox check1, check2, check3, check4;
    Button recapContinueButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_recap_info, container, false);

        recapContinueButton = (Button) viewGroup.findViewById(R.id.recap_continue_button);
        check1 = (CheckBox) viewGroup.findViewById(R.id.recap_1);
        check2 = (CheckBox) viewGroup.findViewById(R.id.recap_2);
        check3 = (CheckBox) viewGroup.findViewById(R.id.recap_3);
        check4 = (CheckBox) viewGroup.findViewById(R.id.recap_4);

        recapContinueButton.setOnClickListener(this);
        recapContinueButton.setEnabled(false);

        // Enables and changes background color of continue button when all checkboxes are checked:
        check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(check1.isChecked() && check2.isChecked() && check3.isChecked() && check4.isChecked()){
                    recapContinueButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    recapContinueButton.setEnabled(true);
                }else{
                    recapContinueButton.setBackgroundColor(getResources().getColor(R.color.colorInactive));
                    recapContinueButton.setEnabled(false);
                }
            }
        });
        check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(check1.isChecked() && check2.isChecked() && check3.isChecked() && check4.isChecked()){
                    recapContinueButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    recapContinueButton.setEnabled(true);
                }else{
                    recapContinueButton.setBackgroundColor(getResources().getColor(R.color.colorInactive));
                    recapContinueButton.setEnabled(false);
                }
            }
        });
        check3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(check1.isChecked() && check2.isChecked() && check3.isChecked() && check4.isChecked()){
                    recapContinueButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    recapContinueButton.setEnabled(true);
                }else{
                    recapContinueButton.setBackgroundColor(getResources().getColor(R.color.colorInactive));
                    recapContinueButton.setEnabled(false);
                }
            }
        });
        check4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(check1.isChecked() && check2.isChecked() && check3.isChecked() && check4.isChecked()){
                    recapContinueButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    recapContinueButton.setEnabled(true);
                }else{
                    recapContinueButton.setBackgroundColor(getResources().getColor(R.color.colorInactive));
                    recapContinueButton.setEnabled(false);
                }
            }
        });

        return viewGroup;
    }

    @Override
    public void onClick(View v) {
        recapContinueButton.setBackgroundColor(getResources().getColor(R.color.colorInactive));
        android.support.v4.app.FragmentTransaction fragmentTransactionRecap;
        fragmentTransactionRecap = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransactionRecap.replace(R.id.new_video_container, new CameraFragment());
        fragmentTransactionRecap.addToBackStack(null);
        fragmentTransactionRecap.commit();
    }
}

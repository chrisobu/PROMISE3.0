package com.example.faars.promise30.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faars.promise30.ChildActivity;
import com.example.faars.promise30.MainActivity;
import com.example.faars.promise30.R;
import com.example.faars.promise30.Dialogs.TermDatePickerDialog;
import com.example.faars.promise30.SQL.Child;
import com.example.faars.promise30.SQL.MyDBHandler;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterChildFragment extends Fragment implements View.OnClickListener{

    public RegisterChildFragment() {
        // Required empty public constructor
    }

    TextView tvChildID, tvHospitalID, tvCountry;
    String childID, hospitalID, countryID, termDate, nickname, apiKey, profileName;
    EditText showPickedTermDate, firstName;
    MyDBHandler dbHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_register_child, container, false);

        tvChildID = (TextView) viewGroup.findViewById(R.id.et_child_id);
        tvHospitalID = (TextView) viewGroup.findViewById(R.id.et_hospital_id);
        tvCountry = (TextView) viewGroup.findViewById(R.id.et_country);
        showPickedTermDate = (EditText) viewGroup.findViewById(R.id.et_term_date);
        firstName = (EditText) viewGroup.findViewById(R.id.et_first_name);
        Button registerChildButton = (Button) viewGroup.findViewById(R.id.register_child_button);

        dbHandler = MyDBHandler.getInstance(getActivity());

        // Display info from QR-code: TODO: get info from QR code to display here:
        childID = "1234";
        hospitalID = "STO";
        countryID = "NO";
        apiKey = "1234hemmelig";
        tvChildID.setText(childID);
        tvHospitalID.setText(hospitalID);
        tvCountry.setText(countryID);

        registerChildButton.setOnClickListener(this);
        showPickedTermDate.setOnClickListener(this);

        return viewGroup;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_child_button:
                if(checkChildInput()){
                    termDate = showPickedTermDate.getText().toString();
                    nickname = firstName.getText().toString();
                    profileName = "kiri"; // TODO: getCurrentProfileName()
                    Child child = new Child(childID, hospitalID, countryID, termDate, nickname, apiKey, profileName);
                    dbHandler.addChild(child);
                    dbHandler.updateCurrentChild(nickname);

                    Toast toast = Toast.makeText(getActivity(),"Child Registered", Toast.LENGTH_SHORT);
                    toast.show();
                    getActivity().finish();
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }
                break;
            case R.id.et_term_date:
                FragmentTransaction fragTrans = getActivity().getSupportFragmentManager().beginTransaction();
                TermDatePickerDialog termDatePickerDialog = new TermDatePickerDialog();
                termDatePickerDialog.show(fragTrans, "datePicker");

        }

    }

    private boolean checkChildInput(){
        // check: nickname entered
        // check: termdate selected
        return true;
    }

}

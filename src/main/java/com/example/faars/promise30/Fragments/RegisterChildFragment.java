package com.example.faars.promise30.Fragments;


import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faars.promise30.ChildActivity;
import com.example.faars.promise30.MainActivity;
import com.example.faars.promise30.R;
import com.example.faars.promise30.TermDatePickerDialog;

import org.json.JSONException;
import org.json.JSONObject;
import android.support.v4.app.FragmentTransaction;
/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterChildFragment extends Fragment implements View.OnClickListener{


    public RegisterChildFragment() {
        // Required empty public constructor
    }

    TextView tvChildID, tvHospitalID, tvCountry, tvApiKey;

    public final static String EXTRA_NEWCHILDNAME = "com.example.faars.promise20";
    public String childID = null, hospitalID = null, country = null, apiKey = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_register_child, container, false);

        //String strtext = getArguments().getString("message");

        // Display info from QR-code:
        tvChildID = (TextView) getActivity().findViewById(R.id.et_child_id);
        tvChildID = (TextView) viewGroup.findViewById(R.id.et_child_id);
        tvHospitalID = (TextView) viewGroup.findViewById(R.id.et_hospital_id);
        tvCountry = (TextView) viewGroup.findViewById(R.id.et_country);
        tvApiKey = (TextView) viewGroup.findViewById(R.id.et_api_key);
        EditText showPickedTermDate = (EditText) viewGroup.findViewById(R.id.et_term_date);
        // TODO: get info from QR code to display here:
        tvChildID.setText("1234");
        tvHospitalID.setText("STO");
        tvCountry.setText("NO");
        tvApiKey.setText("1234hemmelig");

        //TODO: Save ChildID, HospitalID, Country, APIkey, and use when sending the video

        Button registerChildButton = (Button) viewGroup.findViewById(R.id.register_child_button);
        registerChildButton.setOnClickListener(this);
        showPickedTermDate.setOnClickListener(this);

        return viewGroup;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_child_button:
                Toast toast = Toast.makeText(getActivity(),"Child Registered", Toast.LENGTH_SHORT);
                toast.show();

                // TODO: change "New Child" to name of new child registered
                sendNewNameToMainPage("New Child");
                break;
            case R.id.et_term_date:
                FragmentTransaction fragTrans = getActivity().getSupportFragmentManager().beginTransaction();
                TermDatePickerDialog termDatePickerDialog = new TermDatePickerDialog();
                termDatePickerDialog.show(fragTrans, "datePicker");

        }

    }

    public void sendNewNameToMainPage(String Name){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        String ChildName = Name;
        intent.putExtra(EXTRA_NEWCHILDNAME, ChildName);
        getActivity().finish();
        startActivity(intent);
    }


}

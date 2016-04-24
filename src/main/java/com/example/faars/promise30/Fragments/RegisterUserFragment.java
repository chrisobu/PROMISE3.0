package com.example.faars.promise30.Fragments;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faars.promise30.ChildActivity;
import com.example.faars.promise30.R;
import com.example.faars.promise30.SQL.MyDBHandler;
import com.example.faars.promise30.SQL.Profile;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;

import java.io.FileOutputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterUserFragment extends Fragment implements View.OnClickListener{


    public RegisterUserFragment() {
        // Required empty public constructor
    }

    EditText etNewUsername, etNewPassword, etNewPasswordCheck;
    MyDBHandler dbHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_register_user, container, false);
        TextView orLogIn = (TextView) viewGroup.findViewById(R.id.tvLogInOption);
        SpannableString content = new SpannableString("or log in");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        orLogIn.setText(content);

        // TODO: save username and password
        etNewUsername = (EditText) viewGroup.findViewById(R.id.etNewUsername);
        etNewPassword = (EditText) viewGroup.findViewById(R.id.etNewPassword);
        etNewPasswordCheck = (EditText) viewGroup.findViewById(R.id.etNewPasswordCheck);
        Button bRegister = (Button) viewGroup.findViewById(R.id.bCreateProfile);
        TextView LogInOption = (TextView) viewGroup.findViewById(R.id.tvLogInOption);
        dbHandler = MyDBHandler.getInstance(getActivity());

        bRegister.setOnClickListener(this);
        LogInOption.setOnClickListener(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        return viewGroup;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bCreateProfile:
                 if(checkInput()){
                    saveUserProfile();

                    Toast.makeText(getActivity(),
                            "Profile saved!", Toast.LENGTH_LONG)
                            .show();
                    getActivity().finish();
                    startActivity(new Intent(getActivity(), ChildActivity.class));
                }
                break;

            case R.id.tvLogInOption:
                android.support.v4.app.FragmentTransaction fragmentTransactionLogInOption;
                fragmentTransactionLogInOption = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransactionLogInOption.replace(R.id.log_in_container, new LogInFragment());
                fragmentTransactionLogInOption.addToBackStack(null);
                fragmentTransactionLogInOption.commit();
                break;
        }
    }

    // Checks whether the user has entered text in all three edit text fields or not:
    private boolean checkInput() {
        // If one or two of the fields are empty, return false:
        if (TextUtils.isEmpty(etNewUsername.getText()) || TextUtils.isEmpty(etNewPassword.getText())) {
            Toast.makeText(getActivity().getApplicationContext(),
                    "All fields must have inputs", Toast.LENGTH_LONG).show();
            return false;
        } else {
            if (checkPassword()) {
                if(UsernameNotUsed()){
                    return true;
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Already a profile with this username.", Toast.LENGTH_LONG).show();
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    // Checks if password 1 and 2 are alike:
    private boolean checkPassword() {
        String passwordOne = etNewPassword.getText().toString();
        String passwordTwo = etNewPasswordCheck.getText().toString();

        if (passwordOne.equals(passwordTwo)) {
            return true;
        } else {
            Toast.makeText(getActivity().getApplicationContext(),
                    "Passwords not alike. Reenter password", Toast.LENGTH_LONG)
                    .show();
            etNewPassword.setText(null);
            etNewPasswordCheck.setText(null);
            return false;
        }
    }

    // Checks if username already in use:
    private boolean UsernameNotUsed(){
        String Username = etNewUsername.getText().toString();
        //TODO: check if the same username is saved in PROFILE_TABLE
        /* if(...){

            return true;
        }else{
            return false;
        } */
        return false;
    }

    // Save username and password in SQL database PROFILE_TABLE and update CURRENT_VALUES_TABLE:
    public void saveUserProfile() {
        Profile profile = new Profile(etNewUsername.getText().toString(),etNewPassword.getText().toString());
        dbHandler.addProfile(profile);
        dbHandler.updateLoggedIn("true");
        dbHandler.updateCurrentProfile(etNewUsername.getText().toString());
    }

}

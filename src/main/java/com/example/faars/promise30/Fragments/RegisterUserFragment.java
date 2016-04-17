package com.example.faars.promise30.Fragments;


import android.content.Context;
import android.content.Intent;
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

import java.io.FileOutputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterUserFragment extends Fragment implements View.OnClickListener{


    public RegisterUserFragment() {
        // Required empty public constructor
    }

    EditText etNewUsername, etNewPassword, etNewPasswordCheck;
    // private GoogleApiClient client;

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
                /* if(checkInput()){
                    try {
                    //TODO: use saveUserProfile():
                        saveUserProfile();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } */
                    Toast.makeText(getActivity(),
                            "Profile saved!", Toast.LENGTH_LONG)
                            .show();
                    getActivity().finish();
                    startActivity(new Intent(getActivity(), ChildActivity.class));
                //}
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

  /*  // Save username and password locally:
    public void saveUserProfile() throws Exception {
        String user = readFields();
        String filePath = getFilesDir().toString() + "/test.txt";
        FileOutputStream out = openFileOutput(filePath, Context.MODE_PRIVATE);
        out.write(user.getBytes());
        out.close();
    }

    // Checks whether the user has entered text in all three edit text fields or not:
    private boolean checkInput() {
        // If one or two of the fields are empty, return false:
        if (TextUtils.isEmpty(etNewUsername.getText()) || TextUtils.isEmpty(etNewPassword.getText())) {
            Toast.makeText(getApplicationContext(),
                    "All fields must have inputs", Toast.LENGTH_LONG).show();
            return false;
        } else {
            if (checkPassword()) {
                return true;
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
            Toast.makeText(getApplicationContext(),
                    "Passwords not alike. Reenter password", Toast.LENGTH_LONG)
                    .show();
            etNewPassword.setText(null);
            etNewPasswordCheck.setText(null);
            return false;
        }
    }

    // Merges username and password to one string:
    private String readFields() {
        String password = etNewPassword.getText().toString();
        String username = etNewUsername.getText().toString();
        String eol = System.getProperty("line.separator");
        String user = username + ", " + password + eol;
        return user;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Register Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.faars.promise22/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Register Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.faars.promise22/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
  */

}

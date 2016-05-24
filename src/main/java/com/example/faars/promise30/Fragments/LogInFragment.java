package com.example.faars.promise30.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
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
import com.example.faars.promise30.SQL.MyDBHandler;

public class LogInFragment extends Fragment implements View.OnClickListener{

    public LogInFragment() {
        // Required empty public constructor
    }

    EditText username, password;
    public final static String EXTRA_LAYOUT = "com.example.faars.promise20";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_log_in, container, false);

        Button bLoginPressed = (Button) viewGroup.findViewById(R.id.bLogIn);
        TextView orRegisterNewProfile = (TextView) viewGroup.findViewById(R.id.orRegisterNewProfileOption);
        username = (EditText) viewGroup.findViewById(R.id.etUsername);
        password = (EditText) viewGroup.findViewById(R.id.etPassword);

        // Underline "Or Register a new profile"- text
        SpannableString content = new SpannableString("or register a new profile");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        orRegisterNewProfile.setText(content);

        orRegisterNewProfile.setOnClickListener(this);
        bLoginPressed.setOnClickListener(this);

        return viewGroup;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogIn:
                if(checkLogInData()){
                    Intent intent = new Intent(getActivity(), ChildActivity.class);
                    intent.putExtra(EXTRA_LAYOUT, "ChooseChildFragment");
                    getActivity().finish(); // TODO: can I remove this?
                    startActivity(intent);
                }
                break;
            case R.id.orRegisterNewProfileOption:
                android.support.v4.app.FragmentTransaction fragmentTransactionLogIn;
                fragmentTransactionLogIn = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransactionLogIn.replace(R.id.log_in_container, new RegisterUserFragment());
                fragmentTransactionLogIn.addToBackStack(null);
                fragmentTransactionLogIn.commit();
                break;
        }
    }

    private Boolean checkLogInData(){
        MyDBHandler dbHandler = MyDBHandler.getInstance(getActivity());
        String profileUsername = username.getText().toString();
        String profilePassword = password.getText().toString();

        if(dbHandler.usernameInUse(profileUsername)){
            if(dbHandler.getProfilePassword(profileUsername).equals(profilePassword)){
                dbHandler.updateLoggedIn("true");
                dbHandler.updateCurrentProfile(profileUsername);
                return true;
            }else{
                Toast.makeText(getActivity().getApplicationContext(),
                        "Password incorrect. Try again.", Toast.LENGTH_LONG)
                        .show();
                password.setText(null);
                return false;
            }
        }else{
            Toast.makeText(getActivity().getApplicationContext(),
                    "Username incorrect. Try again.", Toast.LENGTH_LONG)
                    .show();
            password.setText(null);
            username.setText(null);
            return false;
        }
    }
}
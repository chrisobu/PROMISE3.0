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
import android.widget.TextView;

import com.example.faars.promise30.ChildActivity;
import com.example.faars.promise30.MainActivity;
import com.example.faars.promise30.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogInFragment extends Fragment implements View.OnClickListener{


    public LogInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_log_in, container, false);
        Button bLoginPressed = (Button) viewGroup.findViewById(R.id.bLogIn);
        TextView orRegisterNewProfile = (TextView) viewGroup.findViewById(R.id.orRegisterNewProfileOption);
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
                // TODO: check username and password
                getActivity().finish();
                startActivity(new Intent(getActivity(), ChildActivity.class));
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
}

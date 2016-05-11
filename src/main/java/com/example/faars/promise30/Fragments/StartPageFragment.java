package com.example.faars.promise30.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.faars.promise30.ChildActivity;
import com.example.faars.promise30.MainActivity;
import com.example.faars.promise30.R;
import com.example.faars.promise30.SQL.MyDBHandler;

public class StartPageFragment extends Fragment implements View.OnClickListener{

    public StartPageFragment() {
        // Required empty public constructor
    }

    Button About, Login;
    android.support.v4.app.FragmentTransaction fragmentTransactionLogIn;
    MyDBHandler dbHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_start_page, container, false);

        ImageView ivEnglish = (ImageView) viewGroup.findViewById(R.id.ivEnglish);
        ImageView ivNorwegian = (ImageView) viewGroup.findViewById(R.id.ivNorwegian);
        Login = (Button) viewGroup.findViewById(R.id.bLogin);
        About = (Button) viewGroup.findViewById(R.id.bAbout);

        ivEnglish.setOnClickListener(this);
        ivNorwegian.setOnClickListener(this);
        Login.setOnClickListener(this);
        About.setOnClickListener(this);

        dbHandler = MyDBHandler.getInstance(getActivity());

        return viewGroup;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivNorwegian:
                Login.setText("Logg inn");
                About.setText("Om appen");
                break;
            case R.id.ivEnglish:
                Login.setText("Log in");
                About.setText("About the app");
                break;
            case R.id.bLogin:
                if(dbHandler.checkIfAnyRegisteredProfiles()) {
                    fragmentTransactionLogIn = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransactionLogIn.replace(R.id.log_in_container, new LogInFragment());
                    fragmentTransactionLogIn.addToBackStack(null);
                    fragmentTransactionLogIn.commit();
                }else{
                    fragmentTransactionLogIn = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransactionLogIn.replace(R.id.log_in_container, new RegisterUserFragment());
                    fragmentTransactionLogIn.addToBackStack(null);
                    fragmentTransactionLogIn.commit();
                }
                break;
            case R.id.bAbout:
                fragmentTransactionLogIn = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransactionLogIn.replace(R.id.log_in_container, new AboutFragment());
                fragmentTransactionLogIn.addToBackStack(null);
                fragmentTransactionLogIn.commit();
                break;
        }
    }
}

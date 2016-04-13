package com.example.faars.promise30;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.faars.promise30.Fragments.AboutFragment;
import com.example.faars.promise30.Fragments.MainPageFragment;
import com.example.faars.promise30.Fragments.StartPageFragment;

public class LogInActivity extends AppCompatActivity {

    android.support.v4.app.FragmentTransaction fragmentTransactionLogIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        fragmentTransactionLogIn = getSupportFragmentManager().beginTransaction();
        fragmentTransactionLogIn.replace(R.id.log_in_container, new StartPageFragment());
        fragmentTransactionLogIn.commit();
        getSupportActionBar().setTitle("PROMISE 3.0");
    }
}

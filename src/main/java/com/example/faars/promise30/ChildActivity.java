package com.example.faars.promise30;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.faars.promise30.Fragments.ChooseChildFragment;
import com.example.faars.promise30.Fragments.StartPageFragment;

public class ChildActivity extends AppCompatActivity {

    android.support.v4.app.FragmentTransaction fragmentTransactionLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        fragmentTransactionLogIn = getSupportFragmentManager().beginTransaction();
        fragmentTransactionLogIn.replace(R.id.child_container, new ChooseChildFragment());
        fragmentTransactionLogIn.commit();
        getSupportActionBar().setTitle("PROMISE 3.0");
    }
}

package com.example.faars.promise30;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.faars.promise30.Fragments.ChooseChildFragment;
import com.example.faars.promise30.Fragments.MainPageFragment;
import com.example.faars.promise30.Fragments.MyVideosFragment;
import com.example.faars.promise30.Fragments.ReadQRcodeFragment;
import com.example.faars.promise30.Fragments.RegisterChildFragment;
import com.example.faars.promise30.Fragments.SendVideoFragment;

public class ChildActivity extends AppCompatActivity  {

    android.support.v4.app.FragmentTransaction fragmentTransactionChild;
    public static String ChildID = null, HospitalID = null, Country = null;
    public final static String EXTRA_LAYOUT = "com.example.faars.promise20";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        // Check which layout to display:
        String layout = getIntent().getStringExtra(EXTRA_LAYOUT);

        if(layout != null){
            if(layout.equals("RegisterChildFragment")){
                fragmentTransactionChild = getSupportFragmentManager().beginTransaction();
                fragmentTransactionChild.replace(R.id.child_container, new ReadQRcodeFragment());
                fragmentTransactionChild.commit();
                getSupportActionBar().setTitle("PROMISE 3.0");
            }else{ // layout = "ChooseChildFragment" (or other)
                fragmentTransactionChild = getSupportFragmentManager().beginTransaction();
                fragmentTransactionChild.replace(R.id.child_container, new ChooseChildFragment());
                fragmentTransactionChild.commit();
                getSupportActionBar().setTitle("PROMISE 3.0");
            }
        }else{ // layout = empty
            fragmentTransactionChild = getSupportFragmentManager().beginTransaction();
            fragmentTransactionChild.replace(R.id.child_container, new ChooseChildFragment());
            fragmentTransactionChild.commit();
            getSupportActionBar().setTitle("PROMISE 3.0");
        }
    }

    public static void sendQRDataToChildActivity(String child, String hospital, String country){
        ChildID = child;
        HospitalID = hospital;
        Country = country;
    }

    public static String getChildID() {
        return ChildID;
    }

    public static String getHospitalID() {
        return HospitalID;
    }

    public static String getCountry() {
        return Country;
    }
}

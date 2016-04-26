package com.example.faars.promise30;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.faars.promise30.Fragments.ChooseChildFragment;

public class ChildActivity extends AppCompatActivity  {

    android.support.v4.app.FragmentTransaction fragmentTransactionChild;
    public static String ChildID = null, HospitalID = null, Country = null;
    private static final String TAG = ChildActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        //TODO: check if any children are registered on current profile, if so show ChooseChildFragment, else show RegisterChildFragment
        // receive this data in Activity:
      /*  Intent intent = getIntent();
        String intentIsUsed = intent.getStringExtra("isUsed");

        if(intentIsUsed.equals("true")){
            // send data to Fragment
            String childIDmessage = intent.getStringExtra("message");
            Bundle bundle = new Bundle();
            bundle.putString("message", childIDmessage);
            RegisterChildFragment fragobj = new RegisterChildFragment();
            fragobj.setArguments(bundle);
            fragmentTransactionChild = getSupportFragmentManager().beginTransaction();
            fragmentTransactionChild.replace(R.id.child_container, fragobj);
            fragmentTransactionChild.addToBackStack(null);
            fragmentTransactionChild.commit();
            getIntent().removeExtra("isUsed");

        }else{ */

            fragmentTransactionChild = getSupportFragmentManager().beginTransaction();
            fragmentTransactionChild.replace(R.id.child_container, new ChooseChildFragment());
            fragmentTransactionChild.commit();
       // }

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

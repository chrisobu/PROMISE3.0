package com.example.faars.promise30;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faars.promise30.Fragments.ChooseChildFragment;
import com.example.faars.promise30.Fragments.FeedbackFragment;
import com.example.faars.promise30.Fragments.ReadQRcodeFragment;
import com.example.faars.promise30.Fragments.RegisterChildFragment;
import com.example.faars.promise30.Fragments.StartPageFragment;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class ChildActivity extends AppCompatActivity {

    android.support.v4.app.FragmentTransaction fragmentTransactionChild;
    public String childID, hospitalID, country, apiKey;
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

}

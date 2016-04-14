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

        fragmentTransactionChild = getSupportFragmentManager().beginTransaction();
        fragmentTransactionChild.replace(R.id.child_container, new ChooseChildFragment());
        fragmentTransactionChild.commit();
    }

    public void sendInfoToChildActivity(String name, String hosp, String cntry, String api){
        childID = name;
        hospitalID = hosp;
        country = cntry;
        apiKey = api;
    }
}

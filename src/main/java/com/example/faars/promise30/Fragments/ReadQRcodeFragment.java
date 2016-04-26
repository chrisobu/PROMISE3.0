package com.example.faars.promise30.Fragments;


import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faars.promise30.ChildActivity;
import com.example.faars.promise30.MainActivity;
import com.example.faars.promise30.R;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.faars.promise30.SQL.MyDBHandler;
import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadQRcodeFragment extends Fragment implements View.OnClickListener, ZXingScannerView.ResultHandler{

    public ReadQRcodeFragment() {
        // Required empty public constructor
    }

    private ZXingScannerView mScannerView;
    TextView test;
    String ChildID = null;
    String HospitalID = null;
    String Country = null;
    String APIkey = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_read_qrcode, container, false);
        Button scanButton = (Button) viewGroup.findViewById(R.id.scan_button);
        test = (TextView) viewGroup.findViewById(R.id.test);
        scanButton.setOnClickListener(this);

        return  viewGroup;
    }

    @Override
    public void onClick(View v) {
        mScannerView = new ZXingScannerView(getActivity());   // Programmatically initialize the scanner view
        getActivity().setContentView(mScannerView);
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();         // Start camera
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();   // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        mScannerView.stopCamera();   // Stop camera
        Log.e("handler", rawResult.getText()); // Prints scan results

        JSONObject ChildData = null;
        String scanningResult = rawResult.getText();

        if (scanningResult != null) {
            try {
                ChildData = new JSONObject(scanningResult);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                ChildID = ChildData.getString("CHILD_ID");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                HospitalID = ChildData.getString("HOSPITAL_ID");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                Country = ChildData.getString("COUNTRY");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                APIkey = ChildData.getString("API_KEY");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Toast toast = Toast.makeText(getActivity(),"Scan data received!", Toast.LENGTH_SHORT);
            toast.show();

            ChildActivity.sendQRDataToChildActivity(ChildID, HospitalID, Country);
            MyDBHandler dbHandler = MyDBHandler.getInstance(getActivity());
            dbHandler.updateCurrentAPIkey(APIkey);

            getActivity().setContentView(R.layout.activity_child);
            android.support.v4.app.FragmentTransaction fragmentTransactionChild;
            fragmentTransactionChild = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransactionChild.replace(R.id.child_container, new RegisterChildFragment());
            fragmentTransactionChild.addToBackStack(null);
            fragmentTransactionChild.commit();

        }else{
            Toast toast = Toast.makeText(getActivity(),"No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}

package com.example.faars.promise30.Fragments;


import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadQRcodeFragment extends Fragment implements View.OnClickListener{

    public ReadQRcodeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_read_qrcode, container, false);
        Button scanButton = (Button) viewGroup.findViewById(R.id.scan_button);
        scanButton.setOnClickListener(this);

        return  viewGroup;
    }

    @Override
    public void onClick(View v) {
        scanNow();

        //sendIfoToChildActivity();
        android.support.v4.app.FragmentTransaction fragmentTransactionChild;
        fragmentTransactionChild = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransactionChild.replace(R.id.child_container, new RegisterChildFragment());
        fragmentTransactionChild.addToBackStack(null);
        fragmentTransactionChild.commit();
    }

    // event handler for scan button
    public void scanNow(){
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan a QR-code");
        integrator.setResultDisplayDuration(0);
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.initiateScan();
    }

    // function handle scan result
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result:
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        JSONObject ChildData = null;
        String ChildID = null;
        String HospitalID = null;
        String Country = null;
        String APIkey = null;

        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            try {
                ChildData = new JSONObject(scanContent);
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

        }else{
            Toast toast = Toast.makeText(getActivity(),"No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /*
    public void sendIfoToChildActivity() {
        Intent intent = new Intent(getActivity().getBaseContext(), ChildActivity.class);
        Bundle exstras = new Bundle();
        exstras.putString("message", childID);
        exstras.putString("isUsed", "true");
        intent.putExtras(exstras);
        getActivity().startActivity(intent);
    } */
}

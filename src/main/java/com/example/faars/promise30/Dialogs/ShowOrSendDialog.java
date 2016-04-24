package com.example.faars.promise30.Dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.faars.promise30.Fragments.EditChildFragment;
import com.example.faars.promise30.Fragments.SendVideoFragment;
import com.example.faars.promise30.Fragments.StartPageFragment;
import com.example.faars.promise30.R;

/**
 * Created by faars on 17-Apr-16.
 */
public class ShowOrSendDialog extends DialogFragment {

    public final static String EXTRA_VIDEONAME = "com.example.faars.promise20";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // TODO: change "frida_2016_Apr_17_12:49" into actual video chosen
        return new AlertDialog.Builder(getActivity())
                .setTitle("frida_2016_Apr_17_12:49")
                .setNegativeButton("Preview", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: go to preview fragment
                    }
                })
                .setPositiveButton("Send",  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        android.support.v4.app.FragmentTransaction fragmentTransactionVideoSent;
                        fragmentTransactionVideoSent = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransactionVideoSent.replace(R.id.main_container, new SendVideoFragment());
                        fragmentTransactionVideoSent.commit();
                    }
                })
                .create();
    }
}

package com.example.faars.promise30.Dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.faars.promise30.ChildActivity;
import com.example.faars.promise30.Fragments.ChooseChildFragment;
import com.example.faars.promise30.Fragments.EditChildFragment;
import com.example.faars.promise30.MainActivity;
import com.example.faars.promise30.R;
import com.example.faars.promise30.SQL.MyDBHandler;

/**
 * Created by faars on 16-Apr-16.
 */
public class ChildActionDialog extends DialogFragment {

    public final static String EXTRA_CHILDNAME = "com.example.faars.promise20";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        MyDBHandler dbHandler = MyDBHandler.getInstance(getActivity());
        String childName = dbHandler.getCurrentChild();

        return new AlertDialog.Builder(getActivity())
                .setTitle(childName)
                .setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        android.support.v4.app.FragmentTransaction fragmentTransactionEditChild;
                        fragmentTransactionEditChild = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransactionEditChild.replace(R.id.child_container, new EditChildFragment());
                        fragmentTransactionEditChild.commit();
                    }
                })
                .setPositiveButton("Choose", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish(); // TODO: can I remove this one?
                        startActivity(new Intent(getActivity(), MainActivity.class));

                        //TODO: må kanskje sende med intent for å fortelle MainActivity hvilken layout den skal velge?
                       /* Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra(EXTRA_CHILDNAME, "Frida");
                        startActivity(intent); */
                    }
                })
                .create();
    }

}

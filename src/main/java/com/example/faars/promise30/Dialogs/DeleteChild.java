package com.example.faars.promise30.Dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.faars.promise30.Fragments.ChooseChildFragment;
import com.example.faars.promise30.R;
import com.example.faars.promise30.SQL.MyDBHandler;

/**
 * Created by faars on 24-May-16.
 */
public class DeleteChild extends DialogFragment {

    public final static String EXTRA_LAYOUT = "com.example.faars.promise20";
    MyDBHandler dbHandler;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dbHandler = MyDBHandler.getInstance(getActivity());
        String childName = dbHandler.getCurrentChild();

        return new AlertDialog.Builder(getActivity())
                .setTitle("Do you really want to remove " + childName + "?")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                    }
                })
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHandler.deleteChild(dbHandler.getCurrentChild());
                        dbHandler.deleteAllVideosOfChild(dbHandler.getCurrentChild());

                        android.support.v4.app.FragmentTransaction fragmentTransactionDeleteEdit;
                        fragmentTransactionDeleteEdit = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransactionDeleteEdit.replace(R.id.child_container, new ChooseChildFragment());
                        fragmentTransactionDeleteEdit.commit();

                    }
                })
                .create();
    }
}

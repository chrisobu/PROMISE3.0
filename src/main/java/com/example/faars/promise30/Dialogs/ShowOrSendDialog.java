package com.example.faars.promise30.Dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.faars.promise30.Fragments.EditChildFragment;
import com.example.faars.promise30.Fragments.SendVideoFragment;
import com.example.faars.promise30.Fragments.StartPageFragment;
import com.example.faars.promise30.PreviewActivity;
import com.example.faars.promise30.R;
import com.example.faars.promise30.SQL.MyDBHandler;
import com.example.faars.promise30.SQL.Video;

public class ShowOrSendDialog extends DialogFragment {

    MyDBHandler dbHandler;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        dbHandler = MyDBHandler.getInstance(getActivity());

        return new AlertDialog.Builder(getActivity())
                .setTitle(dbHandler.getCurrentVideo())
                .setNegativeButton("Preview", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        playVideo(dbHandler.getCurrentVideo());
                    }
                })
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dbHandler.videoIsSent()) {
                            Toast.makeText(getActivity(), "This video is already sent", Toast.LENGTH_LONG).show();
                        } else {
                            android.support.v4.app.FragmentTransaction fragmentTransactionVideoSent;
                            fragmentTransactionVideoSent = getActivity().getSupportFragmentManager().beginTransaction();
                            fragmentTransactionVideoSent.replace(R.id.main_container, new SendVideoFragment());
                            fragmentTransactionVideoSent.addToBackStack(null);
                            fragmentTransactionVideoSent.commit();
                        }
                    }
                })
                .create();
    }

    private void playVideo(String filename) {
        Intent videoPreviewActivity = new Intent(getActivity(), PreviewActivity.class);
        String res = "/storage/sdcard0/Pictures/PROMISE/" + filename;

        // TODO: check if file exists, else Toast
        videoPreviewActivity.putExtra("fileRes", res);
        startActivity(videoPreviewActivity);
    }
}

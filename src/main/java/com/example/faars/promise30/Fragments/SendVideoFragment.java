package com.example.faars.promise30.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.faars.promise30.PreviewActivity;
import com.example.faars.promise30.R;
import com.example.faars.promise30.SQL.MyDBHandler;
import com.example.faars.promise30.SQL.Video;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
public class SendVideoFragment extends Fragment implements View.OnClickListener {


    public SendVideoFragment() {
        // Required empty public constructor
    }

    ImageView playButton, videoView;
    MyDBHandler dbHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_send_video, container, false);

        dbHandler = MyDBHandler.getInstance(getActivity());

        Button sendButton = (Button) viewGroup.findViewById(R.id.send_button);
        videoView = (ImageView) viewGroup.findViewById(R.id.thumbnail_video);
        playButton = (ImageView) viewGroup.findViewById(R.id.play_current_video);

        File directory = new File("/storage/sdcard0/Pictures/PROMISE/" + dbHandler.getCurrentVideo());
        if(directory.exists()){
            Glide.with(this)
                    .load(Uri.fromFile((directory)))
                    .centerCrop()
                    .into(videoView);
        }else{
            Toast.makeText(getActivity(), "File don't exist", Toast.LENGTH_LONG).show();
        }
        sendButton.setOnClickListener(this);
        playButton.setOnClickListener(this);

        return viewGroup;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play_current_video:
                playVideo(dbHandler.getCurrentVideo());
                break;
            case R.id.send_button:
                Video video = new Video(dbHandler.getCurrentVideo(), "true", dbHandler.getCurrentProfile(), dbHandler.getCurrentChild());
                dbHandler.updateVideo(video);

                //sendVideo();
                android.support.v4.app.FragmentTransaction fragmentTransactionVideoSent;
                fragmentTransactionVideoSent = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransactionVideoSent.replace(R.id.main_container, new FeedbackFragment());
                fragmentTransactionVideoSent.commit();
                break;
        }
    }

    private void playVideo(String filename) {
        Intent videoPreviewActivity = new Intent(getActivity(), PreviewActivity.class);
        String res = "/storage/sdcard0/Pictures/PROMISE/" + filename;

        // TODO: check if file exists, else Toast
        videoPreviewActivity.putExtra("fileRes", res);
        startActivity(videoPreviewActivity);
    }

    private void sendVideo(){
        String address = "christine.buen@hotmail.com";
        String subject = "New video from PROMISE";
        String res = "/storage/sdcard0/Pictures/PROMISE/" + dbHandler.getCurrentVideo();
        Uri attachment = Uri.parse(res);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, address);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_STREAM, attachment);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
        Toast.makeText(getActivity(), "Video sent!", Toast.LENGTH_LONG).show();
    }
}

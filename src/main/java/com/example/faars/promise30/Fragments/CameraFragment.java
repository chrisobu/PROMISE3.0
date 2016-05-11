package com.example.faars.promise30.Fragments;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.example.faars.promise30.R;
import com.example.faars.promise30.SQL.MyDBHandler;
import com.example.faars.promise30.SQL.Video;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CameraFragment extends Fragment implements View.OnClickListener {

    public CameraFragment() {
        // Required empty public constructor
    }

    private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
    private static Uri fileUri;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    static File mediaStorageDir;
    Button cameraButton;
    ImageView showThumbnail;
    static MyDBHandler dbHandler;
    static String APP_FOLDER = "PROMISE";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_camera, container, false);

        dbHandler = MyDBHandler.getInstance(getActivity());

        cameraButton = (Button) viewGroup.findViewById(R.id.camera_button);
        showThumbnail = (ImageView) viewGroup.findViewById(R.id.ivPicture);
        cameraButton.setOnClickListener(this);

        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);  // create a file to save the video
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);  // set the image file name
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1); // set the video image quality to high

        // start the Video Capture Intent
        startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);

        return viewGroup;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);  // create a file to save the video
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);  // set the image file name
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1); // set the video image quality to high

        // start the Video Capture Intent
        startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                // Video captured and saved to fileUri specified in the Intent
                Toast.makeText(getActivity(), "Video saved to:" + APP_FOLDER, Toast.LENGTH_LONG).show();

                android.support.v4.app.FragmentTransaction fragmentTransactionCamera;
                fragmentTransactionCamera = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransactionCamera.replace(R.id.new_video_container, new ReviewVideoFragment());
                fragmentTransactionCamera.commit();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // User cancelled the video capture
                Toast.makeText(getActivity(), "Video capture cancelled. Take a new video.", Toast.LENGTH_LONG).show();
                cameraButton.setText("Go to Camera");
                cameraButton.setVisibility(View.VISIBLE);
            } else {
                // Video capture failed
                Toast.makeText(getActivity(), "Video capture failed. Try again.", Toast.LENGTH_LONG).show();
                cameraButton.setText("Go to Camera");
                cameraButton.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * Create a file Uri for saving an image or video
     */
    private static Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * Create a File for saving an image or video
     */
    private static File getOutputMediaFile(int type) {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), APP_FOLDER);
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(APP_FOLDER, "failed to create directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyy_MMM_dd_HH:mm").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            String fileName = dbHandler.getCurrentChild() + "_" + timeStamp + ".mp4";
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + fileName);
            dbHandler.updateCurrentVideo(fileName);
            Video video = new Video(fileName, "false", dbHandler.getCurrentProfile(), dbHandler.getCurrentChild());
            dbHandler.addVideo(video);
        } else {
            return null;
        }

        return mediaFile;
    }
}

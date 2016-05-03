package com.example.faars.promise30;

import android.app.Activity;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.faars.promise30.Fragments.MyVideosFragment;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by faars on 27-Apr-16.
 */
public class CustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> childVideos;
   // private final Integer[] imageId;

    public CustomList(Activity context, ArrayList<String> childVideos) {
        super(context, R.layout.my_list_view, childVideos);
        this.context = context;
        this.childVideos = childVideos;
       // this.imageId = imageId;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.my_list_view, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.text1);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.thumbnail);
        String fileName = childVideos.get(position);
        txtTitle.setText(fileName);

        // Get Thumbnails:
        String diskState = Environment.getExternalStorageState();
        if(diskState.equals("mounted")){
            File directory = new File("/storage/sdcard0/Pictures/PROMISE/" + fileName);

            if(directory.exists()){
                Glide.with(context)
                        .load(Uri.fromFile((directory)))
                        .centerCrop()
                        .into(imageView);
            }else{
                Toast.makeText(context, "thumbnail don't exist", Toast.LENGTH_LONG).show();
            }
        }


        //imageView.setImageResource(imageId[position]);
        return rowView;
    }
}
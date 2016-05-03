package com.example.faars.promise30.Fragments;


import android.database.Cursor;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faars.promise30.CustomList;
import com.example.faars.promise30.MainActivity;
import com.example.faars.promise30.R;
import com.example.faars.promise30.Dialogs.ShowOrSendDialog;
import com.example.faars.promise30.SQL.MyDBHandler;

import java.io.File;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyVideosFragment extends Fragment {


    public MyVideosFragment() {
        // Required empty public constructor
    }

    ListView lvVideosTaken;
    MyDBHandler dbHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_my_videos, container, false);

        TextView noVideos = (TextView) viewGroup.findViewById(R.id.no_videos);
        lvVideosTaken = (ListView) viewGroup.findViewById(R.id.lvVideosTaken);
        dbHandler = MyDBHandler.getInstance(getActivity());

        // TODO: replace with videos taken with thumbnail:


        // Get List of videos for current profile of current child:
        ArrayList<String> ListVideos = null;
        try {
            ListVideos = dbHandler.getAllCurrentVideos(dbHandler.getCurrentProfile(), dbHandler.getCurrentChild());
        }catch (Exception e){
            e.printStackTrace();
        }

        if(ListVideos.size()>0) {
            noVideos.setVisibility(View.GONE);
            // Define a new Adapter for ListView
            CustomList adapter = new CustomList(getActivity(), ListVideos);

            // Assign adapter to ListView
            lvVideosTaken.setAdapter(adapter);

            // ListView Item Click Listener
            lvVideosTaken.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // ListView Clicked item value
                    String itemValue = (String) lvVideosTaken.getItemAtPosition(position);

                    // Selected name gets highlighted:
                    for (int a = 0; a < parent.getChildCount(); a++) {
                        parent.getChildAt(a).setBackgroundColor(Color.TRANSPARENT);
                    }
                    view.setBackgroundColor(getResources().getColor(R.color.colorSelected));

                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    // Create and show the dialog.
                    ShowOrSendDialog newDialog = new ShowOrSendDialog();
                    newDialog.show(ft, "ShowOrSend");
                }
            });
        }else {
            noVideos.setVisibility(View.VISIBLE);
        }

        return viewGroup;
    }

}

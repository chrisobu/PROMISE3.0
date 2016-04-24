package com.example.faars.promise30.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.faars.promise30.R;
import com.example.faars.promise30.Dialogs.ShowOrSendDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyVideosFragment extends Fragment {


    public MyVideosFragment() {
        // Required empty public constructor
    }

    ListView lvVideosTaken;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup= (ViewGroup) inflater.inflate(R.layout.fragment_my_videos, container, false);

        lvVideosTaken = (ListView) viewGroup.findViewById(R.id.lvVideosTaken);

        // TODO: replace with videos taken with thumbnail, maybe something like this:
        // http://android-er.blogspot.no/2011/05/display-video-thumbnail-in-listview.html
        String[] ListVideos = new String[] { "frida_2016_Apr_17_12:49",
                "frida_2016_Apr_16_12:52",
                "frida_2016_Apr_15_10:02"
        };

        // Define a new Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_selectable_list_item, android.R.id.text1, ListVideos);

        // Assign adapter to ListView
        lvVideosTaken.setAdapter(adapter);

        // ListView Item Click Listener
        lvVideosTaken.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ListView Clicked item value
                String  itemValue    = (String) lvVideosTaken.getItemAtPosition(position);

                // Selected name gets highlighted:
                for(int a = 0; a < parent.getChildCount(); a++) {
                    parent.getChildAt(a).setBackgroundColor(Color.TRANSPARENT);
                }
                view.setBackgroundColor(getResources().getColor(R.color.colorSelected));

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                // Create and show the dialog.
                ShowOrSendDialog newDialog = new ShowOrSendDialog();
                newDialog.show(ft, "ShowOrSend");

                /* A Toast message when a child is chosen:
                // ListView Clicked item index
                int itemPosition = position;
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show(); */
            }
        });

        return viewGroup;
    }

}

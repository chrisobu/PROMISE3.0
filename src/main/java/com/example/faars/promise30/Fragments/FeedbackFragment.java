package com.example.faars.promise30.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.faars.promise30.Dialogs.ShowOrSendDialog;
import com.example.faars.promise30.FeedbackList;
import com.example.faars.promise30.MyVideosList;
import com.example.faars.promise30.R;
import com.example.faars.promise30.SQL.MyDBHandler;

import java.util.ArrayList;

public class FeedbackFragment extends Fragment {

    public FeedbackFragment() {
        // Required empty public constructor
    }

    ListView lvVideosSent;
    MyDBHandler dbHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_feedback, container, false);

        TextView noVideos = (TextView) viewGroup.findViewById(R.id.no_videos_sent);
        lvVideosSent = (ListView) viewGroup.findViewById(R.id.lvVideosSent);

        // Get List of videos for current profile of current child:
        dbHandler = MyDBHandler.getInstance(getActivity());
        ArrayList<String> ListVideos = null;
        try {
            ListVideos = dbHandler.getAllCurrentSentVideos();
        }catch (Exception e){
            e.printStackTrace();
        }

        if(ListVideos.size()>0) {
            noVideos.setVisibility(View.GONE);
            // Define a new Adapter for ListView
            FeedbackList adapter = new FeedbackList(getActivity(), ListVideos);

            // Assign adapter to ListView
            lvVideosSent.setAdapter(adapter);

        }else {
            noVideos.setVisibility(View.VISIBLE);
        }

        return viewGroup;
    }
}

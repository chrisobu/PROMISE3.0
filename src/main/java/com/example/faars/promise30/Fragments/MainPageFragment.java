package com.example.faars.promise30.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.faars.promise30.MainActivity;
import com.example.faars.promise30.R;
import com.example.faars.promise30.SQL.MyDBHandler;

public class MainPageFragment extends Fragment {

    public MainPageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_main_page, container, false);

        TextView tvChildName = (TextView) viewGroup.findViewById(R.id.tvChildName);

        // Get current child from CURRENT_VALUES_TABLE in SQL database:
        MyDBHandler dbHandler = MyDBHandler.getInstance(getActivity());
        String childName = dbHandler.getCurrentChild();

        tvChildName.setText(childName);

        return viewGroup;
    }

}
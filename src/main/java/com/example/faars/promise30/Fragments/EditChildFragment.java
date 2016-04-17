package com.example.faars.promise30.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.faars.promise30.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditChildFragment extends Fragment implements View.OnClickListener{


    public EditChildFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_edit_child, container, false);

        Button saveChildEditButton = (Button) viewGroup.findViewById(R.id.save_edit_child_button);
        Button cancelChildEditButton = (Button) viewGroup.findViewById(R.id.cancel_edit_child_button);

        saveChildEditButton.setOnClickListener(this);
        cancelChildEditButton.setOnClickListener(this);


        return viewGroup;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_edit_child_button:
                android.support.v4.app.FragmentTransaction fragmentTransactionSaveEdit;
                fragmentTransactionSaveEdit = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransactionSaveEdit.replace(R.id.child_container, new ChooseChildFragment());
                fragmentTransactionSaveEdit.commit();
                break;
            case R.id.cancel_edit_child_button:
                android.support.v4.app.FragmentTransaction fragmentTransactionCancelEdit;
                fragmentTransactionCancelEdit = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransactionCancelEdit.replace(R.id.child_container, new ChooseChildFragment());
                fragmentTransactionCancelEdit.commit();
                break;
        }
    }
}

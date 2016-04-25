package com.example.faars.promise30.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.faars.promise30.R;
import com.example.faars.promise30.SQL.Child;
import com.example.faars.promise30.SQL.MyDBHandler;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditChildFragment extends Fragment implements View.OnClickListener{


    public EditChildFragment() {
        // Required empty public constructor
    }

    String childID, hospitalID, countryID, nickname, termDate;
    EditText etNickname, etTermDate;
    Child child;
    MyDBHandler dbHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_edit_child, container, false);

        Button saveChildEditButton = (Button) viewGroup.findViewById(R.id.save_edit_child_button);
        Button cancelChildEditButton = (Button) viewGroup.findViewById(R.id.cancel_edit_child_button);
        Button deleteChildEditButton = (Button) viewGroup.findViewById(R.id.delete_edit_child_button);
        TextView etChildID = (TextView) viewGroup.findViewById(R.id.et_child_edit);
        TextView etHospitalID = (TextView) viewGroup.findViewById(R.id.et_hospital_edit);
        TextView etCountryID = (TextView) viewGroup.findViewById(R.id.et_country_edit);
        etNickname = (EditText) viewGroup.findViewById(R.id.et_nickname_edit);
        etTermDate = (EditText) viewGroup.findViewById(R.id.et_term_date_edit);

        saveChildEditButton.setOnClickListener(this);
        cancelChildEditButton.setOnClickListener(this);
        deleteChildEditButton.setOnClickListener(this);

        dbHandler = MyDBHandler.getInstance(getActivity());
        nickname = dbHandler.getCurrentChild();
        child = dbHandler.getChildData(nickname);
        childID = child.get_childID();
        hospitalID = child.get_hospitalID();
        countryID = child.get_countryID();
        termDate = child.get_termDate();

        etChildID.setText(childID);
        etHospitalID.setText(hospitalID);
        etCountryID.setText(countryID);
        etNickname.setText(nickname);
        etTermDate.setText(termDate);

        return viewGroup;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_edit_child_button:
                if(etNickname.getText().toString().isEmpty()){
                    etNickname.setText(nickname);
                }
                if (etTermDate.getText().toString().isEmpty()){
                    etTermDate.setText(termDate);
                }
                Child editChild = new Child(childID, hospitalID, countryID,
                        etTermDate.getText().toString(), etNickname.getText().toString(),
                        dbHandler.getCurrentProfile());
                // TODO: updateChild() not working
                dbHandler.updateChild(editChild);

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
            case R.id.delete_edit_child_button:
                dbHandler.deleteChild(dbHandler.getCurrentChild());

                android.support.v4.app.FragmentTransaction fragmentTransactionDeleteEdit;
                fragmentTransactionDeleteEdit = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransactionDeleteEdit.replace(R.id.child_container, new ChooseChildFragment());
                fragmentTransactionDeleteEdit.commit();
                break;
        }
    }
}

package com.example.faars.promise30.Fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.faars.promise30.Dialogs.DeleteChild;
import com.example.faars.promise30.R;
import com.example.faars.promise30.SQL.Child;
import com.example.faars.promise30.SQL.MyDBHandler;

import java.util.Calendar;

public class EditChildFragment extends Fragment implements View.OnClickListener{

    public EditChildFragment() {
        // Required empty public constructor
    }

    String childID, hospitalID, countryID, nickname, termDate, videoSent;
    EditText etNickname, etTermDate;
    Child child;
    MyDBHandler dbHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        etTermDate.setOnClickListener(this);

        // Get information from QR-code and display in fragment:
        dbHandler = MyDBHandler.getInstance(getActivity());
        nickname = dbHandler.getCurrentChild();
        child = dbHandler.getChildData(nickname);
        childID = child.get_childID();
        hospitalID = child.get_hospitalID();
        countryID = child.get_countryID();
        termDate = child.get_termDate();
        videoSent =child.get_videoSent();
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
            case R.id.et_term_date_edit:
                DialogFragment termdatePicker = new TermdatePicker();
                termdatePicker.show(getActivity().getSupportFragmentManager(), "datePicker");
                break;
            case R.id.save_edit_child_button:
                if(etNickname.getText().toString().isEmpty()){
                    etNickname.setText(nickname);
                }
                Child editChild = new Child(childID, hospitalID, countryID,
                        termDate, etNickname.getText().toString(),
                        dbHandler.getCurrentProfile(), videoSent);
                dbHandler.updateChild(editChild);

                //TODO: add updateAlarm: 1 week & now
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
                //TODO: add removeAlarm 1 week & now

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                // Create and show the dialog:
                DeleteChild newDialog = new DeleteChild();
                newDialog.show(ft, "DeleteChild");
                break;
        }
    }



    /** TERM DATE PICKER DIALOG CLASS **/
    // Not recommended to include a class in another, but I don't get to display the date otherwise
    public class TermdatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public DatePickerDialog onCreateDialog(Bundle savedInstanceState) {
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);

            DatePickerDialog myDatePicker = new DatePickerDialog(getActivity(), this, year, month, day);

            return myDatePicker;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            etTermDate.setText(String.valueOf(dayOfMonth) + ". " + getMonthName(monthOfYear) + " " + String.valueOf(year));
            termDate = String.valueOf(monthOfYear+1) + "/" + String.valueOf(dayOfMonth) + "/" + String.valueOf(year);
        }

        public String getMonthName(int month) {
            switch (month + 1) {
                case 1:
                    return "Jan";
                case 2:
                    return "Feb";
                case 3:
                    return "Mar";
                case 4:
                    return "Apr";
                case 5:
                    return "May";
                case 6:
                    return "Jun";
                case 7:
                    return "Jul";
                case 8:
                    return "Aug";
                case 9:
                    return "Sep";
                case 10:
                    return "Oct";
                case 11:
                    return "Nov";
                case 12:
                    return "Dec";
            }
            return "";
        }
    }
}

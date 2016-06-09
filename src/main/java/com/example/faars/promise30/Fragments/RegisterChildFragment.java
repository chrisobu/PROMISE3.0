package com.example.faars.promise30.Fragments;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faars.promise30.ChildActivity;
import com.example.faars.promise30.MainActivity;
import com.example.faars.promise30.R;
import com.example.faars.promise30.SQL.Child;
import com.example.faars.promise30.SQL.MyDBHandler;

import java.util.Calendar;

public class RegisterChildFragment extends Fragment implements View.OnClickListener {

    public RegisterChildFragment() {
        // Required empty public constructor
    }

    TextView tvChildID, tvHospitalID, tvCountry;
    String childID, hospitalID, countryID, termDate, nickname, apiKey, profileName;
    EditText showPickedTermDate, firstName;
    Button registerChildButton;
    MyDBHandler dbHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_register_child, container, false);

        tvChildID = (TextView) viewGroup.findViewById(R.id.et_child_id);
        tvHospitalID = (TextView) viewGroup.findViewById(R.id.et_hospital_id);
        tvCountry = (TextView) viewGroup.findViewById(R.id.et_country);
        showPickedTermDate = (EditText) viewGroup.findViewById(R.id.et_term_date);
        firstName = (EditText) viewGroup.findViewById(R.id.et_first_name);
        registerChildButton = (Button) viewGroup.findViewById(R.id.register_child_button);

        registerChildButton.setOnClickListener(this);
        showPickedTermDate.setOnClickListener(this);

        // Display info from QR-code:
        childID = ChildActivity.getChildID();
        hospitalID = ChildActivity.getHospitalID();
        countryID = ChildActivity.getCountry();
        tvChildID.setText(childID);
        tvHospitalID.setText(hospitalID);
        tvCountry.setText(countryID);

        // Checks if the childID is in use:
        dbHandler = MyDBHandler.getInstance(getActivity());
        if (dbHandler.childIdInUse(childID)) {
            Toast.makeText(getActivity(), "Child_ID in use. Can not register the same ID twice. " +
                    "Scan a new QR code", Toast.LENGTH_SHORT).show();
            android.support.v4.app.FragmentTransaction fragmentTransactionChild;
            fragmentTransactionChild = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransactionChild.replace(R.id.child_container, new ReadQRcodeFragment());
            fragmentTransactionChild.commit();
        }
        return viewGroup;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_child_button:
                if (checkChildInput()) {
                    nickname = firstName.getText().toString();
                    profileName = dbHandler.getCurrentProfile();

                    Child child = new Child(childID, hospitalID, countryID, termDate, nickname, profileName, "false");
                    dbHandler.addChild(child);
                    dbHandler.updateCurrentChild(nickname);

                    //TODO: add setupAlarm: 1 week & now
                    Toast.makeText(getActivity(), "Child Registered", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }
                break;
            case R.id.et_term_date:
                DialogFragment termdatePicker = new TermdatePicker();
                termdatePicker.show(getActivity().getSupportFragmentManager(), "datePicker");
                break;
        }
    }

    // String to notification:
    private String stringToNotification(){
        String notification = "Record a video of " + dbHandler.getCurrentChild();
        return notification;
    }


    // Ensures only one child with the same nickname
    private boolean checkChildInput() {
        if (firstName.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Enter a username for the child", Toast.LENGTH_SHORT).show();
            return false;
        } else if (showPickedTermDate.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Choose the child's term date", Toast.LENGTH_SHORT).show();
            return false;
        } else if (dbHandler.nicknameInUse(firstName.getText().toString())) {
            Toast.makeText(getActivity(), "Child's username in use. Choose another one.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }


    /**
     * TERM DATE PICKER DIALOG CLASS
     **/
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
            showPickedTermDate.setText(String.valueOf(dayOfMonth) + ". " + getMonthName(monthOfYear) + " " + String.valueOf(year));
            termDate = String.valueOf(monthOfYear + 1) + "/" + String.valueOf(dayOfMonth) + "/" + String.valueOf(year);
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

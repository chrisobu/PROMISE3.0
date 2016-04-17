package com.example.faars.promise30;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/**
 * Created by faars on 17-Apr-16.
 */
public class TermDatePickerDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener{

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
        //EditText showPickedTermDate = (EditText) findViewById(R.id.et_term_date);;
        //showPickedTermDate.setText(dayOfMonth + " " + monthOfYear +1 + " " + year);

        Toast.makeText(getActivity(), String.valueOf(dayOfMonth) + " " + getMonthName(monthOfYear) +
                " " + String.valueOf(year), Toast.LENGTH_LONG).show();
    }

    public static String getMonthName(int month){
        switch(month+1){
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

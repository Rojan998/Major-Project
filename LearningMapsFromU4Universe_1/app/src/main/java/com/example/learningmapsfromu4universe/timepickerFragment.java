package com.example.learningmapsfromu4universe;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class timepickerFragment extends DialogFragment {

    Calendar c = Calendar.getInstance();
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int minute =  c.get(Calendar.MINUTE);
    @NonNull

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new TimePickerDialog(getActivity(),
                (TimePickerDialog.OnTimeSetListener) getActivity(),
                hour,minute, DateFormat.is24HourFormat(getActivity()));
    }
}

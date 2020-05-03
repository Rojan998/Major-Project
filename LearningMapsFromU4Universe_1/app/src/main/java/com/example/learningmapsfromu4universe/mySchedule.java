package com.example.learningmapsfromu4universe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.util.Calendar;

public class mySchedule extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    public static final String TAG = "Calender View";

    private CalendarView calenderView;
    private TextView datetime;
    private String time;
    private Button set_reminder;
    private Button cancel_reminder;

    TextView mytextView;

    //TimePicker timePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_schedule);

        calenderView = findViewById(R.id.calenderView);
        datetime = findViewById(R.id.timeanddateTextView);

        set_reminder = findViewById(R.id.set_reminder);
        cancel_reminder = findViewById(R.id.cancel_reminder);
       // timePicker = (TimePicker) findViewById(R.id.time_picker);
        mytextView = findViewById(R.id.time_text);

        set_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DialogFragment timepicker = new timepickerFragment();
                timepicker.show(getSupportFragmentManager(), "time picker");

            }
        });

        cancel_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelAlarm();
            }
        });

        calenderView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                String date = i + "/" + (i1 + 1) + "/" + i2;
                Log.d(TAG, "onSelectDateChange: yyyy/mm/dd: " + date);


            }
        });
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND,0);

        updateTimeText(c);
        startAlarm(c);
        TextView textView=  findViewById(R.id.time_text);
        textView.setText("Hour: "+hourOfDay + " Minute: " +minute);
    }

    private void updateTimeText(Calendar c){
        String timeText = "Alarm set for: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        mytextView.setText(timeText);
    }

    private void startAlarm(Calendar c){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, myAlarmBroadcast.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,
                1, intent, 0);
        if (Build.VERSION.SDK_INT >= 23) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }
        Toast.makeText(this, "Alarm is saved !", Toast.LENGTH_SHORT).show();

    }

    private void cancelAlarm() {

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, myAlarmBroadcast.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,
                1, intent, 0);
        alarmManager.cancel(pendingIntent);
        mytextView.setText("Alarm Cancelled ! ");
        Toast.makeText(this, "Alarm is cancelled !", Toast.LENGTH_SHORT).show();
    }

}

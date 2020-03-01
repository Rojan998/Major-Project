package com.example.learningmapsfromu4universe;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class mySchedule extends AppCompatActivity {

    public static final String TAG = "Calender View";

    private CalendarView calenderView;
    private TextView datetime;
    private String time;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_schedule);

        calenderView = findViewById(R.id.calenderView);
        datetime = findViewById(R.id.timeanddateTextView);

        calenderView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                String date = i + "/" + (i1+1) + "/" +i2;
                Log.d(TAG,"onSelectDateChange: yyyy/mm/dd: " + date);
               // Toast.makeText(mySchedule.this, "this button is selected", Toast.LENGTH_SHORT).show();
             /*   String dateValue = date.length();
                if (dateValue.substring(8,9))
*/

                    // i1 = month ; i2= day; i = year

              /*  1st march  String dateValue = new String("date");
                    int len = date.indexOf(date,8);
                Log.d(TAG, "onSelectedDayChange: index is: " + len);*/






         /*    calendarView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Toast.makeText(mySchedule.this, "this button is selected", Toast.LENGTH_SHORT).show();
                 }
             });
*/


            }
        });
    }
}

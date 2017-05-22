package com.example.evan.comp296.calendar_events;


import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.evan.comp296.MainHomeScreen;
import com.example.evan.comp296.Notes.MyAlertDialogFragment;
import com.example.evan.comp296.Notes.Note_database;
import com.example.evan.comp296.Notes.Note_delete_view;
import com.example.evan.comp296.R;

import java.util.Calendar;

/**
 * Created by Evan on 5/19/17.
 */

public class calendar_main extends AppCompatActivity implements DatePicker.OnDateChangedListener {

    DatePicker date;
    TimePicker time;

    String title;
    String date2;
    String time2;

    EditText edit_text_title;

    Button add;
    Button cancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_new_event);

        edit_text_title = (EditText) findViewById(R.id.event_title);

        title = edit_text_title.getText().toString();

        add = (Button) findViewById(R.id.button_event_add);
        cancel= (Button) findViewById(R.id.button_event_cancel);

        date=(DatePicker) findViewById(R.id.datePicker);
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());
        date.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), this);
        date.setBackgroundColor(getResources().getColor(R.color.blue_900));
        date.setLayoutMode(Gravity.CENTER_HORIZONTAL);

        time = (TimePicker) findViewById(R.id.timePicker);
        //time.setCurrentHour(12);
        //time.setCurrentMinute(15);

        time.setEnabled(true);
        date.setEnabled(true);


        time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(calendar_main.this, "Hour of day: "+hourOfDay+ " Minute of day: "+minute,
                        Toast.LENGTH_SHORT).show();


                String formatted_minute = String.format("%02d", minute);


                if(hourOfDay <12) {
                time2=hourOfDay+ ":" + minute + "AM";

                    if (minute < 10) {

                        time2=hourOfDay+ ":" + formatted_minute + "AM";
                    }

                }
                else {
                    time2= hourOfDay-12 + ":" +minute + "PM";

                    if (minute < 10) {

                        time2= hourOfDay-12 + ":" +formatted_minute + "PM";
                    }
                }

                Log.d("Hello", "time = " + time2);
            }
        });




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = edit_text_title.getText().toString();
                if (title == null || title.isEmpty() ) {
                    Toast.makeText(calendar_main.this, "Title cannot be empty",
                            Toast.LENGTH_SHORT).show();
                } else if (title.length() > 25) {
                    Toast.makeText(calendar_main.this, "Title too long",
                            Toast.LENGTH_SHORT).show();
                } else {
                    showDialog(); }
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), MainHomeScreen.class));
            }
        });

    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        //Toast.makeText(calendar_main.this, "Fullate date: Year " +dayOfMonth +" "+monthOfYear +" " +year +" ",
                //Toast.LENGTH_SHORT).show();

            date2 = +monthOfYear + " / "  +dayOfMonth + " / " + year;


        Log.d("Hello", "time = " + date2);

    }


    void showDialog() {
        String message = "Title: " +title +"\n"+ "Date: "+ date2 + "\n"+
                "Time: " +time2;
        DialogFragment newFragment = MyAlertDialogEvents.newInstance(
                R.string.alert_dialog_two_buttons_event, message);
        newFragment.show(getFragmentManager(), "dialog");
    }

    public void doPositiveClick() {
        // Do stuff here.
        //int value = getIntent().getExtras().getInt("position");

        Note_database db = new Note_database(calendar_main.this);

        Event_SQLite event = new Event_SQLite(title, date2, time2);

        db.add_row_event_2(event);

        finish();

        Log.i("FragmentAlertDialog", "Positive click!");
    }

    public void doNegativeClick() {
        // Do stuff here.

        finish();

        Log.i("FragmentAlertDialog", "Negative click!");
    }
}

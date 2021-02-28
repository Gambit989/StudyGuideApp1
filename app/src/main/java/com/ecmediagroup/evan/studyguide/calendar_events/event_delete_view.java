package com.ecmediagroup.evan.studyguide.calendar_events;

import android.app.DialogFragment;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.ecmediagroup.evan.studyguide.Notes_main.Note_database;
import com.ecmediagroup.evan.studyguide.R;

/**
 * Created by Evan on 5/21/17.
 */

public class event_delete_view extends AppCompatActivity {


    int i;
    int position;

    String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_delete);


        //position = getIntent().getExtras().getInt("position");

        title= getIntent().getExtras().getString("title");

        showDialog();

    }


    public event_delete_view() {



    }

    public event_delete_view(int i) {

        this.i=i;


    }




    void showDialog() {
        DialogFragment newFragment = MyAlertDialogDeleteEvent.newInstance(
                R.string.alert_dialog_two_buttons_event_title);
        newFragment.show(getFragmentManager(), "dialog");
    }

    public void doPositiveClick() {
        // Do stuff here.
        //int value = getIntent().getExtras().getInt("position");

        Note_database db = new Note_database(event_delete_view.this);


        Note_database note = Note_database.getInstance(event_delete_view.this);

        note.delete_row_3(title);
        finish();

        Log.i("FragmentAlertDialog", "Positive click!");
    }

    public void doNegativeClick() {
        // Do stuff here.
        finish();
        Log.i("FragmentAlertDialog", "Negative click!");
    }

}

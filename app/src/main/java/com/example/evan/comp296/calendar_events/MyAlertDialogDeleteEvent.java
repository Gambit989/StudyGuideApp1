package com.example.evan.comp296.calendar_events;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.evan.comp296.Notes.MyAlertDialogFragment;
import com.example.evan.comp296.Notes.Note_delete_view;
import com.example.evan.comp296.R;

/**
 * Created by Evan on 5/21/17.
 */

public class MyAlertDialogDeleteEvent extends android.app.DialogFragment {

    public static MyAlertDialogDeleteEvent newInstance(int title) {
        MyAlertDialogDeleteEvent frag = new MyAlertDialogDeleteEvent();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");

        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_warning_black_24dp)
                .setTitle(title)
                .setPositiveButton(R.string.alert_dialog_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((event_delete_view)getActivity()).doPositiveClick();
                            }
                        }
                )
                .setNegativeButton(R.string.alert_dialog_cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((event_delete_view)getActivity()).doNegativeClick();
                            }
                        }
                )
                .create();
    }

}

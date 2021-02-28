package com.ecmediagroup.evan.studyguide.calendar_events;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;

import com.ecmediagroup.evan.studyguide.R;

/**
 * Created by Evan on 5/21/17.
 */

public class MyAlertDialogEvents extends DialogFragment {

    public static MyAlertDialogEvents newInstance(int title, String message) {
        MyAlertDialogEvents frag = new MyAlertDialogEvents();
        Bundle args = new Bundle();
        args.putInt("title", title);
        args.putString("message", message);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");
        String message = getArguments().getString("message");

        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_warning_black_24dp)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.alert_dialog_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((calendar_main)getActivity()).doPositiveClick();
                            }
                        }
                )
                .setNegativeButton(R.string.alert_dialog_edit,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((calendar_main)getActivity()).doNegativeClick();
                            }
                        }
                )
                .create();
    }

}

package com.ecmediagroup.evan.studyguide.Notes_main;

/**
 * Created by Evan on 5/8/17.
 */

public class Note_data {

    public Note_data() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }


        public String note_title;
        public String note_text;



        public Note_data(String title, String text) {

            this.note_title=title;
            this.note_text=text;

        }


    public String getNote_title() {
        return note_title;
    }

    public String getNote_text() {
        return note_text;
    }
}


package com.example.evan.comp296.Notes;

/**
 * Created by Evan on 5/8/17.
 */

public class Note_data {

    public Note_data() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }


        public String note_title;
        public String note_text;



        public Note_data(String text, String title) {

            this.note_text=text;
            this.note_title=title;
        }

    }


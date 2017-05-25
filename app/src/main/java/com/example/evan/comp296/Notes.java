package com.example.evan.comp296;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by Evan on 5/8/17.
 */

public class Notes extends AppCompatActivity {


    String title;
    String note;

    String Email;

    DatabaseReference mDatabase;

        public Notes (String Email, DatabaseReference databaseReference) {

            this.Email = Email;
            this.mDatabase=databaseReference;




        }

        public Notes (String title, String note) {
            this.title = title;
            this.note = note;

        }




        public void CreateNote () {

            Notes note1 = new Notes(title,note);

            mDatabase.child("com.example.evan.comp296.Notes").child(Email).setValue(note1);








        }




}

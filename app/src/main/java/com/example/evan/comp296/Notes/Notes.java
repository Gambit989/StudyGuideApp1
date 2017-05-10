package com.example.evan.comp296.Notes;

import android.support.v7.app.AppCompatActivity;

import com.example.evan.comp296.Data;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by Evan on 5/8/17.
 */

public class Notes extends AppCompatActivity {


    String title;
    String note;
    String Email;
    DatabaseReference mDatabase;

        public Notes(String Email, String title, String note, DatabaseReference databaseReference) {

            this.Email = Email;
            this.mDatabase=databaseReference;
            this.title = title;
            this.note = note;





        }





        public void CreateNote () {


            ArrayList<Note_data> al = new ArrayList();

            al.add(new Note_data(note,title));

            mDatabase.child("Notes").child(Email).setValue(al);








        }




}

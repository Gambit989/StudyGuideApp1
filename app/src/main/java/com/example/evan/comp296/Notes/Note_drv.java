package com.example.evan.comp296.Notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.evan.comp296.R;
import com.example.evan.comp296.tutorials.java.java_main;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * Created by Evan on 5/8/17.
 */

public class Note_drv extends AppCompatActivity implements View.OnClickListener{


    DatabaseReference mDatabase;

    DatabaseReference mDatabase1;
    //SharedPreferences sharedPref;

    String email1;

    String note_title1;
    String note_text1;

    Button create_note;
    Button cancel_note;

    EditText title;
    EditText note_text;


    private FirebaseAuth mAuth;

    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_main);


        create_note =(Button) findViewById(R.id.button_create_note);
        cancel_note =(Button) findViewById(R.id.button_cancel_note);

        title = (EditText) findViewById(R.id.editText_notes_title);
        note_text = (EditText) findViewById(R.id.editText_notes);


        create_note.setOnClickListener(this);
        cancel_note.setOnClickListener(this);
        title.setOnClickListener(this);
        note_text.setOnClickListener(this);




        //sharedPref = Note_drv.this.getSharedPreferences("prefs", MODE_PRIVATE);


        //email1 = sharedPref.getString("email","default");



        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();


        email1= "this is an email";





        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase1 = FirebaseDatabase.getInstance().getReference().child("Notes");



    }


    @Override
    public void onClick(View v) {

        int i =v.getId();


        if (i == R.id.button_create_note) {


            mDatabase1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    System.out.println("does the child exist? " + dataSnapshot.child(email1).exists());





                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });




            //note_info.CreateNote();


            note_title1 = title.getText().toString();
            note_text1 = note_text.getText().toString();

            mDatabase.child("Notes").child(email1).child("note_title").setValue(note_title1);
            mDatabase.child("Notes").child(email1).child("note_text").setValue(note_text1);



            Toast.makeText(Note_drv.this, "Note Created",
                    Toast.LENGTH_SHORT).show();

            finish();


            //startActivity(new Intent(Note_drv.this, java_main.class));


            //Notes note_info = new Notes(email1,note_title1,note_text1,mDatabase);
















        } else if (i == R.id.button_cancel_note){


            Toast.makeText(Note_drv.this, "Cancelled",
                    Toast.LENGTH_SHORT).show();

            finish();

            //startActivity(new Intent(Note_drv.this, java_main.class));




        }

    }
}

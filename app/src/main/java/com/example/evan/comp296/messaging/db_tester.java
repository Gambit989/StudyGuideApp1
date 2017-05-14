package com.example.evan.comp296.messaging;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.evan.comp296.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.evan.comp296.messaging.ProfileActivity;

/**
 * Created by Evan on 4/28/17.
 */

public class db_tester extends AppCompatActivity{

    Button db_post;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_tester);

        // Write a message to the database


    }


    public void db_post (View v) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");


        Toast.makeText(this, myRef.toString(), Toast.LENGTH_LONG).show();



    }




}

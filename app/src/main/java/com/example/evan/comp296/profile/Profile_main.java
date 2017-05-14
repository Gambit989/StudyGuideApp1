package com.example.evan.comp296.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.evan.comp296.Notes.Note_database;
import com.example.evan.comp296.R;

import java.util.ArrayList;

/**
 * Created by Evan on 5/10/17.
 */

public class Profile_main extends AppCompatActivity {


    Note_database nd;

    TextView name;
    TextView email;
    TextView school;
    TextView major;

    ArrayList<String> info = new ArrayList<>();




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        name = (TextView) findViewById(R.id.profile_name);
        email = (TextView) findViewById(R.id.profile_email);
        school = (TextView) findViewById(R.id.profile_school);
        major = (TextView) findViewById(R.id.profile_major);


        nd = new Note_database(Profile_main.this);

        info = nd.get_user_info();

        name.setText(info.get(0));
        email.setText(info.get(1));
        school.setText(info.get(2));







    }
}

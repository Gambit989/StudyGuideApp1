package com.ecmediagroup.evan.studyguide.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ecmediagroup.evan.studyguide.Notes_main.Note_database;
import com.ecmediagroup.evan.studyguide.R;

import java.util.ArrayList;

/**
 * Created by Evan on 5/10/17.
 */

public class Profile_edit extends AppCompatActivity implements View.OnClickListener{


    EditText school;
    EditText major;

    Button submit;
    Button cancel;

    Note_database nd;

    ArrayList<String> info = new ArrayList<>();

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;


    String school2, major2;

    String school_saved, major_saved;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_edit);

        school = (EditText) findViewById(R.id.profile_school);
        major = (EditText) findViewById(R.id.profile_major);


        submit = (Button) findViewById(R.id.edit_profile_submit);
        cancel = (Button) findViewById(R.id.edit_profile_cancel);


        sharedPref = this.getSharedPreferences("myPrefs",Context.MODE_PRIVATE);
        editor = sharedPref.edit();


        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar2);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //ActionBar a = getSupportActionBar();

        school_saved = sharedPref.getString("school_saved", null);

        major_saved = sharedPref.getString("major_saved", null);


        nd = new Note_database(Profile_edit.this);

        info = nd.get_user_info();

        //name.setText(info.get(0));
        //email.setText(info.get(1));


        if(school_saved==null || school_saved.isEmpty()) {
            school.setText(info.get(2));
        } else {
            school.setText(school_saved);
        }

        if (major_saved==null || major_saved.isEmpty()) {
            major.setText("Computer Science");
        } else {
            major.setText(major_saved);
        }




        submit.setOnClickListener(this);
        cancel.setOnClickListener(this);



    }








    @Override
    public void onClick(View v) {

        int id=v.getId();


        if (id== R.id.edit_profile_submit){

            school2 = school.getText().toString();
            major2 = major.getText().toString();

            if (school2.length() < 4 || major2.length() < 4) {

                Toast.makeText(Profile_edit.this, "School or major name cannot be shorter than 4 characters", Toast.LENGTH_SHORT).show();

            } else {
                editor.putString("school_saved", school2);
                editor.commit();

                editor.putString("major_saved", major2);
                editor.commit();

                Log.d("TAG", "*********** new school is " +sharedPref.getString("school_saved", null)+ " **********");
                Log.d("TAG", "*********** new major is " +sharedPref.getString("major_saved", null)+ " **********");

                startActivity(new Intent(Profile_edit.this, Profile_main.class));
            }



        }else if (id == R.id.edit_profile_cancel) {

            finish();


        }


    }
}

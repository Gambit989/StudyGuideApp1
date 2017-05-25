package com.example.evan.comp296.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.evan.comp296.Notes_main.Note_database;
import com.example.evan.comp296.R;
import com.example.evan.comp296.Task2;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Evan on 5/10/17.
 */

public class Profile_main extends AppCompatActivity {


    Note_database nd;

    TextView name;
    TextView email;
    TextView school;
    TextView major;

    ImageView profile_pic;
    ImageView profile_pic_edit;

    Button edit;

    ArrayList<String> info = new ArrayList<>();


    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    String school_saved, major_saved;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        name = (TextView) findViewById(R.id.profile_name);
        email = (TextView) findViewById(R.id.profile_email);
        school = (TextView) findViewById(R.id.profile_school);
        major = (TextView) findViewById(R.id.profile_major);

        profile_pic_edit=(ImageView) findViewById(R.id.imageButton_edit_profile_pic);
        profile_pic= (ImageView) findViewById(R.id.imageView_profile);

        edit = (Button) findViewById(R.id.button_profile_edit_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar2);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //ActionBar a = getSupportActionBar();


        sharedPref = this.getSharedPreferences("myPrefs",Context.MODE_PRIVATE);
        editor = sharedPref.edit();


        school_saved = sharedPref.getString("school_saved", null);

        major_saved = sharedPref.getString("major_saved", null);

        Log.d("TAG", "*********** new school is " +sharedPref.getString("school_saved", null)+ " **********");
        Log.d("TAG", "*********** new major is " +sharedPref.getString("major_saved", null)+ " **********");

        nd = new Note_database(Profile_main.this);

        info = nd.get_user_info();
        name.setText(info.get(0));
        email.setText(info.get(1));


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





        if (nd.Picture_Exists(1) && nd.get_Picture_URL(1) !=null) {

                String url2= nd.get_Picture_URL(1);

                //new Task2().execute(url2);


            if (isConnectedToInternet()) {

                try {
                    profile_pic.setImageBitmap(new Task2().execute(url2).get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            } else { finish();
            }

        }





        profile_pic_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Profile_main.this,MainActivity_Profile.class));
            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Profile_main.this,Profile_edit.class));
            }
        });


    }



    public boolean isConnectedToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }

        }
        return false;
    }



}

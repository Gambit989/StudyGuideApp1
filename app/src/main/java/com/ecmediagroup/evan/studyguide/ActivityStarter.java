package com.ecmediagroup.evan.studyguide;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Evan on 5/6/17.
 */

public class ActivityStarter extends AppCompatActivity {



    ActivityStarter () {


    }


    public void startActivity () {


        startActivity(new Intent(getApplicationContext(), Firebase_New_User.class));




    }





}
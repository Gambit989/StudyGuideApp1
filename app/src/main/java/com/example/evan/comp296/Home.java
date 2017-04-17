package com.example.evan.comp296;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Evan on 11/7/16.
 */

public class Home extends Activity {


        String name, password, email, Err;
        TextView nameTV, emailTV, passwordTV, err;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.home);

            nameTV = (TextView) findViewById(R.id.home_name);
            emailTV = (TextView) findViewById(R.id.home_email);
            passwordTV = (TextView) findViewById(R.id.home_password);
            err = (TextView) findViewById(R.id.err);

            name = getIntent().getStringExtra("name");
            password = getIntent().getStringExtra("password");
            email = getIntent().getStringExtra("email");
            Err = getIntent().getStringExtra("err");

            nameTV.setText("Welcome "+name);
            passwordTV.setText("Your password is "+password);
            emailTV.setText("Your email is "+email);
            err.setText(Err);
        }



    }






package com.example.evan.comp296;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.OutputStream;
import java.io.InputStream;

import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.widget.Button;


import android.view.View;
import android.view.View.OnClickListener;

import android.content.Intent;

/**
 * Created by Evan on 10/23/16.
 */

public class Register  extends AppCompatActivity  {

    EditText first_name, last_name, password, phone_num, email;
    String First_Name, Last_Name, Phone_Number, Password, Email;
    Context ctx=this;

    Toolbar myToolbar;
    boolean enabled = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
               super.onCreate(savedInstanceState);

        setContentView(R.layout.register);



        first_name = (EditText) findViewById(R.id.register_first_name);
        last_name = (EditText) findViewById(R.id.register_last_name);
        phone_num = (EditText) findViewById(R.id.register_phone_number);
        password = (EditText) findViewById(R.id.register_password);
        email = (EditText) findViewById(R.id.register_email);

        //myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //setSupportActionBar(myToolbar);


        getSupportActionBar().setHomeButtonEnabled(enabled);



        //getSupportActionBar().setDisplayUseLogoEnabled(enabled);

        //getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME, 0);




        Button r_button = (Button) findViewById(R.id.register_register);
        r_button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                System.out.println("clicked");

                register_clicked(v);

                }

                });


    }



    public void register_clicked (View v) {



        //System.out.println("clicked");




        Email = email.getText().toString();
        First_Name = first_name.getText().toString();
        Last_Name = last_name.getText().toString();
        Phone_Number = phone_num.getText().toString();
        Password = password.getText().toString();




        AndroidDatabaseHelper database = new AndroidDatabaseHelper(Register.this);
        user_info new_info = new user_info(Email,First_Name,Last_Name, Phone_Number,Password);

        database.add_row(new_info);

        display_info display = new display_info(Email,First_Name,Phone_Number,Password);


        Intent intent =new Intent(Register.this,display_info.class);
        intent.putExtra("email", Email);
        intent.putExtra("first", First_Name);
        intent.putExtra("last", Last_Name);
        intent.putExtra("phone", Phone_Number);
        intent.putExtra("pass", Password);

        startActivity(intent);




        /*

        display.setEmail(Email);
        display.setName(First_Name+Last_Name);
        display.setPhone(Phone_Number);
        display.setPass(Password);

        */


        //setContentView(R.layout.display_layout);

        //startActivity(new Intent(this,display_info.class));


        //display_info display = new display_info(Email,First_Name,Phone_Number,Password);






    }






    public String get_email (String a){
        String email;

        email=a;


    return email;



    }









}

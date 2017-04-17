package com.example.evan.comp296;


import android.support.v7.app.AppCompatActivity;


import android.os.AsyncTask;
import android.util.Log;


import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import java.lang.String;

import android.widget.Toast;

import android.content.Context;

import org.json.JSONObject;
import org.json.JSONException;





public class MainActivity extends AppCompatActivity {



    private EditText user;
    private EditText pass;

    String user_id, password;

    //String Name, Password ;

    Context ctx=this;


    private static final String TAG = MainActivity.class.getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        getSupportActionBar().hide();


        user = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);




        Button sign_in_button = (Button) findViewById(R.id.email_sign_in_button);
        sign_in_button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

               main_login();



            }



        });





    }




    public void main_register(View v){

        startActivity(new Intent(this,Register.class));
    }



    public void main_login(){
        user_id = user.getText().toString();
        password = pass.getText().toString();


        AndroidDatabaseHelper login = new AndroidDatabaseHelper(MainActivity.this);


        if(login.check_Login_Credentials(user_id,password))
        {


            startActivity(new Intent(this,MainHomeScreen.class));
            setContentView(R.layout.activity_main_home_screen);

            Log.d(TAG, "if executed");


            CharSequence hello = "Thank You for logging in " + user_id;


            Toast.makeText( ctx, hello, Toast.LENGTH_LONG).show();



        }
        else {
            CharSequence wrong = "User not found. Please try again";


            Toast.makeText(ctx, wrong, Toast.LENGTH_LONG).show();

            Log.d(TAG, "else executed");


        }



    }









}


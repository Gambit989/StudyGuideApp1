package com.example.evan.comp296;


import android.app.Activity;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;


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

import android.widget.LinearLayout;

import android.content.Context;

import org.json.JSONObject;
import org.json.JSONException;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.CallbackManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;






public class MainActivity extends AppCompatActivity {



    private EditText user;
    private EditText pass;

    String user_id, password;

    //String Name, Password ;

    Context ctx=this;

    private CallbackManager callbackManager;
    private LoginButton loginButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static final String TAG = MainActivity.class.getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

       // AppCompatActivity a = new AppCompatActivity();

        getSupportActionBar().hide();


        user = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);

        //FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };


        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String success = "User ID: "
                        + loginResult.getAccessToken().getUserId()
                        + "\n" +
                        "Auth Token: "
                        + loginResult.getAccessToken().getToken();

                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.email),success
                                + loginResult.getAccessToken().getUserId()
                                + "\n" +
                                "Auth Token: "
                                + loginResult.getAccessToken().getToken(), Snackbar.LENGTH_LONG);

                                snackbar.show();

                            //test();
                //setContentView(R.layout.activity_main_home_screen);
            }

            @Override
            public void onCancel() {

                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.email),("Login attempt canceled."), Snackbar.LENGTH_LONG);

                snackbar.show();

            }

            @Override
            public void onError(FacebookException e) {

                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.email),("Login attempt Failed."), Snackbar.LENGTH_LONG);

                snackbar.show();


            }
        });




        Button sign_in_button = (Button) findViewById(R.id.email_sign_in_button);
        sign_in_button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

               main_login();



            }



        });


    }

    //end of OnCreate



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    // main register function - opens register class
    public void main_register(View v){

        startActivity(new Intent(this,Register.class));
    }



    // main login funtion
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


    // main firebase login funtion
    public void main_login_firebase(View v){

        startActivity(new Intent(this,Firebase_New_User.class));


    }









    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void test () {
        startActivity(new Intent(MainActivity.this, MainHomeScreen.class));
        setContentView(R.layout.activity_main_home_screen);

    }


    public void getmAuth1() {
        mAuth.createUserWithEmailAndPassword(user_id, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // [START_EXCLUDE]
                        //hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }

}


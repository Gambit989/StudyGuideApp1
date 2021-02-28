package com.ecmediagroup.evan.studyguide;


import android.net.Uri;
import androidx.annotation.NonNull;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.FragmentTransaction;
import android.util.Log;

import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import java.lang.String;
import java.util.Arrays;

import android.widget.Toast;

import android.content.Context;

import org.json.JSONObject;
import org.json.JSONException;

import com.ecmediagroup.evan.studyguide.Notes_main.Note_database;
import com.ecmediagroup.evan.studyguide.Notes_main.User_SQLite;
import com.ecmediagroup.evan.studyguide.google.ChooserActivity;
import com.ecmediagroup.evan.studyguide.google.SignInActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.*;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        GoogleApiClient.OnConnectionFailedListener {



    private EditText user;
    private EditText pass;

    private EditText sign_in_status;

    String user_id, password;

    //String Name, Password ;

    Context ctx=this;

    private CallbackManager callbackManager;
    private LoginButton loginButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private FirebaseUser mFirebaseUser;

    private static final String TAG = MainActivity.class.getSimpleName();

    String mUsername;
    //Firebase_New_User fb_user = new Firebase_New_User();
    private String mPhotoUrl;
    private static final int RC_SIGN_IN = 9001;


    private SignInButton mSignInButton;

    private GoogleApiClient mGoogleApiClient;

    String str;


    private DatabaseReference mDatabase;
    private String Name;
    private String FEmail;
    private String Fbirthday;
    private String Fprofile;

    private String Link;
    private String Gender;
    private String FB_id;

    LoginManager lm;

    FragmentTransaction FragmentTransaction;




    //SharedPreferences sharedPref;

    AccessTokenTracker accessTokenTracker;

    Note_database note_db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // AppCompatActivity a = new AppCompatActivity();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //getSupportActionBar().hide();
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();


        mUsername = "Anonymous";

        user = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);

        // Assign fields
        mSignInButton = (SignInButton) findViewById(R.id.sign_in_button);

        //FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());

        mAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mAuth.getCurrentUser();




        note_db = new Note_database(MainActivity.this);


        //sharedPref = MainActivity_Profile.this.getSharedPreferences("prefs", MODE_PRIVATE);

        //final FirebaseUser user = firebaseAuth.getCurrentUser();


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {

                if (mFirebaseUser != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + mFirebaseUser.getUid());


                } else {
                    // User is signed out
                   // mUsername = mFirebaseUser.getDisplayName();
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }

        };




                //FACEBOOK CODE


        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.setReadPermissions(Arrays.asList("public_profile", "user_friends", "email", "user_birthday"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                AccessToken accessToken = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();
                // Facebook Email address


                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                Log.v("LoginActivity Response ", response.toString());

                                try {
                                    Name = object.getString("name");
                                    //Link = object.getString("link");
                                    //Gender = object.getString("gender");
                                    FB_id = object.getString("id");
                                    FEmail = object.getString("email");
                                    Fbirthday = object.getString("birthday");
                                    Gender= object.getString("gender");
                                    //Fprofile = object.getString("public_profile");
                                    Log.v("ID = ", " " + FB_id);
                                    Log.v("Email = ", " " + FEmail);

                                    writeNewFBUser(FB_id,Name,FEmail,Fbirthday,Gender);

                                    User_SQLite sql = new User_SQLite("1", FEmail, Name, "Brookdale Community College");

                                    Toast.makeText(getApplicationContext(), "Welcome " + Name, Toast.LENGTH_LONG).show();


                                    if (note_db.ID_Exists(1)) {
                                        note_db.update_row_info(sql);

                                    } else {

                                        note_db.add_row_info(sql); }




                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id, first_name, last_name, name, email, birthday, gender");
                        request.setParameters(parameters);
                        request.executeAsync();







                        //SharedPreferences.Editor editor = sharedPref.edit();

                        /*

                        SharedPreferences.Editor editor = sharedPref.edit();

                        editor.putString("email",FEmail);



                        editor.commit();


                        */

                        //System.out.println(Name);
                        //System.out.println(FEmail);



                String success = "User ID: "
                        + loginResult.getAccessToken().getUserId()
                        + "\n" +
                        "Auth Token: "
                        + loginResult.getAccessToken().getToken();


                Log.d(TAG, "********** FACEBOOK SUCCEEDED*******" + /*loginResult.getAccessToken().getToken().toString()*/ loginResult.getAccessToken().getPermissions().toString());

                //startActivity(new Intent(MainActivity_Profile.this, MainHomeScreen.class));
                //setContentView(R.layout.activity_main_home_screen);



                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.email), success
                                + loginResult.getAccessToken().getUserId()
                                + "\n" +
                                "Auth Token: "
                                + loginResult.getAccessToken().getToken()
                                + "\n" +
                                "Email "
                                + loginResult.getAccessToken().getPermissions().toString()
                                +"\n" +
                                "Name " + Name, Snackbar.LENGTH_LONG);

                snackbar.show();

                //user.setText(loginResult.getAccessToken().getUserId());
                //pass.setText(loginResult.getAccessToken().getToken());




                startActivity(new Intent(MainActivity.this, MainHomeScreen.class));
                //setContentView(R.layout.activity_main_home_screen);

                //test();
                //setContentView(R.layout.activity_main_home_screen);
            }

            @Override
            public void onCancel() {

                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.email), ("Login attempt canceled."), Snackbar.LENGTH_LONG);

                snackbar.show();

            }

            @Override
            public void onError(FacebookException e) {

                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.email), ("Login attempt Failed."), Snackbar.LENGTH_LONG);

                snackbar.show();


            }
        });




        /*

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken newAccessToken) {
                updateWithToken(newAccessToken);
            }
        };

        */



        //updateWithToken(AccessToken.getCurrentAccessToken());

        AccessToken token;
        token = AccessToken.getCurrentAccessToken();

        if (token != null) {
            startActivity(new Intent(this,MainHomeScreen.class));
        }



        //END FACEBOOK CODE




                Button sign_in_button = (Button) findViewById(R.id.email_sign_in_button);
                sign_in_button.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        main_login();


                    }


                });

        findViewById(R.id.sign_in_button).setOnClickListener(this);

        //mSignInButton.setOnClickListener(this);

        // [START configure_signin]
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("616707699367-soapc1oeelufi1e9tuultnu5at9k0q6j.apps.googleusercontent.com")
                .requestEmail()
                .build();
        // [END configure_signin]

        // [START build_client]
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        // [END build_client]

        //mGoogleApiClient.connect();

        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {

            startActivity(new Intent(this,MainHomeScreen.class));
            // ...
        } else {
            // not signed in. Show the "sign in" button and explanation.
            // ...
        }


        mAuth = FirebaseAuth.getInstance();




        //SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
                 //signInButton.setSize(SignInButton.SIZE_STANDARD);







        if (mFirebaseUser==null) {

        }
        else if (mFirebaseUser.isEmailVerified()==true) {
            setContentView(R.layout.activity_main_home_screen); }
        //updateUI(currentUser);

        //str = String.valueOf(mGoogleApiClient.isConnected());
        //user.setText(str);


        mDatabase = FirebaseDatabase.getInstance().getReference();





        /*
        //loginButton.setReadPermissions(Arrays.asList(
                //"public_profile", "email", "user_birthday", "user_friends"));


        FragmentTransaction = getSupportFragmentManager().beginTransaction();

        FragmentTransaction.replace(R.id.fb_login,new fbLogin());

        FragmentTransaction.commit();


        */



    }

            //end of OnCreate







    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);


        Log.d(TAG, "------******ON ACTIVITY RESULT CALLED ---------- *******");



        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);}


    }


    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Google Sign In was successful, authenticate with Firebase
            GoogleSignInAccount account = result.getSignInAccount();
            firebaseAuthWithGoogle(account);

            GoogleSignInAccount acct = result.getSignInAccount();
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();



            Log.d(TAG, personName);
            Log.d(TAG, personId);
            Log.d(TAG, personEmail);
            Log.d(TAG, personGivenName);
            Log.d(TAG, personFamilyName);


            /*


            writeNewGoogleUser(personId,personName,personEmail);

            SharedPreferences.Editor editor = sharedPref.edit();

            editor.putString("email",personEmail);

            editor.commit();


            */



            Bundle b = new Bundle();

            b.putString("google_name", personName);
            b.putString("google_given", personGivenName);
            b.putString("google_family", personFamilyName);
            b.putString("google_email", personEmail);
            b.putString("google_id", personId);


            User_SQLite sql2 = new User_SQLite("1", personEmail, personName, "Brookdale Community College");

            //note_db.add_row_info(sql2);


            if (note_db.ID_Exists(1)) {

                note_db.update_row_info(sql2);
            } else {

                note_db.add_row_info(sql2); }



            startActivity(new Intent(this,MainHomeScreen.class));

            Intent in = new Intent(getApplicationContext(), MainHomeScreen.class);
            in.putExtras(b);
            //startActivity(in);
        } else {
            // Google Sign In failed, update UI appropriately
            // [START_EXCLUDE]
            updateUI(null);
            // [END_EXCLUDE]
        }
    }




    // [START auth_with_google]
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]
        //showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                        //hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END auth_with_google]


    private void writeNewGoogleUser(String userId, String name, String email) {

        Log.d(TAG, " ******** GOOGLE USER DATABASE METHOD CALLED *******");
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("message");
        User user = new User(name, email);

        mDatabase.child("Google Users").child(userId).setValue(user);

        //myRef.setValue(user);
    }


    private void writeNewFireBaseUser(String userId, String name, String email) {
        User user = new User(name, email);

        mDatabase.child("Firebase Users").child(userId).setValue(user);
    }


    private void writeNewFBUser(String userId, String name, String email, String birthday, String gender) {

        Log.d(TAG, "******FB DATABSE METHOD CALLED******");

        User user = new User(name, email, birthday, gender);


        mDatabase.child("Facebook Users").child(userId).setValue(user);
    }



    // [START signin]
    private void signIn() {

        Log.d("tag", "---------- SIGN IN WAS CLICKED --- ");
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
        if (mGoogleApiClient.isConnected()) {
            Log.d("tag", "---------- GOOGLE API CLIENT IS CONNECTED --- ");
            //startActivity(new Intent(this,MainHomeScreen.class));
        }
    }
    // [END signin]

    private void signOut() {
        // Firebase sign out
        mAuth.signOut();

        // Google sign out
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        updateUI(null);
                    }
                });
    }

    private void revokeAccess() {
        // Firebase sign out
        mAuth.signOut();

        // Google revoke access
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        updateUI(null);
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        //hideProgressDialog();



        /*
        if (user != null) {
            mStatusTextView.setText(getString(R.string.google_status_fmt, user.getEmail()));
            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
        } else {
            mStatusTextView.setText(R.string.signed_out);
            mDetailTextView.setText(null);

            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
        }


        */
    }




    // main register function - opens register class
    public void main_register(View v){

        startActivity(new Intent(this,Firebase_New_User.class));
    }






    // main login funtion
    public void main_login() {
        user_id = user.getText().toString();
        password = pass.getText().toString();


        //mAuth.signInWithEmailAndPassword(user_id,password);
        //fb_user.signIn(user_id, password);


        if (user_id ==null || user_id.isEmpty() || password ==null || password.isEmpty()) {
            Toast.makeText(MainActivity.this, "Username or password cannot be empty",
                    Toast.LENGTH_LONG).show();

        } else {
            mAuth.signInWithEmailAndPassword(user_id, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                            FirebaseUser currentUser = mAuth.getCurrentUser();

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.


                            if (!task.isSuccessful()) {
                                Log.w(TAG, "signInWithEmail:failed", task.getException());
                                Toast.makeText(MainActivity.this, R.string.auth_failed,
                                        Toast.LENGTH_SHORT).show();
                            } else if (currentUser.isEmailVerified() == false) {
                                Toast.makeText(MainActivity.this, "Sign in failed, Verify your email",
                                        Toast.LENGTH_LONG).show();


                            } else if (task.isSuccessful() == true & currentUser.isEmailVerified()) {
                                startActivity(new Intent(MainActivity.this, MainHomeScreen.class));
                                setContentView(R.layout.activity_main_home_screen);
                            }

                        }
                    });
        }







    }


/*
        AndroidDatabaseHelper login = new AndroidDatabaseHelper(MainActivity_Profile.this);


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

        */




    // main firebase login funtion
    public void main_login_firebase(View v){

        //startActivity(new Intent(this,Firebase_New_User.class));
        startActivity(new Intent(this,SignInActivity.class));


    }

    // main google login funtion
    public void main_login_google(View v){


        Log.d("tag", "GOOGLE LOGIN WAS CLICKED --- 1");
        //startActivity(new Intent(this,Firebase_New_User.class));
        startActivity(new Intent(this,ChooserActivity.class));
        //setContentView(R.layout.main_activity);
        Log.d("tag", "GOOGLE LOGIN WAS CLICKED --- 2");


    }



    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

        FirebaseUser currentUser = mAuth.getCurrentUser();


        if (currentUser==null) {

        }
        else if (currentUser.isEmailVerified()==true) {
            startActivity(new Intent(MainActivity.this, MainHomeScreen.class));
            setContentView(R.layout.activity_main_home_screen); }
        //updateUI(currentUser);

            mGoogleApiClient.reconnect();
        if (mGoogleApiClient.isConnected()==true) {
            startActivity(new Intent(MainActivity.this, MainHomeScreen.class));
        }

        user.setText(str);

    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        user.setText(str);
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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }




    private void updateWithToken(AccessToken currentAccessToken) {

        if (currentAccessToken != null) {

            Intent i = new Intent(MainActivity.this, MainHomeScreen.class);
            startActivity(i);

            finish();
        } else {


            finish();
        }
    }







    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.sign_in_button) {
            signIn();
        } else if (i == R.id.sign_out_button) {
            signOut();
        } else if (i == R.id.disconnect_button) {
            //revokeAccess();
        }


    }
}


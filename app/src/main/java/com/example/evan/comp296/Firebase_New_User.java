package com.example.evan.comp296;

/**
 * Created by Evan on 4/24/17.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evan.comp296.Notes.Note_database;
import com.example.evan.comp296.Notes.User_SQLite;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Firebase_New_User extends BaseActivity implements View.OnClickListener{

    private static final String TAG = "EmailPassword";

    private TextView mStatusTextView;
    private TextView mDetailTextView;
    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mNameField;

    boolean signed_in = true;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    Context ctx=this;

    private AdView mAdView;

    private DatabaseReference mDatabase;
    String display_name;
    String full_name;


    //SharedPreferences sharedPref;

    Note_database db;
    LinearLayout l;


    @Override
    public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
    setContentView(R.layout.firebase_new_user);

    // Views
    mStatusTextView = (TextView) findViewById(R.id.status);
    mDetailTextView = (TextView) findViewById(R.id.detail);
    mEmailField = (EditText) findViewById(R.id.firebase_email);
    mPasswordField = (EditText) findViewById(R.id.firebase_password);
        mNameField = (EditText) findViewById(R.id.firebase_name);


    // Buttons
    findViewById(R.id.firebase_back_BTN).setOnClickListener(this);
    findViewById(R.id.firebase_register_BTN).setOnClickListener(this);
    findViewById(R.id.firebase_sign_out_BTN).setOnClickListener(this);
    findViewById(R.id.verify_email_button).setOnClickListener(this);

        //layout
        l = (LinearLayout) findViewById(R.id.linear_layout_firebase);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]


        mDatabase = FirebaseDatabase.getInstance().getReference();


        //sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        db = new Note_database(Firebase_New_User.this);

}

    //END ON CREATE




    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
        findViewById(R.id.firebase_register_BTN).setVisibility(View.VISIBLE);

        if (signed_in) {
            findViewById(R.id.firebase_sign_out_BTN).setVisibility(View.VISIBLE);
            findViewById(R.id.firebase_register_BTN).setVisibility(View.GONE);

        }


    }
    // [END on_start_check_user]

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            ///.. SET DISPLAY NAME AND IF APPLICABLE PROFILE PICTURE ...  //////

                            String FullName = mNameField.getText().toString();
                            String user_id = user.getUid();
                            String full_name = user.getDisplayName();
                            String fb_email = user.getEmail();
                            writeNewFireBaseUser(user_id, full_name, fb_email);


                            User_SQLite sql3 = new User_SQLite("1", fb_email, FullName, "Brookdale Community College");


                            if (db.ID_Exists(1)) {
                                db.update_row_info(sql3);
                            } else {
                                db.add_row_info(sql3); }



                            Toast.makeText(Firebase_New_User.this, "email is "+fb_email + " user name is "+FullName,
                                    Toast.LENGTH_SHORT).show();

                            //setDisplayName();


                            /*

                            SharedPreferences.Editor editor = sharedPref.edit();

                            editor.putString("email",fb_email);

                            editor.commit();

                            */

                            updateUI(user);





                            }else if(!task.isSuccessful()) {
                            try {
                                throw task.getException();
                            } catch(FirebaseAuthWeakPasswordException e) {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Firebase_New_User.this, "Authentication failed. weak password",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            } catch(FirebaseAuthInvalidCredentialsException e) {
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Firebase_New_User.this, "Authentication failed. email format invalid",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            } catch(FirebaseAuthUserCollisionException e) {
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Firebase_New_User.this, "Authentication failed. User already exists",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            } catch(Exception e) {
                                Log.e(TAG, e.getMessage());
                            }
                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }


    public void setDisplayName () {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(full_name)
                //.setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                .build();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = database.getReference().child("Firebase Users");
        try
        {

            mDatabase.child(user.getUid()).child("username").setValue(full_name);


            Log.d(TAG, " ----- UPDATED DATABASE  --------");

        }
        catch (Exception e)
        {
            Toast.makeText(getBaseContext(), "Unable to Transfer to Firebase", Toast.LENGTH_LONG).show();
        }

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile display name updated to- " + full_name);
                        }
                    }
                });
    }

    private void writeNewFireBaseUser(String userId, String name, String email) {

        Log.d(TAG, "------WRITE TO DATABSE FUNCTION CALLED---------");

        User user = new User(name, email);

        Log.d(TAG, "****************display name is " + name + " ********************");

        mDatabase.child("Firebase Users").child(userId).setValue(user);
    }



    protected void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;

        }

        showProgressDialog();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Firebase_New_User.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            mStatusTextView.setText("authorization failed");
                        }
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);

        findViewById(R.id.firebase_name).setVisibility(View.VISIBLE);
        findViewById(R.id.firebase_email).setVisibility(View.VISIBLE);
        findViewById(R.id.firebase_password).setVisibility(View.VISIBLE);
        findViewById(R.id.linear_layout_firebase).setVisibility(View.VISIBLE);;
        findViewById(R.id.verify_email_button).setVisibility(View.GONE);
        findViewById(R.id.firebase_sign_out_BTN).setVisibility(View.GONE);
        //findViewById(R.id.firebase_signed_in).setVisibility(View.GONE);
        findViewById(R.id.firebase_back_BTN).setVisibility(View.VISIBLE);
        findViewById(R.id.firebase_register_BTN).setVisibility(View.VISIBLE);

    }

    private void sendEmailVerification() {
        // Disable button
        findViewById(R.id.verify_email_button).setEnabled(false);

        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button
                        findViewById(R.id.verify_email_button).setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(Firebase_New_User.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();

                            findViewById(R.id.firebase_sign_out_BTN).setVisibility(View.VISIBLE);

                            //startActivity(new Intent(Firebase_New_User.this, MainActivity.class));
                            //setContentView(R.layout.main_activity);
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(Firebase_New_User.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else if (!email.contains(".")) {
            mEmailField.setError("invalid email");
        } else if(!email.contains("@")) {
            mEmailField.setError("invalid email");
        } else{
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else if (password.length()<=5){
            mPasswordField.setError("Password not strong enough");
        } else {
            mPasswordField.setError(null);
        }

        display_name = mNameField.getText().toString();
        if (TextUtils.isEmpty(display_name)) {
            mNameField.setError("Required.");
            valid = false;
        } else if (password.length()<=4){
            mPasswordField.setError("Enter full name");
        } else {
            full_name = display_name;
            mPasswordField.setError(null);
        }


        return valid;
    }

    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
        if (user != null) {
            mStatusTextView.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(), user.isEmailVerified()));
            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            findViewById(R.id.firebase_email).setVisibility(View.GONE);
            findViewById(R.id.firebase_password).setVisibility(View.GONE);
            findViewById(R.id.firebase_name).setVisibility(View.GONE);
            findViewById(R.id.linear_layout_firebase).setVisibility(View.GONE);
            findViewById(R.id.verify_email_button).setVisibility(View.VISIBLE);
            //findViewById(R.id.firebase_signed_in).setVisibility(View.VISIBLE);
            findViewById(R.id.firebase_back_BTN).setVisibility(View.VISIBLE);
            findViewById(R.id.firebase_register_BTN).setVisibility(View.GONE);



            findViewById(R.id.verify_email_button).setEnabled(!user.isEmailVerified());


        } else {
            mStatusTextView.setText("Not Signed in");
            mDetailTextView.setText(null);

            signed_in = false;

            findViewById(R.id.firebase_email).setVisibility(View.VISIBLE);
            findViewById(R.id.firebase_password).setVisibility(View.VISIBLE);
            //findViewById(R.id.firebase_signed_in).setVisibility(View.GONE);
        }

    }



        @Override
        public void onClick (View v){
            int i = v.getId();
            if (i == R.id.firebase_register_BTN) {
                createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
            } else if (i == R.id.email_sign_in_button) {
                signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
            } else if (i == R.id.firebase_sign_out_BTN) {
                signOut();
            } else if (i == R.id.verify_email_button) {
                sendEmailVerification();
                CharSequence email_sent = "Check your email for verification, then sign in";


                Toast.makeText(ctx, email_sent, Toast.LENGTH_LONG).show();
                startActivity(new Intent(this,MainActivity.class));

            }
            else if (i==R.id.firebase_back_BTN) {
                startActivity(new Intent(Firebase_New_User.this, MainActivity.class));
                setContentView(R.layout.main_activity);

            }
        }
    }

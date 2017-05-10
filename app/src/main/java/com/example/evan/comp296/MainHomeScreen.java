package com.example.evan.comp296;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.appindexing.Action;
import com.google.firebase.appindexing.FirebaseUserActions;
import com.google.firebase.appindexing.builders.Actions;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import com.example.evan.comp296.MainActivity;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.ArrayList;
import java.util.List;


public class MainHomeScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {


    private static final String TAG = "Tag";
    private FirebaseAuth mAuth;

    boolean signed_in;
    private FirebaseUser mFirebaseUser;


    GoogleApiClient mGoogleApiClient;

    private LoginManager loginManager;

    AccessToken accessToken;


    String str;

    Button home;
    private static final int RC_SIGN_IN = 9001;

    Intent intent = getIntent();

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    private RecyclerView.Adapter mAdapter;

    List<Data> data;

    AccessTokenTracker accessTokenTracker;


    Boolean facebook_loggedin;


    //SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
    String restoredText ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //restoredText = prefs.getString("email", null);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "**********hello ***********", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mAuth = FirebaseAuth.getInstance();

        mFirebaseUser = mAuth.getCurrentUser();


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
                .addConnectionCallbacks(this)
                .build();
        // [END build_client]

        mGoogleApiClient.connect();

        //home = (Button) findViewById(R.id.home_test);
        //str = String.valueOf(mGoogleApiClient.isConnected());
        //home.setText(str);

        //home.setText("******LOGIN MANAGER******" + loginManager.getInstance().toString());

        loginManager = LoginManager.getInstance();

        Log.d(TAG,loginManager.getInstance().toString());


        accessToken = AccessToken.getCurrentAccessToken();

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken newAccessToken) {
                updateWithToken(newAccessToken);
            }
        };


        updateWithToken(accessToken);


        //Log.d(TAG,accessToken.toString());


        //RECYCLER VIEW

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);


        data = fill_with_data();

        mAdapter = new RecyclerAdapter(data, getApplicationContext());

        mRecyclerView.setAdapter(mAdapter);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.log_out) {

            signOut();
            //revokeAccess();
            if (mGoogleApiClient.isConnected()==false){
            startActivity(new Intent(this,MainActivity.class));}

            loginManager.logOut();
            startActivity(new Intent(this,MainActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_profile) {
            startActivity(new Intent(this,onehundred_level.class));
        } else if (id == R.id.menu_notes) {

        } else if(id == R.id.menu_messaging) {

        } else if (id == R.id.menu_about_us) {

        } else if (id == R.id.menu_contact_us) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void go_back_home (View v) {



        startActivity(new Intent(this,MainActivity.class));


    }



    private void updateUI(FirebaseUser user) {
       // hideProgressDialog();
        if (user != null) {


        } else {

            signed_in = false;
        }

    }





    private void signOut() {
        mAuth.signOut();

        if (mGoogleApiClient.isConnected()==true) {


            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                            // [START_EXCLUDE]
                            updateUI(null);
                            // [END_EXCLUDE]
                        }
                    });
        }
        else if (accessToken != null) {

                loginManager.logOut();
                startActivity(new Intent(MainHomeScreen.this,MainActivity.class));


            GraphRequest delPermRequest = new GraphRequest(AccessToken.getCurrentAccessToken(), "/{user-id}/permissions/", null, HttpMethod.DELETE, new GraphRequest.Callback() {
                @Override
                public void onCompleted(GraphResponse graphResponse) {
                    if(graphResponse!=null){
                        FacebookRequestError error =graphResponse.getError();
                        if(error!=null){
                            Log.e(TAG, error.toString());
                        }else {
                            finish();
                            loginManager.logOut();
                            startActivity(new Intent(MainHomeScreen.this,MainActivity.class));
                        }
                    }
                }
            });
            Log.d(TAG,"Executing revoke permissions with graph path" + delPermRequest.getGraphPath());
            delPermRequest.executeAsync();


                loginManager.getInstance().logOut();
        }

        updateUI(null);
        //mGoogleApiClient.disconnect();
    }


    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {

                    }
                });
        mGoogleApiClient.disconnect();
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
            if (mGoogleApiClient.isConnected()==true) {
                //home.setText("true");

            }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
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

                home.setText(personName);
            }
        }
    }




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
                            Toast.makeText(MainHomeScreen.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                        //hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            home.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            //updateUI(true);
        } else {
            GoogleSignInAccount acct = result.getSignInAccount();
            home.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            // Signed out, show unauthenticated UI.
            //updateUI(false);
        }
    }



    public List fill_with_data() {

        List<Data> data = new ArrayList();


        List<Integer> data1 = new ArrayList();


        //data.add(R.mipmap.java_logo);

        /*
        data1.add((R.mipmap.java_logo));
        data1.add(R.mipmap.mysql);
        data1.add(R.mipmap.c_logo_icon_6);
        data1.add(R.mipmap.assembly);

        */

        data.add(new Data(R.mipmap.java_logo));
        data.add(new Data(R.mipmap.mysql));
        data.add(new Data(R.mipmap.c_logo_icon_6));
        data.add(new Data(R.mipmap.assembly));
        data.add(new Data(R.mipmap.html_css1));

        return data;
    }





    private void updateWithToken(AccessToken currentAccessToken) {

        if (currentAccessToken != null) {

            facebook_loggedin=true;


        } else {

            facebook_loggedin=false;

            finish();
        }

    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        return Actions.newView("MainHomeScreen", "http://[ENTER-YOUR-URL-HERE]");
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        FirebaseUserActions.getInstance().start(getIndexApiAction());
    }

    @Override
    public void onStop() {

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        FirebaseUserActions.getInstance().end(getIndexApiAction());
        super.onStop();
    }
}

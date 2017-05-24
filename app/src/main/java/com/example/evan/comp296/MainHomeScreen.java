package com.example.evan.comp296;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evan.comp296.Notes_main.Note_Homepage;
import com.example.evan.comp296.Notes_main.Note_database;
import com.example.evan.comp296.about_and_contact.Contact_us;
import com.example.evan.comp296.about_and_contact.about_us_2;
import com.example.evan.comp296.calendar_events.calendar_viewer;
import com.example.evan.comp296.group_chat.LoginActivity_for_chat;
import com.example.evan.comp296.invites.MainActivity_invites;
import com.example.evan.comp296.profile.Profile_main;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;
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
import com.google.firebase.auth.GoogleAuthProvider;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class MainHomeScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {


    private static final String TAG = "Tag";
    private FirebaseAuth mAuth;

    boolean signed_in;
    private FirebaseUser mFirebaseUser;


    GoogleApiClient mGoogleApiClient;

    private LoginManager loginManager;

    //AccessToken accessToken;


    String str;

    Button home;
    private static final int RC_SIGN_IN = 9001;

    Intent intent = getIntent();

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    private RecyclerView.Adapter mAdapter;

    List<Data> data;

    //AccessTokenTracker accessTokenTracker;


    Boolean facebook_loggedin;


    ProfilePictureView profilePictureView;

    TextView nav_text;
    ImageView nav_pic;

    Note_database nd;

    FloatingActionButton fab;
    FloatingActionButton fab1;
    FloatingActionButton fab2;
    FloatingActionButton fab3;
    FloatingActionButton fab4;

    private Boolean isFabOpen = false;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;


    //SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
    String restoredText ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //restoredText = prefs.getString("email", null);

        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                animateFAB();
                //Snackbar.make(view, "**********hello ***********", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
            }
        });

        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainHomeScreen.this, LoginActivity_for_chat.class));
            }
        });

        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainHomeScreen.this, Note_Homepage.class));
            }
        });

        fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainHomeScreen.this, calendar_viewer.class));
            }
        });

        fab4 = (FloatingActionButton) findViewById(R.id.fab4);
        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainHomeScreen.this, Profile_main.class));
            }
        });




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //navigationView.findViewById(R.id.textView_nav)

        Note_database nd = new Note_database(MainHomeScreen.this);

        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main_home_screen);
        nav_text = (TextView) headerView.findViewById(R.id.textView_nav);



        if(nd.ID_Exists(1)) {
            nav_text.setText(nd.getEmail());
        }else {

        }




        if (nd.Picture_Exists(1) && nd.get_Picture_URL(1) !=null) {

            nav_pic = (ImageView) headerView.findViewById(R.id.imageView_nav_profile);

            //Uri uri=Uri.parse(nd.get_Picture_URL(1));


            //Bitmap b = getImageBitmap(nd.get_Picture_URL(1));

            //nav_pic.setImageURI(uri);


            String url2= nd.get_Picture_URL(1);

            //new Task2().execute(url2);


            if (isConnectedToInternet()) {

                try {
                    nav_pic.setImageBitmap(new Task2().execute(url2).get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            } else {
                finish();
            }




            /*

            InputStream result=null;
            try {
                result = new Task2().execute(url2).get();
            } catch (InterruptedException e) {
                e.printStackTrace(); //handle it the way you like
            } catch (ExecutionException e) {
                e.printStackTrace();//handle it the way you like
            }


            if(result != null) {

                Drawable drawable = Drawable.createFromStream(result, null);
                nav_pic.setImageDrawable(drawable);

            }


            URL myUrl = null;
            try {
                myUrl = new URL(url2);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            InputStream inputStream = null;
            try {
                inputStream = (InputStream)myUrl.getContent();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Drawable drawable = Drawable.createFromStream(inputStream, null);
            nav_pic.setImageDrawable(drawable);

            */




        } else {
            nav_pic = (ImageView) headerView.findViewById(R.id.imageView_nav_profile);
            nav_pic.setImageResource(R.drawable.profile_icon);
        }






        if (nd.ID_Exists(1)) {
            Log.d(TAG, "*********** email is " + nd.getEmail() + "**********");
        }else {

        }

        //nav_text = (TextView) findViewById(R.id.textView_nav);




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


        //accessToken = AccessToken.getCurrentAccessToken();

        /*accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken newAccessToken) {
                updateWithToken(newAccessToken);
            }
        };

        */
        //updateWithToken(accessToken);


        //Log.d(TAG,accessToken.toString());


        //RECYCLER VIEW

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);


        data = fill_with_data();

        mAdapter = new RecyclerAdapter(data, getApplicationContext());

        mRecyclerView.setAdapter(mAdapter);


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 || dy < 0 && fab.isShown())

                    if (isFabOpen)
                        fab.show();
                    else
                    fab.hide();



            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    fab.show();



                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });


        //Profile profile;
        //profile = Profile.getCurrentProfile();

        //profile.getId();



        //profilePictureView = (ProfilePictureView) findViewById(R.id.friendProfilePicture);

        //profilePictureView.setProfileId(profile.getId());



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
            if (mGoogleApiClient.isConnected() == false) {
                startActivity(new Intent(this, MainActivity.class));
            }

            loginManager.logOut();
            startActivity(new Intent(this, MainActivity.class));
            return true;

        }else if(id ==R.id.settings) {
            startActivity(new Intent(this, Settings_screen.class));
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
            startActivity(new Intent(this,Profile_main.class));
        } else if (id == R.id.menu_notes) {
            startActivity(new Intent(this,Note_Homepage.class));

        } else if(id == R.id.menu_messaging) {
            startActivity(new Intent(this,LoginActivity_for_chat.class));

        } else if (id == R.id.menu_about_us) {
            startActivity(new Intent(this,about_us_2.class));

        } else if (id == R.id.menu_contact_us) {

            startActivity(new Intent(this, Contact_us.class));

        } else if (id == R.id.menu_calendar) {

            startActivity(new Intent(this, calendar_viewer.class));

        } else if (id == R.id.nav_share) {

            startActivity(new Intent(this,MainActivity_invites.class));

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
        //else if (accessToken != null) {

               // loginManager.logOut();
               // startActivity(new Intent(MainHomeScreen.this,MainActivity_invites.class));

            /*

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
                            startActivity(new Intent(MainHomeScreen.this,MainActivity_Profile.class));
                        }
                    }
                }
            });


            */

            //Log.d(TAG,"Executing revoke permissions with graph path" + delPermRequest.getGraphPath());
            //delPermRequest.executeAsync();


               // loginManager.getInstance().logOut();
        //}

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

        data.add(new Data(R.mipmap.java_logo_icon_23));
        data.add(new Data(R.mipmap.mysql));
        data.add(new Data(R.mipmap.c_logo_icon_67));
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


    private Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e(TAG, "Error getting bitmap", e);
        }
        return bm;
    }


    public void downloadFile(String uRl) {
        File direct = new File(Environment.getExternalStorageDirectory()
                + "/study_guide");

        if (!direct.exists()) {
            direct.mkdirs();
        }

        DownloadManager mgr = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);

        Uri downloadUri = Uri.parse(uRl);
        DownloadManager.Request request = new DownloadManager.Request(
                downloadUri);

        request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI
                        | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false).setTitle("Demo")
                .setDescription("Profile Picture.")
                .setDestinationInExternalPublicDir("/study_guide", "profile_pic.jpg");

        mgr.enqueue(request);

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


    public void animateFAB(){

        if(isFabOpen){

            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab3.startAnimation(fab_close);
            fab4.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.setClickable(false);
            fab4.setClickable(false);
            isFabOpen = false;
            Log.d("mainhomescreen", "close");

        } else {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab3.startAnimation(fab_open);
            fab4.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.setClickable(true);
            fab4.setClickable(true);
            isFabOpen = true;
            Log.d("mainhomescreen","open");

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

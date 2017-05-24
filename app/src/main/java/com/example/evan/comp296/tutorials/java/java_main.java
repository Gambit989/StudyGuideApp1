package com.example.evan.comp296.tutorials.java;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.evan.comp296.Data;
import com.example.evan.comp296.Notes_main.Note_drv;
import com.example.evan.comp296.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evan on 5/7/17.
 */

public class java_main extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner java_spinner;

    RecyclerView java_RecyclerView1;

    LinearLayoutManager mLinearLayoutManager;
    LinearLayoutManager mLinearLayoutManager2;
    LinearLayoutManager mLinearLayoutManager3;
    LinearLayoutManager mLinearLayoutManager4;

    Java_Recycler_Adapter mAdapter;
    Java_Recycler_Adapter mAdapter1;
    Java_Recycler_Adapter2 mAdapter2;
    Java_Recycler_Adapter3 mAdapter3;

    Toolbar toolbar;


    List<Object> data;
    List data2;

    NativeExpressAdView adView;

    AdView adView2;

    int spinner_position;
    //int spinner_saved_position;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    // A Native Express ad is placed in every nth position in the RecyclerView.
    public static final int ITEMS_PER_AD = 6;

    // The Native Express ad height.
    private static final int NATIVE_EXPRESS_AD_HEIGHT = 150;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.java_main);

        Log.d("onCreate", "*********onCreate CALLED **********");


        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();


        spinner_position= sharedPref.getInt("saved_position",0);
        /*
        if (savedInstanceState != null) {
        Bundle bundle = savedInstanceState != null ? savedInstanceState : getIntent().getExtras();
        spinner_saved_position = bundle.getInt("java_spinner");
            Log.d("onCreate", "*********spinner saved postion= " +spinner_saved_position+ "**********");
        java_spinner.setSelection(spinner_saved_position); }
        */



        toolbar = (Toolbar) findViewById(R.id.toolbar_java);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        java_spinner = (Spinner) findViewById(R.id.spinner_java);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.java_classes, R.layout.spinner_text);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        // Apply the adapter to the spinner
        java_spinner.setAdapter(adapter);


        java_spinner.setOnItemSelectedListener(this);


        MobileAds.initialize(getApplicationContext(), getString(R.string.adMob_App_ID) );



        adView = (NativeExpressAdView) findViewById(R.id.java_native_ad_1);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        adView2 = (AdView) findViewById(R.id.java_banner);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        adView.loadAd(adRequest2);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab4);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(java_main.this, Note_drv.class));

                /*

                Snackbar.make(view, "Email app to a friend", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                 */
            }
        });




        data = fill_with_data();

        data2=fill_with_data3();



        java_RecyclerView1 = (RecyclerView) findViewById(R.id.java_recycler);
        java_RecyclerView1.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(this);
        java_RecyclerView1.setLayoutManager(mLinearLayoutManager);


        addNativeExpressAds();
        //setUpAndLoadNativeExpressAds();

        mAdapter = new Java_Recycler_Adapter(data2, getApplicationContext());
        java_RecyclerView1.setAdapter(mAdapter);


    }










    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("java_spinner", spinner_position);


        Log.d("SpinnerState", "*********Spinner at position " + spinner_position  + " was saved**************");
    }





    protected void onResume(){
        super.onResume();


        if (spinner_position != -1) {
            java_spinner.setSelection(spinner_position);}

        Log.d("SpinnerState", "*********onResume CALLED spinner position= " +spinner_position+ "**********");

    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String selected = parent.getItemAtPosition(position).toString();


        //spinner_position=position;

        if (position==1) {


            editor.putInt("saved_position", position);
            editor.commit();


            mAdapter1 = new Java_Recycler_Adapter(data, getApplicationContext());


            mLinearLayoutManager2 = new LinearLayoutManager(this);
            java_RecyclerView1.setLayoutManager(mLinearLayoutManager2);

            java_RecyclerView1.setAdapter(mAdapter1);

            setUpAndLoadNativeExpressAds();

        } else if (position==2) {

            editor.putInt("saved_position", position);
            editor.commit();

            mAdapter2 = new Java_Recycler_Adapter2(fill_with_data2(), getApplicationContext());


            mLinearLayoutManager3 = new LinearLayoutManager(this);
            java_RecyclerView1.setLayoutManager(mLinearLayoutManager3);

            java_RecyclerView1.setAdapter(mAdapter2);

            adView.setVisibility(View.VISIBLE);

        } else if (position==3) {

            editor.putInt("saved_position", position);
            editor.commit();

            mAdapter3 = new Java_Recycler_Adapter3(fill_with_data4(), getApplicationContext());


            mLinearLayoutManager4 = new LinearLayoutManager(this);
            java_RecyclerView1.setLayoutManager(mLinearLayoutManager4);

            java_RecyclerView1.setAdapter(mAdapter3);

            adView2.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    private void addNativeExpressAds() {

        // Loop through the items array and place a new Native Express ad in every ith position in
        // the items List.
        for (int i = 0; i <= data.size(); i += ITEMS_PER_AD) {
            final NativeExpressAdView adView = new NativeExpressAdView(java_main.this);
            adView.setAdUnitId(getString(R.string.java_main_ad));
            //adView.setAdSize(new AdSize(320, 150));
            //adView.loadAd(new AdRequest.Builder().build());
            data.add(i, adView);
        }

    }


    private void setUpAndLoadNativeExpressAds() {
        // Use a Runnable to ensure that the RecyclerView has been laid out before setting the
        // ad size for the Native Express ad. This allows us to set the Native Express ad's
        // width to match the full width of the RecyclerView.





        final Handler handler = new Handler();


        handler.postDelayed(new Runnable() {

            @Override
            public void run() {


                java_RecyclerView1.post(new Runnable() {
                    @Override
                    public void run() {
                        final float scale = java_main.this.getResources().getDisplayMetrics().density;
                        // Set the ad size and ad unit ID for each Native Express ad in the items list.
                        for (int i = 0; i <= data.size(); i += ITEMS_PER_AD) {
                            final NativeExpressAdView adView =
                                    (NativeExpressAdView) data.get(i);
                            final CardView cardView = (CardView) findViewById(R.id.ad_card_view);
                            final int adWidth = cardView.getWidth() - cardView.getPaddingLeft()
                                    - cardView.getPaddingRight();
                            AdSize adSize = new AdSize((int) (adWidth / scale), NATIVE_EXPRESS_AD_HEIGHT);
                            adView.setAdSize(adSize);
                            //adView.setAdUnitId(getString(R.string.java_main_ad));
                        }

                        // Load the first Native Express ad in the items list.
                        loadNativeExpressAd(0);
                    }
                });



            }

        }, 1200);



    }





    /**
     * Loads the Native Express ads in the items list.
     */
    private void loadNativeExpressAd(final int index) {

        if (index >= data.size()) {
            return;
        }

        Object item = data.get(index);
        if (!(item instanceof NativeExpressAdView)) {
            throw new ClassCastException("Expected item at index " + index + " to be a Native"
                    + " Express ad.");
        }

        final NativeExpressAdView adView = (NativeExpressAdView) item;

        // Set an AdListener on the NativeExpressAdView to wait for the previous Native Express ad
        // to finish loading before loading the next ad in the items list.
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                // The previous Native Express ad loaded successfully, call this method again to
                // load the next ad in the items list.
                loadNativeExpressAd(index + ITEMS_PER_AD);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // The previous Native Express ad failed to load. Call this method again to load
                // the next ad in the items list.
                Log.e("Java Main", "The previous Native Express ad failed to load. Attempting to"
                        + " load the next Native Express ad in the items list.");
                loadNativeExpressAd(index + ITEMS_PER_AD);
            }
        });

        // Load the Native Express ad.
        adView.loadAd(new AdRequest.Builder().build());
    }

    public List fill_with_data() {

        List<Data> data = new ArrayList();



        data.add(new Data("Intro"));
        data.add(new Data("Basic Syntax"));
        data.add(new Data("Objects and Classes"));
        data.add(new Data("Basic Datatypes"));
        data.add(new Data("Variables"));
        data.add(new Data("Modifier Types"));
        data.add(new Data("Operators"));
        data.add(new Data("Loops"));
        data.add(new Data("Decision Making"));
        data.add(new Data("Numbers"));
        data.add(new Data("Chars"));
        data.add(new Data("Strings"));
        data.add(new Data("Arrays"));
        data.add(new Data("RegEx"));
        data.add(new Data("Methods"));
        data.add(new Data("File I/O"));


        return data;
    }


    public List fill_with_data2() {

        List<Data> data = new ArrayList();


        data.add(new Data("Intro to Data Structures"));
        data.add(new Data("Stacks"));
        data.add(new Data("Queue"));
        data.add(new Data("Linked List"));

        return data;
    }


    public List fill_with_data3() {

        List<Data> data = new ArrayList();


        return data;
    }


    public List fill_with_data4() {

        List<Data> data = new ArrayList();


        data.add(new Data("Inheritence"));
        data.add(new Data("Polymorphism"));
        data.add(new Data("Abstraction"));
        data.add(new Data("Enapsulation"));
        data.add(new Data("Overriding"));
        data.add(new Data("Interfaces"));
        data.add(new Data("Packages"));


        return data;
    }






}

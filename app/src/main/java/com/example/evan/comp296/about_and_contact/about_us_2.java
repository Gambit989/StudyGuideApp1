package com.example.evan.comp296.about_and_contact;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.evan.comp296.R;

/**
 * Created by Evan on 5/15/17.
 */

public class about_us_2 extends AppCompatActivity {


    CollapsingToolbarLayout mCollapsingToolbar;
    View view_fb, view_github, view_email, view_itc, view_easter_egg;
    FloatingActionButton mFab;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        findViews();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://comp296-84489.firebaseapp.com"));
                startActivity(browserIntent);
            }
        });

        view_itc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://comp296-84489.firebaseapp.com/aboutus.html"));
                startActivity(browserIntent);

            }
        });

    }




    @Override
    public void finish() {
        super.finish();

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


    private void findViews() {
        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        view_itc = findViewById(R.id.view_itc);
        view_easter_egg = findViewById(R.id.view_easter_egg);

        mFab = (FloatingActionButton) findViewById(R.id.fab);
    }
}

package com.ecmediagroup.evan.studyguide.Notes_main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.ecmediagroup.evan.studyguide.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;

/**
 * Created by Evan on 5/12/17.
 */

public class Note_viewer extends AppCompatActivity {

    TextView note;

    NativeExpressAdView mAdView;

    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_viewer);

        toolbar= (Toolbar) findViewById(R.id.toolbar_note_viewer);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        MobileAds.initialize(getApplicationContext(), getString(R.string.adMob_App_ID));

        mAdView = (NativeExpressAdView) findViewById(R.id.native_express_adview_note_viewer);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        String value = getIntent().getExtras().getString("note");


        note = (TextView) findViewById(R.id.note_text);


        note.setText(value);



    }
}



package com.example.evan.comp296.Notes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.evan.comp296.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by Evan on 5/12/17.
 */

public class Note_viewer extends AppCompatActivity {

    TextView note;

    AdView mAdView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_viewer);


        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");

        mAdView = (AdView) findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        String value = getIntent().getExtras().getString("note");


        note = (TextView) findViewById(R.id.note_text);


        note.setText(value);



    }
}



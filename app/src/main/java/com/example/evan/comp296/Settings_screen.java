package com.example.evan.comp296;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.evan.comp296.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;

/**
 * Created by Evan on 5/17/17.
 */

public class Settings_screen extends AppCompatActivity {

    NativeExpressAdView adView;
    ImageView website;
    Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_screen);

        toolbar= (Toolbar) findViewById(R.id.settings_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        MobileAds.initialize(getApplicationContext(), getResources().getString(R.string.banner_ad_unit_id) );



        adView = (NativeExpressAdView) findViewById(R.id.native_express_adview_1);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        website = (ImageView) findViewById(R.id.view_itc_2);


        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://comp296-84489.firebaseapp.com/aboutus.html"));
                startActivity(browserIntent);

            }
        });


    }
}






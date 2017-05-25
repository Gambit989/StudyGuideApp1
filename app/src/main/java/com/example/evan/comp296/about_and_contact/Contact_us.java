package com.example.evan.comp296.about_and_contact;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.evan.comp296.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;

/**
 * Created by Evan on 5/17/17.
 */

public class Contact_us extends AppCompatActivity {

    View view_fb, view_github, view_email, view_itc, view_easter_egg;
    NativeExpressAdView adView;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us_3);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_contact_us);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViews();

        MobileAds.initialize(getApplicationContext(), getString(R.string.adMob_App_ID) );
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);











        view_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://comp296-84489.firebaseapp.com/index.html"));
                startActivity(browserIntent);

            }
        });


        view_github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Gambit989"));
                startActivity(browserIntent);

            }
        });




        view_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:"
                        + "evan@ecmediagrup.net"
                        + "?subject=" + "Feedback" + "&body=" + "");
                intent.setData(data);
                startActivity(intent);

            }
        });


    }




    @Override
    public void finish() {
        super.finish();

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


    private void findViews() {

        view_fb = (ImageView) findViewById(R.id.view_fb2);
        view_github = (ImageView) findViewById(R.id.view_github2);
        view_email = (ImageView) findViewById(R.id.view_email2);
        adView = (NativeExpressAdView) findViewById(R.id.native_express_adview_2);

        }


    }

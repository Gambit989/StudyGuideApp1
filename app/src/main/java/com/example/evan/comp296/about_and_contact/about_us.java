package com.example.evan.comp296.about_and_contact;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.evan.comp296.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by Evan on 5/11/17.
 */

public class about_us extends AppCompatActivity {


    TextView about_us_text;
    TextView about_us_section;
    WebView wv;

    private AdView mAdview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);


        AdView mAdView = (AdView) findViewById(R.id.adView4);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        //wv = (WebView) findViewById(R.id.webview_about_us);

        //wv.loadUrl("https://comp296-84489.firebaseapp.com/index2.html");

        about_us_section = (TextView) findViewById(R.id.textview_about_section);
        about_us_text = (TextView) findViewById(R.id.text_about_us_link);

        about_us_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://comp296-84489.firebaseapp.com/aboutus.html"));
                startActivity(intent);

            }
        });





        //about_us_text.setText((Html.fromHtml("<h2>Title</h2><br><p>Description here</p>"));;



        //wv = (WebView) findViewById(R.id.webview_about_us);

        //wv.loadUrl("http://www.ecmediagroup.net/aboutus.html");



    }
}

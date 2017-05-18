package com.example.evan.comp296.tutorials.java;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.evan.comp296.Notes.Note_drv;
import com.example.evan.comp296.R;

/**
 * Created by Evan on 5/13/17.
 */

public class comp271 extends AppCompatActivity{




    int page_num;

    WebView wv;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_webview);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar1);


        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Comp-271");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        wv = (WebView) findViewById(R.id.webview1);

        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);

        Bundle bundle = getIntent().getExtras();

        page_num= bundle.getInt("page_num");


        if (page_num==0){
            wv.loadUrl("https://www.tutorialspoint.com/java/java_inheritance.htm");
        } else if (page_num==1) {
            wv.loadUrl("https://www.tutorialspoint.com/java/java_polymorphism.htm");

        } else if (page_num==2) {
            wv.loadUrl("https://www.tutorialspoint.com/java/java_abstraction.htm");

        } else if (page_num==3) {
            wv.loadUrl("https://www.tutorialspoint.com/java/java_encapsulation.htm");

        } else if (page_num==4) {
            wv.loadUrl("https://www.tutorialspoint.com/java/java_overriding.htm");
        } else if (page_num==5) {
            wv.loadUrl("https://www.tutorialspoint.com/java/java_interfaces.htm");
        }  else if (page_num==6) {
            wv.loadUrl("https://www.tutorialspoint.com/java/java_packages.htm");
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.webview_menu, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_favorite:

                startActivity(new Intent(comp271.this, Note_drv.class));

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
